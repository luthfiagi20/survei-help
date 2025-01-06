package id.co.app.application.domain.table;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "ms_user")
public class MsUser {
    @Id
    @Column(name = "id_ms_user")
    private String idMsUser;
    private String nama;
    private String pwd;
    private String telp;
    private String role;
    private String fgAktif;
    private String lastLoginIp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;
    @Transient
    public String getLastLogin() {
        if (lastLoginTime != null) {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(lastLoginTime);
        }else{
            return "";
        }
    }
}
