package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_reward")
public class TbReward {
    @Id
    @GeneratedValue(generator = "uuidGen2")
    @GenericGenerator(name = "uuidGen2", strategy = "org.hibernate.id.UUIDGenerator")
    private String chIdReward;
    private String chIdResponden;
    private Long numNilai;
    private String chStatusPencairan;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTglPermohonan;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTglPersetujuan;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTglPencairan;
    private String chIdCs;
    private String chKeterangan;
}
