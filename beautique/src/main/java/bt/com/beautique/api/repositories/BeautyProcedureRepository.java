package bt.com.beautique.api.repositories;

import bt.com.beautique.api.entities.BeautyProceduresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeautyProcedureRepository extends JpaRepository<BeautyProceduresEntity, Long> {
}
