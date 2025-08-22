package br.com.ms_beautique_query.services.impl;

import br.com.ms_beautique_query.dtos.appointments.FullAppoinmentDTO;
import br.com.ms_beautique_query.repositories.AppointmentRepository;
import br.com.ms_beautique_query.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<FullAppoinmentDTO> findAll() {
        try {
            return appointmentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving appointments: " + e.getMessage(), e);
        }
    }

    @Override
    public List<FullAppoinmentDTO> findByCustomerId(Long customerId) {
        try {
            return appointmentRepository.findByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving appointments: " + e.getMessage(), e);
        }
    }

    @Override
    public List<FullAppoinmentDTO> findByBeautyProcedureId(Long beautyProcedureId) {
        try {
            return appointmentRepository.findByBeautyProcedureId(beautyProcedureId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving appointments: " + e.getMessage(), e);
        }
    }
}
