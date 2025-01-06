package id.co.app.application.controller;

import id.co.app.application.domain.model.request.ReqDataUser;
import id.co.app.application.domain.model.request.ReqLogin;
import id.co.app.application.domain.model.ResponseModel;
import id.co.app.application.service.LoginSvc;
import id.co.app.application.service.MasterDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    LoginSvc loginService;

    @Autowired
    MasterDataSvc masterDataSvc;

    @PostMapping("login_check_data")
    public ResponseModel loginCheck(HttpServletRequest request, @RequestBody ReqLogin loginReq) throws ServletException {
        return loginService.loginAction(request, loginReq);
    }

    @PostMapping("simpan-register")
    public ResponseModel simpanRegister(@RequestBody ReqDataUser req) throws ServletException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserAccount user = (UserAccount) authentication.getPrincipal();
        return masterDataSvc.simpanRegister(req.getIdUser(),req);
    }
}
