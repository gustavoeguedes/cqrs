package br.com.ms_beautique_query.services;

import br.com.ms_beautique_query.dtos.appointments.FullAppoinmentDTO;

import java.util.List;

public interface AppointmentService {
    List<FullAppoinmentDTO> findAll();
    List<FullAppoinmentDTO> findByCustomerId(Long customerId);
    List<FullAppoinmentDTO> findByBeautyProcedureId(Long beautyProcedureId);
}
