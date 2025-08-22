package br.com.ms_beautique_query.repositories;

import br.com.ms_beautique_query.dtos.appointments.FullAppoinmentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<FullAppoinmentDTO, Long> {
    @Query("{'customerId': ?0 }")
    List<FullAppoinmentDTO> findByCustomerId(Long customerId);

    @Query("{'beautyProcedureId': ?0 }")
    List<FullAppoinmentDTO> findByBeautyProcedureId(Long beautyProcedureId);
}
