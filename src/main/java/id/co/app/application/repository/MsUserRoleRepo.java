package id.co.app.application.repository;

import id.co.app.application.domain.table.MsUser;
import id.co.app.application.domain.table.MsUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsUserRoleRepo extends JpaRepository<MsUserRole, String> {
    @Query(value = RepoQuery.GET_LIST_USER_ROLES, nativeQuery = true)
    List<String> getRolesByIdUser(String iduser);
}
