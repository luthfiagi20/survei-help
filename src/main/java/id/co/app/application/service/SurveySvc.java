package id.co.app.application.service;

import id.co.app.application.domain.model.*;
import id.co.app.application.domain.model.request.ReqDataResponden;
import id.co.app.application.domain.model.request.ReqDataReward;
import id.co.app.application.domain.model.request.ReqDataSurvey;
import id.co.app.application.domain.table.*;
import id.co.app.application.repository.*;
import id.co.app.application.repository.repoModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SurveySvc {
    @Autowired
    LogSvc logSvc;

    @Autowired
    TbSurveyPenelitiRepo tbSurveyPenelitiRepo;

    @Autowired
    TbRewardRepo tbRewardRepo;

    @Autowired
    TbSurveyRespondenRepo tbSurveyRespondenRepo;

    @Autowired
    MsUserRepo msUserRepo;

    private static String UPLOADED_FOLDER = "C://temp//";

    @Transactional
    public ResponseModel simpanDataSurvey(String idUser, ReqDataSurvey dataSurvey) {
        ResponseModel res = new ResponseModel("Simpan Data Survei");
/*        MsUser user = msUserRepo.findByIdMsUser(idUser);
        if(user == null){
            throw new AppException("Responden tidak ditemukan");
        }
        user.setTelp(dataUser.getNoTelp());
        msUserRepo.save(user);*/

        //insert ke tabel survey

        TbSurveyPeneliti tbSrv = new TbSurveyPeneliti();
        tbSrv.setChIdSurveyPeneliti(idUser);
        tbSrv.setChIdPeneliti(idUser);
        tbSrv.setChJudulSurvey(dataSurvey.getJudul());
        tbSrv.setChLinkSurvey(dataSurvey.getLink());
        tbSrv.setNumIdJmlRes(dataSurvey.getJmlResponden());
        tbSrv.setNumIdJmlPertanyaan(dataSurvey.getJmlPertanyaan());
        tbSrv.setChKriteria_1(dataSurvey.getKriteriaJnsKelamin());
        tbSrv.setChKriteria_2(dataSurvey.getKriteriaStsKawin());
        tbSrv.setChKriteria_3(dataSurvey.getKriteriaUsia());
        tbSrv.setChKriteria_4(dataSurvey.getKriteriaPendidikan());
        tbSrv.setChKriteria_5(dataSurvey.getKriteriaPekerjaan());
        tbSrv.setChStatusBayar("0");
        tbSrv.setNumTotalBayar(dataSurvey.getTotalBayar());
        tbSrv.setDtTglMulai(new Date(dataSurvey.getTglMulai()));
        tbSrv.setDtTglSelesai(new Date(dataSurvey.getTglSelesai()));
        tbSurveyPenelitiRepo.save(tbSrv);

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, idUser, res.getTitle());
        res.setObject(idUser);
        return res;
    }

    public ResponseModel getDataSurvey(String idUser) {
        ResponseModel res = new ResponseModel("Get data Responden");
        List<SurveyModel> dtSurvey = tbSurveyPenelitiRepo.getSurveyByIdUser(idUser);
        res.setObject(dtSurvey);
        return res;
    }

    public ResponseModel updatePembayaran(String idUser, ReqDataSurvey dataSurvey) {
        ResponseModel res = new ResponseModel("Simpan Data Survei");
        TbSurveyPeneliti srv = tbSurveyPenelitiRepo.findByChIdSurveyPeneliti(dataSurvey.getIdSurvey());
        if(srv == null){
            throw new AppException("Survey tidak ditemukan");
        }
        srv.setChStatusBayar(dataSurvey.getStatusBayar());
        tbSurveyPenelitiRepo.save(srv);

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, idUser, res.getTitle());
        res.setObject(idUser);
        return res;
    }

    //CUSTOMER SERVICE
    public ResponseModel getDataPembayaran(String statusBayar) {
        ResponseModel res = new ResponseModel("Get data Responden");
        System.out.println("status "+statusBayar);
        List<SurveyModel> dtSurvey = tbSurveyPenelitiRepo.getSurveyByStsBayar(statusBayar);
        res.setObject(dtSurvey);
        return res;
    }

    public ResponseModel getDaftarDetailSurvey(String idSurvey) {
        ResponseModel res = new ResponseModel("Get data Responden");
        List<ProfilRespondenModel> dtSurvey = tbSurveyPenelitiRepo.getDaftarDetailSurvey(idSurvey);
        res.setObject(dtSurvey);
        return res;
    }

    public ResponseModel getDaftarPmhPencairanReward(String statusBayar) {
        ResponseModel res = new ResponseModel("Get data Responden");
        System.out.println("status "+statusBayar);
        List<RewardModel> dtSurvey = tbRewardRepo.getPmhPencairanReward(statusBayar);
        res.setObject(dtSurvey);
        return res;
    }

    //RESPONDEN
    public ResponseModel getDaftarSurvey(String idUser) {
        ResponseModel res = new ResponseModel("Get data Responden");
        System.out.println("status "+idUser);
        List<IsiSurveyModel> dtSurvey = tbSurveyPenelitiRepo.getDaftarSurvey(idUser);
        res.setObject(dtSurvey);
        return res;
    }

    public ResponseModel simpanIsiSurvey(String idUser, ReqDataSurvey dataSurvey) {
        ResponseModel res = new ResponseModel("Simpan Data Survei");
        TbSurveyResponden srv = tbSurveyRespondenRepo.findByChIdRespondenAndAndChIdSurveyPeneliti(idUser, dataSurvey.getIdSurvey());
        if(srv != null){
            throw new AppException("Survey telah di isi");
        }

        TbSurveyResponden tbSrv = new TbSurveyResponden();
        tbSrv.setChIdSurveyPeneliti(dataSurvey.getIdSurvey());
        tbSrv.setChIdResponden(idUser);
        tbSrv.setFgIsiSurvey("1");
        tbSrv.setNumTotalBobot(dataSurvey.getTotalBayar());
        tbSrv.setDtTglIsiSurvey(new Date());
        tbSurveyRespondenRepo.save(tbSrv);

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, idUser, res.getTitle());
        res.setObject(idUser);
        return res;
    }

    public AjaxResponseModel ubahLampiran(MultipartFile uplFile, LampiranModel lampiranModel, String nip) throws AppException, ParseException {
        AjaxResponseModel result = new AjaxResponseModel();
        //result.setCode("1");
        //result.setTitle("Simpan Surat Masuk");

        try {
            if (!uplFile.isEmpty()) {
                String fileName = uplFile.getOriginalFilename().replaceAll(
                        "\\s+", "");

                String[] arrfile = fileName.split("\\.");
                int psExt = (arrfile.length - 1);

                SimpleDateFormat convTgl = new SimpleDateFormat(
                        "ddMMyyyyHHmmss");

                String tgl = convTgl.format(new Date());
                String fileName2 = arrfile[0].replaceAll("\\W", "") + "-" + tgl + "." + arrfile[psExt];
                /*System.out.println("file name 2 :" + fileName2);
                if (!direktori.exists())
                    direktori.mkdir();

                File serverFile = new File(direktori.getAbsolutePath()
                        + System.getProperty("file.separator") + fileName2);

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));

                stream.write(bytes);
                stream.close();

                result.setCode("1");
                result.setMessage(fileName);

                String path = direktori.getAbsolutePath()
                        + System.getProperty("file.separator") + fileName2;*/

                // upload dok baru

                byte[] bytes = uplFile.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER +fileName2);
                Files.write(path, bytes);

                String path2 = fileName2;

                /*redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");

                String path = Constanta.HCP_PATH + fileName2;
                HttpResponse writeRes = hcpService.writeHcp(path, uplFile);
                if (writeRes.getStatusLine().getStatusCode() == 201) { // upload dok baru sukses*/



                    TbSurveyPeneliti lampiran = tbSurveyPenelitiRepo.getOne(lampiranModel.getIdSurvey());
                    System.out.println("nama lampiran : " + lampiranModel.getIdSurvey());
//                    String pathLama = lampiran.getPath();

                 lampiran.setPath(path2);
                 lampiran.setChStatusBayar("1");

                tbSurveyPenelitiRepo.save(lampiran);

                    // hapus dok lama
                   /* HttpResponse delRes = hcpService.delete(pathLama);
                    if (delRes.getStatusLine().getStatusCode() != 200) {
                        System.out.println("HCP Delete Code : "
                                + delRes.getStatusLine().getStatusCode() + " "
                                + delRes.getStatusLine().getReasonPhrase());
                        System.out.println("Gagal hapus file : " + pathLama);
                    }*/

                } else {
                    // upload dok baru gagal
                    throw new Exception("HCP Write Code : "
                            /*+ writeRes.getStatusLine().getStatusCode() + " "
                            + writeRes.getStatusLine().getReasonPhrase()*/
                    );
                }



        } catch (Exception e) {
            //  logger.error("ERROR-DAO : ", e);
            //System.out.println(e);
            result.setCode("ERROR");
            result.setMessage("Ada Masalah Pada Pemrosesan Data");


            //error upload
           // LOGGER.error("Error upload", e);
            e.printStackTrace();
        }


        result.setCode("1");
        //result.setTitle("Get Data Surat Tugas");
        //result.setObject(tablesOutput);

        return result;
    }

    public ResponseModel getDataAllSurvey(String idSurvey){
        ResponseModel res = new ResponseModel("Get data Survey");
        DetailSurveyModel srv = tbSurveyPenelitiRepo.findDetailSurvey(idSurvey);

        if(srv == null){
            throw new AppException("Silahkan dilengkapi data profil");
        }
        res.setObject(srv);
        return res;
    }

    public ResponseModel getDaftarReward(String idUser) {
        ResponseModel res = new ResponseModel("Get data Responden");
        System.out.println("status "+idUser);
        List<IsiSurveyModel> dtSurvey = tbSurveyRespondenRepo.getDaftarReward(idUser);
        res.setObject(dtSurvey);
        return res;
    }

    public ResponseModel getDaftarPencairanReward(String idUser) {
        ResponseModel res = new ResponseModel("Get data Responden");
        System.out.println("status "+idUser);
        List<RewardModel> dtSurvey = tbSurveyRespondenRepo.getDaftarPencairanReward(idUser);
        res.setObject(dtSurvey);
        return res;
    }

    public ResponseModel getSaldoReward(String idUser){
        ResponseModel res = new ResponseModel("Get data PIC");
        DetailSurveyModel user = tbSurveyRespondenRepo.getSaldoReward(idUser);
        if(user == null){
            throw new AppException("PIC tidak ditemukan");
        }
        res.setObject(user);
        return res;
    }

    @Transactional
    public ResponseModel simpanPencarianReward(String idUser, ReqDataReward dataReward){
        ResponseModel res = new ResponseModel("Simpan Permohonan Pencairan Reward");
        MsUser user = msUserRepo.findByIdMsUser(idUser);
        if(user == null){
            throw new AppException("Responden tidak ditemukan");
        }

        SurveyModel tbRes = tbRewardRepo.findStatusPermohonan(idUser);
        System.out.println("NILAI " + tbRes.getStatusBayar());
        if(!tbRes.getStatusBayar().equals("0")){
            throw new AppException("Sedang Ada Permohonan Pencairan Reward Yang Sedang Diajukan");
        }else {
            TbReward tbRew = new TbReward();
            tbRew.setChIdResponden(idUser);
            tbRew.setNumNilai(dataReward.getNilai());
            tbRew.setChStatusPencairan("1");
            tbRew.setDtTglPermohonan(new Date());
            tbRewardRepo.save(tbRew);
        }

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }
}
