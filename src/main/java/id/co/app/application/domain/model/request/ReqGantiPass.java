package id.co.app.application.domain.model.request;

import lombok.Data;

@Data
public class ReqGantiPass {
    private String passOld;
    private String passNew;
}
