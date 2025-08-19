package bt.com.beautique.api.controllers;

import bt.com.beautique.api.dtos.AppointmentDTO;
import bt.com.beautique.api.services.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentsService appointmentsService;

    @PostMapping
    ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentsService.create(appointmentDTO));
    }

    @PatchMapping("/{id}")
    ResponseEntity<AppointmentDTO> updateById(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentsService.updateById(id, appointmentDTO));
    }

    @PutMapping("/{id}")
    ResponseEntity<AppointmentDTO> setCustomerToAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentsService.setCustomerToAppointment(id, appointmentDTO));
    }

}
