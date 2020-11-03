package com.example.elastic.dto;

import com.example.elastic.model.CommonSeverity;

public class CommonSeverityDTO {
    private String id;

    private Integer cont;

    public CommonSeverityDTO(CommonSeverity aCommonSeverity) {
        this.setId(aCommonSeverity.getId());
        this.setCont(aCommonSeverity.getCont());
    }

    public String getId() {
        return this.id;
    }

    public void setId(String anId) {
        this.id = anId;
    }

    public Integer getCont() {
        return this.cont;
    }

    public void setCont(Integer aCont) {
        this.cont = aCont;
    }

}
