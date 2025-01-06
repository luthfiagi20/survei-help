package id.co.app.application.domain.model.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqDataResponden {
    private String idUser;
    private String nama;
    private String noTelp;
    private String email;
    private String tglLahir;
    private String jnsKelamin;
    private String noIdentitas;
    private String pendidikan;
    private String pekerjaan;
    private Long gajiMin;
    private Long gajiMax;
    private Long stsKawin;
    private Long jmlAnak;
    private String alamatDomisili;
    private Long provinsiDomisili;
    private String alamatKerja;
    private Long provinsiKerja;
    private String namaBank;
    private String noRekening;



}
