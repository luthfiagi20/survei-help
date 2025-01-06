package id.co.app.application.domain.model.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqDataSurvey {
    private String idSurvey;
    private String idUser;
    private String judul;
    private String link;
    private Long jmlResponden;
    private Long jmlKriteria;
    private Long jmlPertanyaan;
    private Long totalBayar;
    private String statusBayar;
    private String tglMulai;
    private String tglSelesai;
    private String kriteriaJnsKelamin;
    private String kriteriaStsKawin;
    private String kriteriaUsia;
    private String kriteriaPendidikan;
    private String kriteriaPekerjaan;
}
