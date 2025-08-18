package bt.com.beautique.api.services;

import bt.com.beautique.api.dtos.AppointmentDTO;

public interface AppointmentsService {
    AppointmentDTO create(AppointmentDTO appointmentDTO);
    AppointmentDTO updateById(Long id, AppointmentDTO appointmentDTO);
    void deleteById(Long id);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentsEntity);
}
