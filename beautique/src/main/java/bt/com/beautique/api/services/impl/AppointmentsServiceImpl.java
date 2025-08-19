package bt.com.beautique.api.services.impl;

import bt.com.beautique.api.dtos.AppointmentDTO;
import bt.com.beautique.api.entities.AppointmentsEntity;
import bt.com.beautique.api.entities.BeautyProceduresEntity;
import bt.com.beautique.api.entities.CustomerEntity;
import bt.com.beautique.api.repositories.AppointmentsRepository;
import bt.com.beautique.api.repositories.BeautyProcedureRepository;
import bt.com.beautique.api.repositories.CustomerRepository;
import bt.com.beautique.api.services.AppointmentsService;
import bt.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    private final ConverterUtil<AppointmentDTO, AppointmentsEntity> converterUtil = new ConverterUtil<>(AppointmentDTO.class, AppointmentsEntity.class);

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

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
        return buildAppointmentDto(updatedAppointmentEntity);
    }
}
