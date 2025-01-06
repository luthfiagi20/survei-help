package id.co.app.application.domain.model;

import id.co.app.application.domain.table.MsMenu;
import lombok.Data;

import java.util.List;

@Data
public class UserAccount {
    private String username;
    private String displayName;
    private String nama;
    private String foto;
    private List<String> roles;
    private List<MsMenu> menus;
    private String rolePengguna;
}
