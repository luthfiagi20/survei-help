package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "dat_log")
public class DatLog {
    @Id
    @GeneratedValue(generator = "uuidGen")
    @GenericGenerator(name = "uuidGen", strategy = "org.hibernate.id.UUIDGenerator")
    private String idDatLog;
    private String idMsUser;
    private String aksi;
    private String tableTerdampak;
    private String idData;
    private String ip;
    private String ket;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
