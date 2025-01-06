package id.co.app.application.domain.model;

import lombok.Data;

@Data
public class ImageModel {
    private String base64;
    private String contentType;
    private String fileExt;
}
