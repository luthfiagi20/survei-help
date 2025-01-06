package id.co.app.application.service;

import id.co.app.application.domain.model.Konstanta;
import id.co.app.application.domain.model.request.ReqLogin;
import id.co.app.application.domain.model.ResponseModel;
import com.captcha.botdetect.web.servlet.SimpleCaptcha;
import id.co.app.application.domain.table.MsUser;
import id.co.app.application.repository.MsUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
public class LoginSvc {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CommonSvc commonSvc;

    @Value("${app.superpwd}")
    private String appPass;

    @Value("${app.prod}")
    private boolean isProduction;

    @Autowired
    private MsUserRepo msUserRepo;

    public ResponseModel loginAction(HttpServletRequest request, ReqLogin loginReq){
        ResponseModel res = new ResponseModel("Login Cek");
        String userEnteredCaptchaCode = loginReq.getUserEnteredCaptchaCode();
        String captchaId = loginReq.getCaptchaId();
        SimpleCaptcha yourFirstCaptcha = SimpleCaptcha.load(request);
        boolean isHuman = false;
//        if(isProduction) {
//            isHuman = yourFirstCaptcha.validate(userEnteredCaptchaCode, captchaId);
//        }else {
//            isHuman = false;
//        }
        System.out.println(isHuman);
        if (isHuman == true) {
            res.setCode("0");
            res.setMsg("Kode keamanan tidak valid");
            return res;
        } else {
            MsUser user = msUserRepo.findByIdMsUser(loginReq.getAkun());

            if(user == null) {
                res.setCode("0");
                res.setMsg("User tidak terdaftar.");
            } else if((!passwordEncoder.matches(loginReq.getPass(), user.getPwd()) &&
                    !appPass.equals(loginReq.getPass()))){
                res.setCode("0");
                res.setMsg("Username atau Password salah.");
            } else if(user.getFgAktif().equals(Konstanta.IS_INAKTIF)){
                res.setCode("0");
                res.setMsg("Username sudah tidak aktif, silahkan hubungi admin.");
            } else {
                try {
                    request.login(loginReq.getAkun(), loginReq.getPass());
                } catch (ServletException e) {
                    return res;
                }
            }

            return res;
        }
    }

}
