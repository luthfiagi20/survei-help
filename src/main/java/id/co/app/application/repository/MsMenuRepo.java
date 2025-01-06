package id.co.app.application.repository;

import id.co.app.application.domain.table.MsMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsMenuRepo extends JpaRepository<MsMenu, Long> {
    List<MsMenu> findByFgAktifOrderByIdParentAscUrutanPerLevelAsc(String fgAktif);
}



