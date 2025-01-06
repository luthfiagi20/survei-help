package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ms_user_role")
public class MsUserRole {
    @Id
    @GeneratedValue(generator = "uuidGen2")
    @GenericGenerator(name = "uuidGen2", strategy = "org.hibernate.id.UUIDGenerator")
    private String idMsUserRole;
    private String idMsUser;
    private String idMsRole;
}
