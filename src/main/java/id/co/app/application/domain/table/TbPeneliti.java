package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_peneliti")
public class TbPeneliti {
    @Id
    @GeneratedValue(generator = "uuidGen2")
    @GenericGenerator(name = "uuidGen2", strategy = "org.hibernate.id.UUIDGenerator")
    private String chIdPeneliti;
    private String chIdUser;
    private String chEmailPeneliti;
    private String chPekerjaanPeneliti;
}
