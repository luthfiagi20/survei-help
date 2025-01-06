package id.co.app.application.repository;

import id.co.app.application.domain.table.MsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsUserRepo extends JpaRepository<MsUser, String> {
    MsUser findByIdMsUser(String idMsUser);
    List<MsUser> findByFgAktif(String fgAktif);
}
