package id.co.app.application.controller;

import id.co.app.application.domain.model.UserAccount;
import id.co.app.application.domain.model.request.ReqDataResponden;
import id.co.app.application.domain.model.request.ReqDataUser;
import id.co.app.application.domain.model.request.ReqGantiPass;
import id.co.app.application.domain.model.ResponseModel;
import id.co.app.application.service.MasterDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController
@RequestMapping("rest/profil")
public class ProfileController {

    @Autowired
    MasterDataSvc masterDataService;

    @PostMapping("simpan-pass")
    public ResponseModel simpanPassword(@RequestBody ReqGantiPass req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataService.simpanPass(user.getUsername(),req);
    }

    @PostMapping("update-akun")
    public ResponseModel updateAkun(@RequestBody ReqDataUser req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataService.updateDataPic(user.getUsername(), req);
    }

    @GetMapping("get-data-akun")
    public ResponseModel getDataAkun(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataService.getDataUser(user.getUsername());
    }

    @GetMapping("get-data-responden")
    public ResponseModel getDataResponden(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataService.getDataResponden(user.getUsername());
    }

    @PostMapping("update-akun-responden")
    public ResponseModel updateAkunResponden(@RequestBody ReqDataResponden req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataService.updateDataResponden(user.getUsername(), req);
    }

    //PENELITI
    @GetMapping("get-data-peneliti")
    public ResponseModel getDataPeneliti(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataService.getDataPeneliti(user.getUsername());
    }

    @PostMapping("update-akun-peneliti")
    public ResponseModel updateAkunPeneliti(@RequestBody ReqDataResponden req) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataService.updateDataPeneliti(user.getUsername(), req);
    }

}
