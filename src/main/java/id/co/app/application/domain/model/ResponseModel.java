package id.co.app.application.domain.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private String title;
    private String code;
    private String msg;
    private String errorTrace;
    private T object;

    public ResponseModel(String title){
        this.title = title;
        this.code = "1";
        this.msg = "Sukses";
    }
}
