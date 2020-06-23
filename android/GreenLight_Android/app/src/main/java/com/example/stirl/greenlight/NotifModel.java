package com.example.stirl.greenlight;

public class NotifModel {

    private String certTitle;
    private String certStatus;
    private String certUnits;

    public NotifModel(String certTitle, String certStatus, String certUnits) {
        this.certTitle = certTitle;
        this.certStatus = certStatus;
        this.certUnits = certUnits;
    }

    public String getCertTitle() {
        return certTitle;
    }

    public void setCertTitle(String certTitle) {
        this.certTitle = certTitle;
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }

    public String getCertUnits() {
        return certUnits;
    }

    public void setCertUnits(String certUnits) {
        this.certUnits = certUnits;
    }
}
