package id.co.app.application.repository;

import id.co.app.application.domain.table.TbReward;
import id.co.app.application.domain.table.TbSurveyPeneliti;
import id.co.app.application.repository.repoModel.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbRewardRepo extends JpaRepository<TbReward, String> {

    @Query(value = RepoQuery.GET_STATUS_REWARD, nativeQuery = true)
    SurveyModel findStatusPermohonan(String idUser);

    TbReward findByChIdReward(String chIdReward);

    @Query(value = RepoQuery.GET_DAFTAR_PMH_PENCAIRAN_REWARD, nativeQuery = true)
    List<RewardModel> getPmhPencairanReward(String statusBayar);


}
