package id.co.app.application.controller;

import id.co.app.application.domain.model.Konstanta;
import id.co.app.application.domain.model.ResponseModel;
import id.co.app.application.domain.model.UserAccount;
import id.co.app.application.domain.model.request.ReqDataUser;
import id.co.app.application.service.MasterDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController
@RequestMapping("rest/admin")
public class AdminController {

    @Autowired
    MasterDataSvc masterDataSvc;

    @PostMapping("simpan-register")
    public ResponseModel simpanRegister(@RequestBody ReqDataUser req) throws ServletException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserAccount user = (UserAccount) authentication.getPrincipal();
        System.out.println("TEST"+req.getIdUser());
        return masterDataSvc.simpanRegister(req.getIdUser(),req);
    }

    @PostMapping("simpan-user")
    public ResponseModel simpanPic(@RequestBody ReqDataUser req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataSvc.simpanPenggunaBaru(user.getUsername(),req);
    }

    @PostMapping("update-user")
    public ResponseModel updatePic(@RequestBody ReqDataUser req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataSvc.updateDataPic(user.getUsername(), req);
    }

    @PostMapping("reset-pass-user")
    public ResponseModel resetPassPic(@RequestBody ReqDataUser req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataSvc.resetPass(user.getUsername(), req.getIdUser());
    }

    @GetMapping("get-all-user")
    public ResponseModel getAllPic(){
        return masterDataSvc.getDaftarUser();
    }


}
