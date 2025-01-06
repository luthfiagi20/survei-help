package id.co.app.application.domain.table;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ms_menu")
public class MsMenu {
    @Id
    @Column(name = "id_ms_menu")
    private Long idMsMenu;
    private String haveChild;
    private String icon;
    private Long idParent;
    private String link;
    private String nama;
    private Long urutanPerLevel;
    private String fgAktif;

}
