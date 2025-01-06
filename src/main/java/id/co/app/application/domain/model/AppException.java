package id.co.app.application.domain.model;

import lombok.Data;

@Data
public class AppException extends RuntimeException {
    private int level;
    private String title;
    private Exception carriedException;

    public AppException(String message, Exception e){

        super(message);
        this.title = "Oops.. ada error nih..";
        this.carriedException = e;
    }

    public AppException(String message){

        super(message);
        this.title = "Oops.. ada error nih..";
    }

}
