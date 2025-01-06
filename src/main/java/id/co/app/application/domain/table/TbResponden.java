package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_responden")
public class TbResponden {
    @Id
    @GeneratedValue(generator = "uuidGen2")
    @GenericGenerator(name = "uuidGen2", strategy = "org.hibernate.id.UUIDGenerator")
    private String chIdRes;
    private String chIdUser;
    private String chEmailRes;
    private String chGenderRes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTglLahirRes;
    private String chKtpRes;
    private String chPendidikanRes;
    private String chPekerjaanRes;
    private Long numPenghasilan_minRes;
    private Long numPenghasilan_maxRes;
    private Long numStsMenikah;
    private Long numJmlAnak;
    private String chAlamatDomisili;
    private Long numProvDomisili;
    private String chAlamatKerja;
    private Long numProvKerja;
    private String chBankRekening;
    private String chNomorRekening;

}
