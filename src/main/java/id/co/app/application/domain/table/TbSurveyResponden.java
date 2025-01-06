package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_survey_responden")
public class TbSurveyResponden {
    @Id
    @GeneratedValue(generator = "uuidGen2")
    @GenericGenerator(name = "uuidGen2", strategy = "org.hibernate.id.UUIDGenerator")
    private String chIdSurveyResponden;
    private String chIdSurveyPeneliti;
    private String chIdResponden;
    private String fgIsiSurvey;
    private Long numTotalBobot;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTglIsiSurvey;
}
