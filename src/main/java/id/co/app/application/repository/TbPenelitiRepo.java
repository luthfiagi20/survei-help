package id.co.app.application.repository;

import id.co.app.application.domain.table.TbPeneliti;
import id.co.app.application.domain.table.TbResponden;
import id.co.app.application.repository.repoModel.ProfilRespondenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TbPenelitiRepo extends JpaRepository<TbPeneliti, String> {

        @Query(value = RepoQuery.GET_DATA_PENELITI, nativeQuery = true)
        ProfilRespondenModel findPeneliti(String iduser);

        TbPeneliti findByChIdUser(String chIdUser);
}
