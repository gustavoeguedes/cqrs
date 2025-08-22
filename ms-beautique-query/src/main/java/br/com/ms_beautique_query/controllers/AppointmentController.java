package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.appointments.FullAppoinmentDTO;
import br.com.ms_beautique_query.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<FullAppoinmentDTO>> findAll() {
        List<FullAppoinmentDTO> appointments = appointmentService.findAll();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<FullAppoinmentDTO>> findByCustomerId(@PathVariable Long customerId) {
        List<FullAppoinmentDTO> appointments = appointmentService.findByCustomerId(customerId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/by-beauty-procedure/{beautyProcedureId}")
    public ResponseEntity<List<FullAppoinmentDTO>> findByBeautyProcedureId(@PathVariable Long beautyProcedureId) {
        List<FullAppoinmentDTO> appointments = appointmentService.findByBeautyProcedureId(beautyProcedureId);
        return ResponseEntity.ok(appointments);
    }
}
