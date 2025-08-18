package bt.com.beautique.api.repositories;

import bt.com.beautique.api.entities.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<AppointmentsEntity, Long> {
}
