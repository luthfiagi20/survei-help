package id.co.app.application.controller;

import id.co.app.application.domain.model.*;
import id.co.app.application.domain.model.request.ReqDataResponden;
import id.co.app.application.domain.model.request.ReqDataReward;
import id.co.app.application.domain.model.request.ReqDataSurvey;
import id.co.app.application.domain.table.TbSurveyPeneliti;
import id.co.app.application.repository.TbSurveyPenelitiRepo;
import id.co.app.application.repository.TbSurveyRespondenRepo;
import id.co.app.application.repository.repoModel.SurveyModel;
import id.co.app.application.service.LogSvc;
import id.co.app.application.service.MasterDataSvc;
import id.co.app.application.service.SurveySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;

@RestController
@RequestMapping("rest/survey")
public class SurveyController {
    @Autowired
    SurveySvc surveySvc;

    @Autowired
    LogSvc logSvc;

    @Autowired
    TbSurveyPenelitiRepo tbSurveyPenelitiRepo;

    @Autowired
    TbSurveyRespondenRepo tbSurveyRespondenRepo;

    //PENELITI
    @PostMapping("simpan-survey")
    public ResponseModel simpanSurvei(@RequestBody ReqDataSurvey req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.simpanDataSurvey(user.getUsername(), req);
    }

    @GetMapping("get-data-survey")
    public ResponseModel getDataSurvey(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.getDataSurvey(user.getUsername());
    }

/*    @PostMapping("update-pembayaran")
    public ResponseModel updatePembayaran(@RequestBody ReqDataSurvey req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.updatePembayaran(user.getUsername(), req);
    }*/

    @PostMapping("validasi-pembayaran")
    public ResponseModel updatePembayaran(@RequestBody ReqDataSurvey req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.updatePembayaran(user.getUsername(), req);
    }

    @RequestMapping(value = "update-pembayaran", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxResponseModel uploadLampiran(@RequestParam("uplFile") MultipartFile uplFile,
                                     @RequestParam(value = "txtPbyIdSurvey", required = false) String idSurvey,
                                     @RequestParam(value = "txtTotalPembayaran", required = false) String statusBayar)
            throws AppException, ParseException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
System.out.println(user.getUsername());
        AjaxResponseModel ajaxResponseModel;

//        ResponseModel res = new ResponseModel("Simpan Data Survei");
        AjaxResponseModel res = new AjaxResponseModel<>();
        TbSurveyPeneliti srv = tbSurveyPenelitiRepo.findByChIdSurveyPeneliti(idSurvey);

        if(srv.getChStatusBayar() == "2"){
            throw new AppException("Survey Sudah Lunas dan Sudah Di Validasi");
        }

        LampiranModel lamp = new LampiranModel();
        lamp.setIdSurvey(idSurvey);
        lamp.setStatusBayar(statusBayar);

        ajaxResponseModel = surveySvc.ubahLampiran(uplFile, lamp, user.getUsername());
        /*srv.setChStatusBayar(statusBayar);
        tbSurveyPenelitiRepo.save(srv);*/

        /*logSvc.simpanLog(user.getUsername(), Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, user.getUsername(), res.getTitle());
        res.setObject(user.getUsername());*/
//        return res;

        return ajaxResponseModel;
    }

    //CUSTOMER SERVICE
    @GetMapping("get-data-pembayaran")
    public ResponseModel getDataPembayaran(@RequestParam String statusBayar){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.getDataPembayaran(statusBayar);
    }

    @GetMapping("get-daftar-detail-survey")
    public ResponseModel getDaftarDetailSurvey(@RequestParam String idSurvey){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.getDaftarDetailSurvey(idSurvey);
    }

    @GetMapping("get-daftar-pmh-pencairan-reward")
    public ResponseModel getDaftarPmhPencairanReward(@RequestParam String statusBayar){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.getDaftarPmhPencairanReward(statusBayar);
    }

    //RESPONDEN
    @GetMapping("get-daftar-survey")
    public ResponseModel getDaftarSurvey(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
/*        String idUser = "nova";
        System.out.println("USER = " + idUser);*/
        return surveySvc.getDaftarSurvey(user.getUsername());
    }

    @PostMapping("simpan-isi-survey")
    public ResponseModel simpanIsiSurvey(@RequestBody ReqDataSurvey req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.simpanIsiSurvey(user.getUsername(), req);
    }

    @GetMapping("get-data-all-survey")
    public ResponseModel getDataAllSurvey(@RequestParam String idSurvey){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.getDataAllSurvey(idSurvey);
    }

    @GetMapping("get-daftar-reward")
    public ResponseModel getDaftarReward(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
/*        String idUser = "nova";
        System.out.println("USER = " + idUser);*/
        return surveySvc.getDaftarReward(user.getUsername());
    }

    @GetMapping("get-daftar-pencairan-reward")
    public ResponseModel getDaftarPencairanReward(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
/*        String idUser = "nova";
        System.out.println("USER = " + idUser);*/
        return surveySvc.getDaftarPencairanReward(user.getUsername());
    }

    @GetMapping("get-saldo-reward")
    public ResponseModel getSaldoReward(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.getSaldoReward(user.getUsername());
    }

    @PostMapping("simpan-permohonan-pencarian")
    public ResponseModel simpanPermohonanReward(@RequestBody ReqDataReward req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return surveySvc.simpanPencarianReward(user.getUsername(), req);
    }
}
