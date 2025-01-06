package id.co.app.application.repository;

import id.co.app.application.domain.table.DatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatLogRepo extends JpaRepository<DatLog, String> {
}
