package bt.com.beautique.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "appointments")
public class AppointmentsEntity extends BaseEntity {

    @Column(nullable = false, updatable = true)
    private LocalDateTime dateTime;

    @Column(name = "appointments_is_open", nullable = false)
    private Boolean appointmentsIsOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauty_procedure_id", nullable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private BeautyProceduresEntity beautyProcedure;
}
