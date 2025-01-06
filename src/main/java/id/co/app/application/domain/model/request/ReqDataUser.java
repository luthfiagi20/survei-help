package id.co.app.application.domain.model.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqDataUser {
    private String idUser;
    private String nama;
    private String notelp;
    private List<String> role;
    private String fgAktif;
    private String roleUser;

}
