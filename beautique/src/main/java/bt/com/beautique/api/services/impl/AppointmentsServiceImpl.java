package bt.com.beautique.api.services.impl;

import bt.com.beautique.api.dtos.AppointmentDTO;
import bt.com.beautique.api.dtos.BeautyProcedureDTO;
import bt.com.beautique.api.dtos.CustomerDTO;
import bt.com.beautique.api.dtos.FullAppointmentDTO;
import bt.com.beautique.api.entities.AppointmentsEntity;
import bt.com.beautique.api.entities.BeautyProceduresEntity;
import bt.com.beautique.api.entities.CustomerEntity;
import bt.com.beautique.api.repositories.AppointmentsRepository;
import bt.com.beautique.api.repositories.BeautyProcedureRepository;
import bt.com.beautique.api.repositories.CustomerRepository;
import bt.com.beautique.api.services.AppointmentsService;
import bt.com.beautique.api.services.BrokerService;
import bt.com.beautique.api.utils.ConverterUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final ConverterUtil<AppointmentDTO, AppointmentsEntity> converterUtil = new ConverterUtil<>(AppointmentDTO.class, AppointmentsEntity.class);

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrokerService brokerService;

    private void sendAppointmentToQueue(AppointmentsEntity appointmentsEntity) {
        CustomerDTO customerDTO = appointmentsEntity.getCustomer() != null ? modelMapper.map(appointmentsEntity.getCustomer(), CustomerDTO.class) : null;
        BeautyProcedureDTO beautyProcedureDto = appointmentsEntity.getBeautyProcedure() != null ? modelMapper.map(appointmentsEntity.getBeautyProcedure(), BeautyProcedureDTO.class) : null;
        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsIsOpen(appointmentsEntity.getAppointmentsIsOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProcedureDto)
                .build();
        brokerService.send("appointments", fullAppointmentDTO);
    }

    private AppointmentsEntity findById(Long id) {
        return appointmentsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "appointment not found"));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return beautyProcedureRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "beauty procedure not found"));
    }

    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    private AppointmentDTO buildAppointmentDto(AppointmentsEntity appointmentsEntity) {
        return AppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .beautyProcedure(appointmentsEntity.getBeautyProcedure().getId())
                .appointmentsIsOpen(appointmentsEntity.getAppointmentsIsOpen())
                .customer(appointmentsEntity.getCustomer().getId())
                .dateTime(appointmentsEntity.getDateTime())
                .build();
    }


    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentToSave = converterUtil.convertToTarget(appointmentDTO);
        AppointmentsEntity appointmentSaved = appointmentsRepository.save(appointmentToSave);

        sendAppointmentToQueue(appointmentSaved);
        return converterUtil.convertToSource(appointmentSaved);
    }

    @Override
    public AppointmentDTO updateById(Long id, AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentToUpdate = findById(id);


        if (appointmentDTO.getDateTime() != null &&
                !Objects.equals(appointmentToUpdate.getDateTime(), appointmentDTO.getDateTime())) {
            appointmentToUpdate.setDateTime(appointmentDTO.getDateTime());
        }

        if (appointmentDTO.getAppointmentsIsOpen() != null &&
                !Objects.equals(appointmentToUpdate.getAppointmentsIsOpen(), appointmentDTO.getAppointmentsIsOpen())) {
            appointmentToUpdate.setAppointmentsIsOpen(appointmentDTO.getAppointmentsIsOpen());
        }

        AppointmentsEntity updated = appointmentsRepository.save(appointmentToUpdate);

        sendAppointmentToQueue(updated);
        return converterUtil.convertToSource(updated);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        appointmentsRepository.deleteById(id);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(Long id, AppointmentDTO appointmentDTO) {
        CustomerEntity customerEntity = findCustomerById(appointmentDTO.getCustomer());
        BeautyProceduresEntity beautyProceduresEntity = findBeautyProcedureById(appointmentDTO.getBeautyProcedure());
        AppointmentsEntity appointmentsEntity = findById(id);
        appointmentsEntity.setCustomer(customerEntity);
        appointmentsEntity.setBeautyProcedure(beautyProceduresEntity);
        appointmentsEntity.setAppointmentsIsOpen(false);
        AppointmentsEntity updatedAppointmentEntity = appointmentsRepository.save(appointmentsEntity);
        sendAppointmentToQueue(updatedAppointmentEntity);
        return buildAppointmentDto(updatedAppointmentEntity);
    }
}
