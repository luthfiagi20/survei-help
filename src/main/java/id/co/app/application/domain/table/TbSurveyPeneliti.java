package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_survey_peneliti")
public class TbSurveyPeneliti {
    @Id
    @GeneratedValue(generator = "uuidGen2")
    @GenericGenerator(name = "uuidGen2", strategy = "org.hibernate.id.UUIDGenerator")
    private String chIdSurveyPeneliti;
    private String chIdPeneliti;
    private String chJudulSurvey;
    private String chLinkSurvey;
    private Long numIdJmlRes;
    private Long numIdKriteriaRes;
    private Long numIdJmlPertanyaan;
    private String chKriteria_1;
    private String chKriteria_2;
    private String chKriteria_3;
    private String chKriteria_4;
    private String chKriteria_5;
    private Long numTotalBayar;
    private String chStatusBayar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTglMulai;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTglSelesai;
    private String path;
}
