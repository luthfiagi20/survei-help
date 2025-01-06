package id.co.app.application.repository;

public class RepoQuery {
    public final static String GET_LIST_USER_ROLES = "select id_ms_role from ms_user_role where id_ms_user = :iduser";
    public final static String GET_MENU_ROLES =
            "select a.menu " +
             "from MsRoleMenu a " +
             "where a.menu.fgAktif = '1' and a.idMsRole in :roles  " +
             "order by a.menu.idParent, a.menu.urutanPerLevel ";

    //Profil Responden
    public final static String GET_DATA_RESPONDEN =
            "SELECT b.id_ms_user idMsUser, b.nama nama, b.telp telepon, a.ch_email_res email, a.ch_gender_res jnsKelamin, TO_CHAR(a.dt_tgl_lahir_res, 'dd/mm/yyyy') tglLahir, " +
                    " a.ch_ktp_res noIdentitas, a.ch_pendidikan_res pendidikan, a.ch_pekerjaan_res pekerjaan, a.num_penghasilan_min_res gajiMin, " +
                    " a.num_penghasilan_max_res gajiMax, a.num_sts_menikah stsKawin, a.num_jml_anak jmlAnak, a.ch_alamat_domisili alamatDomisili, " +
                    " a.num_prov_domisili provinsiDomisili, a.ch_alamat_kerja alamatKerja, a.num_prov_kerja provinsiKerja, " +
                    " a.ch_bank_rekening namaBank, a.ch_nomor_rekening noRekening" +
                    " FROM public.tb_responden a, public.ms_user b " +
                    " WHERE b.id_ms_user=:iduser AND b.id_ms_user=a.ch_id_user";

    //Get List Data Survey by ID PENELITI
    public final static String GET_SURVEY_BY_PENELITI =
    "SELECT ch_id_survey_peneliti IdSurvey, ch_judul_survey judul, num_total_bayar totalBayar, ch_status_bayar statusBayar " +
    " FROM public.tb_survey_peneliti WHERE ch_id_peneliti=:idUser";

    //CUSTOMER SERVICE
    //Get List Data Survey by Status Pembayaran
    public final static String GET_SURVEY_BY_STATUS_PEMBAYARAN =
            "SELECT a.ch_id_survey_peneliti IdSurvey, a.ch_id_peneliti idMsUser, a.ch_judul_survey judul, " +
                    " a.num_total_bayar totalBayar, a.ch_status_bayar statusBayar, a.path path " +
                    " FROM public.tb_survey_peneliti a, public.ms_user b WHERE a.ch_id_peneliti=b.id_ms_user " +
                    " AND a.ch_status_bayar=:statusBayar";

    //Get List DETAIL Data Survey by ID SURVEY
    public final static String GET_DAFTAR_DETAIL_SURVEY_BY_ID_SURVEY =
            "select b.ch_id_user idMsUser, c.nama nama, c.telp telepon " +
                    " from tb_survey_peneliti a, tb_responden b, ms_user c " +
                    " WHERE a.ch_id_survey_peneliti=:idSurvey" +
                    " AND b.ch_id_user = c.id_ms_user " +
                    "AND (a.ch_kriteria_1 = b.ch_gender_res OR " +
                    "(CASE ch_kriteria_1 WHEN '' THEN null ELSE ch_kriteria_1 END) IS NULL) " +
                    "AND (a.ch_kriteria_2 = to_char(b.num_sts_menikah, '9,999') OR " +
                    "(CASE ch_kriteria_2 WHEN '' THEN null ELSE ch_kriteria_2 END) IS NULL)  " +
                    "AND (ch_kriteria_4 = b.ch_pendidikan_res OR " +
                    "(CASE ch_kriteria_4 WHEN '' THEN null ELSE ch_kriteria_4 END) IS NULL) " +
                    "                    AND (ch_kriteria_5 = b.ch_pekerjaan_res OR " +
                    "(CASE ch_kriteria_5 WHEN '' THEN null ELSE ch_kriteria_5 END) IS NULL) " +
                    "AND not EXISTS (SELECT 1 from tb_survey_responden c WHERE c.ch_id_survey_peneliti = a.ch_id_survey_peneliti " +
                    "AND c.ch_id_responden = b.ch_id_user)";

    public final static String GET_DAFTAR_PMH_PENCAIRAN_REWARD =
            "SELECT ch_id_reward idPencairan, ch_id_responden IdResponden, num_nilai nilai, ch_status_pencairan status, ch_keterangan keterangan " +
                    " FROM tb_reward " +
                    " WHERE ch_status_pencairan=:statusBayar";

    //RESPONDEN
    //Get List Data Survey by Status Pembayaran
    public final static String GET_DAFTAR_SURVEY_BY_ID_USER =
            "select a.ch_id_survey_peneliti idSurvey, a.ch_judul_survey judul, a.ch_id_peneliti idPeneliti, a.ch_link_survey linkSurvey, a.dt_tgl_selesai tglSelesai, " +
                    " ((a.num_total_bayar/2)/num_id_jml_res) totalBayar " +
                    " from tb_survey_peneliti a, tb_responden b " +
                    " WHERE b.ch_id_user = :idUser " +
                    " AND CH_STATUS_BAYAR = '2' " +
                    "AND (a.ch_kriteria_1 = b.ch_gender_res OR " +
                    "(CASE ch_kriteria_1 WHEN '' THEN null ELSE ch_kriteria_1 END) IS NULL) " +
                    "AND (a.ch_kriteria_2 = to_char(b.num_sts_menikah, '9,999') OR " +
                    "(CASE ch_kriteria_2 WHEN '' THEN null ELSE ch_kriteria_2 END) IS NULL)  " +
                    "AND (ch_kriteria_4 = b.ch_pendidikan_res OR " +
                    "(CASE ch_kriteria_4 WHEN '' THEN null ELSE ch_kriteria_4 END) IS NULL) " +
                    "                    AND (ch_kriteria_5 = b.ch_pekerjaan_res OR " +
                    "(CASE ch_kriteria_5 WHEN '' THEN null ELSE ch_kriteria_5 END) IS NULL) " +
                    "AND not EXISTS (SELECT 1 from tb_survey_responden c WHERE c.ch_id_survey_peneliti = a.ch_id_survey_peneliti " +
                    "AND c.ch_id_responden = b.ch_id_user)";

    //Get Detail Survey by ID Survey
    public final static String GET_DETAIL_SURVEY_BY_ID_SURVEY =
            "SELECT ch_id_survey_peneliti idSurvey, ch_id_peneliti idPeneliti, ch_judul_survey judul, " +
                    " ch_link_survey link, num_id_jml_res jumlahResponden, " +
                    " ch_kriteria_1 kriteria1, ch_kriteria_2 kriteria2, ch_kriteria_3 kriteria3, " +
                    " ch_kriteria_4 kriteria4, ch_kriteria_5 kriteria5, " +
                    " num_id_jml_pertanyaan jumlahPertanyaan, num_total_bayar totalBayar, " +
                    " ch_status_bayar statusBayar, dt_tgl_mulai tglMulai, dt_tgl_selesai tglSelesai, path path " +
                    " FROM tb_survey_peneliti WHERE ch_id_survey_peneliti=:idSurvey";

    //Get List Data Survey by Status Pembayaran
    public final static String GET_DAFTAR_REWARD_BY_ID_USER =
            "SELECT a.ch_id_survey_peneliti idSurvey, b.ch_judul_survey judul, " +
                    " num_total_bobot totalBayar, dt_tgl_isi_survey tglSelesai " +
                    " FROM tb_survey_responden a, tb_survey_peneliti b " +
                    " WHERE a.ch_id_survey_peneliti = b.ch_id_survey_peneliti " +
                    " AND ch_id_responden=:idUser";

    public final static String GET_DAFTAR_PENCAIRAN_REWARD_BY_ID_USER =
            "SELECT ch_id_reward idPencairan, num_nilai nilai, ch_status_pencairan status, ch_keterangan keterangan " +
                    " FROM tb_reward " +
                    " WHERE ch_id_responden=:idUser";

    //Get List Data Survey by Status Pembayaran
    public final static String GET_SALDO_REWARD_BY_ID_USER =
            "SELECT saldo-nilai totalBayar, y.ch_bank_rekening kriteria1, y.ch_nomor_rekening kriteria2 FROM ( " +
                    "                    SELECT a.ch_id_responden, sum(a.num_total_bobot) saldo, " +
                    "                     (select sum(num_nilai) from tb_reward b WHERE b.ch_id_responden=:idUser " +
                    "                     AND b.ch_status_pencairan='3') nilai " +
                    "                     FROM tb_survey_responden a " +
                    "                     WHERE a.ch_id_responden=:idUser group by a.ch_id_responden) x, tb_responden y " +
                    "WHERE y.ch_id_user=x.ch_id_responden";

    //Get Data Reward
    public final static String GET_STATUS_REWARD =
            "SELECT count(1) statusBayar FROM tb_reward " +
                    " WHERE ch_status_pencairan !='3' " +
                    " AND ch_id_responden=:idUser";

    //PENELITI
    public final static String GET_DATA_PENELITI =
            "SELECT b.id_ms_user idMsUser, b.nama nama, b.telp telepon, a.ch_email_peneliti email, a.ch_pekerjaan_peneliti pekerjaan " +
                    " FROM public.tb_peneliti a, public.ms_user b " +
                    " WHERE b.id_ms_user=:iduser AND b.id_ms_user=a.ch_id_user";

}
