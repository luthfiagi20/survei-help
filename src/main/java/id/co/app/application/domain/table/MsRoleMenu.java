package id.co.app.application.domain.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ms_role_menu")
public class MsRoleMenu {
    @Id
    private String idMsRoleMenu;
    private String idMsRole;
    @ManyToOne
    @JoinColumn(name = "id_ms_menu")
    private MsMenu menu;
}
