package bt.com.beautique.api.services.impl;

import bt.com.beautique.api.dtos.AppointmentDTO;
import bt.com.beautique.api.entities.AppointmentsEntity;
import bt.com.beautique.api.repositories.AppointmentsRepository;
import bt.com.beautique.api.services.AppointmentsService;
import bt.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class AppointmentsServiceImpl implements AppointmentsService {

    private final ConverterUtil<AppointmentDTO, AppointmentsEntity> converterUtil = new ConverterUtil<>(AppointmentDTO.class, AppointmentsEntity.class);

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    private AppointmentsEntity findById(Long id) {
        return appointmentsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "appointment not found"));
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



    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        appointmentsRepository.deleteById(id);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentsEntity) {
        return null;
    }
}
