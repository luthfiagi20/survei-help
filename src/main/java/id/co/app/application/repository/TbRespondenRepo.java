package id.co.app.application.repository;

import id.co.app.application.domain.table.MsUser;
import id.co.app.application.domain.table.MsUserRole;
import id.co.app.application.domain.table.TbResponden;
import id.co.app.application.repository.repoModel.ProfilRespondenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbRespondenRepo extends JpaRepository<TbResponden, String> {
/*    @Query(value = RepoQuery.GET_LIST_USER_ROLES, nativeQuery = true)
    List<String> getRolesByIdUser(String iduser);*/

        @Query(value = RepoQuery.GET_DATA_RESPONDEN, nativeQuery = true)
        ProfilRespondenModel findResponden(String iduser);

        TbResponden findByChIdUser(String chIdUser);

        /*@Query(value = RepoQuery.GET_PROFIL_RESPONDEN, nativeQuery = true)
        ProfilRespondenModel findByIdMsUser(String iduser);*/
}
