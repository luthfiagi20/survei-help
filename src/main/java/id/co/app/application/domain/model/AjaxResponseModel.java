package id.co.app.application.domain.model;

import java.math.BigInteger;

/**
 * Created by David King on 1/18/2016.
 */
public class AjaxResponseModel<T> {
    private String message;
    private String code;
    private String title;
    private BigInteger id;
    private T object;

    public AjaxResponseModel() {
    }

    public AjaxResponseModel(String title) {
        this.title = title;
        this.code = "1";
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
