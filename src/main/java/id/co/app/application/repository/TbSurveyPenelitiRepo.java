package id.co.app.application.repository;

import id.co.app.application.domain.table.TbResponden;
import id.co.app.application.domain.table.TbSurveyPeneliti;
import id.co.app.application.repository.repoModel.DetailSurveyModel;
import id.co.app.application.repository.repoModel.IsiSurveyModel;
import id.co.app.application.repository.repoModel.ProfilRespondenModel;
import id.co.app.application.repository.repoModel.SurveyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbSurveyPenelitiRepo extends JpaRepository<TbSurveyPeneliti, String> {
/*        @Query(value = RepoQuery.GET_DATA_RESPONDEN, nativeQuery = true)
        ProfilRespondenModel findResponden(String iduser);*/

    TbSurveyPeneliti findByChIdSurveyPeneliti(String chIdSurveyPeneliti);

    TbSurveyPeneliti findByChStatusBayar(String chIdSurveyPeneliti);

    @Query(value = RepoQuery.GET_SURVEY_BY_PENELITI, nativeQuery = true)
    List<SurveyModel> getSurveyByIdUser(String idUser);

    //CUSTOMER SERVICE
    @Query(value = RepoQuery.GET_SURVEY_BY_STATUS_PEMBAYARAN, nativeQuery = true)
    List<SurveyModel> getSurveyByStsBayar(String statusBayar);

    @Query(value = RepoQuery.GET_DAFTAR_DETAIL_SURVEY_BY_ID_SURVEY, nativeQuery = true)
    List<ProfilRespondenModel> getDaftarDetailSurvey(String idSurvey);

    //RESPONDEN
    @Query(value = RepoQuery.GET_DAFTAR_SURVEY_BY_ID_USER, nativeQuery = true)
    //by adhit
    List<IsiSurveyModel> getDaftarSurvey(String idUser);

    @Query(value = RepoQuery.GET_DETAIL_SURVEY_BY_ID_SURVEY, nativeQuery = true)
    DetailSurveyModel findDetailSurvey(String idSurvey);

}
