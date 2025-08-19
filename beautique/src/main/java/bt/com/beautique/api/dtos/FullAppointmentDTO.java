package bt.com.beautique.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullAppointmentDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsIsOpen;

    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}
