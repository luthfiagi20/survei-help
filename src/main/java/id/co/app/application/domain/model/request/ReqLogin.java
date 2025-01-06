package id.co.app.application.domain.model.request;

import lombok.Data;

@Data
public class ReqLogin {
    private String akun;
    private String pass;
    private String captchaId;
    private String userEnteredCaptchaCode;
}
