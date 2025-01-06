package id.co.app.application.repository;

import id.co.app.application.domain.table.TbSurveyPeneliti;
import id.co.app.application.domain.table.TbSurveyResponden;
import id.co.app.application.repository.repoModel.DetailSurveyModel;
import id.co.app.application.repository.repoModel.IsiSurveyModel;
import id.co.app.application.repository.repoModel.RewardModel;
import id.co.app.application.repository.repoModel.SurveyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbSurveyRespondenRepo extends JpaRepository<TbSurveyResponden, String> {
    TbSurveyResponden findByChIdRespondenAndAndChIdSurveyPeneliti(String chIdResponden, String chIdSurveyPeneliti);

    @Query(value = RepoQuery.GET_DAFTAR_REWARD_BY_ID_USER, nativeQuery = true)
        //by adhit
    List<IsiSurveyModel> getDaftarReward(String idUser);

    @Query(value = RepoQuery.GET_DAFTAR_PENCAIRAN_REWARD_BY_ID_USER, nativeQuery = true)
    List<RewardModel> getDaftarPencairanReward(String idUser);


    TbSurveyResponden findByChIdResponden(String idUser);

    @Query(value = RepoQuery.GET_SALDO_REWARD_BY_ID_USER, nativeQuery = true)
    DetailSurveyModel getSaldoReward(String idUser);

}
