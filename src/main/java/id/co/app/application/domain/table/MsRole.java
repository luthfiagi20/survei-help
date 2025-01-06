package id.co.app.application.domain.table;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "ms_role")
public class MsRole {
    @Id
    @Column(name = "id_ms_role")
    private String idMsRole;
    private String namaRole;
}
