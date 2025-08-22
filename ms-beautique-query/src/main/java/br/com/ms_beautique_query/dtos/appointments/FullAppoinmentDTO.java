package br.com.ms_beautique_query.dtos.appointments;

import br.com.ms_beautique_query.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.ms_beautique_query.dtos.customers.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class FullAppoinmentDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsIsOpen;
    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}
