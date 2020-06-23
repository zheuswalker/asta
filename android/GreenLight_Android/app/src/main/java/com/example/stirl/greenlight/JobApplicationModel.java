package com.example.stirl.greenlight;

public class JobApplicationModel {

    private String itemOnlineID;
    private String itemOnlineTitle;
    private String itemOnlineDesc;
    private String itemLink;
    private String rja_status;

    public JobApplicationModel() {
    }

    public JobApplicationModel(String itemOnlineID, String itemOnlineTitle, String itemOnlineDesc, String itemLink,String rja_status) {
        this.itemOnlineID = itemOnlineID;
        this.itemOnlineTitle = itemOnlineTitle;
        this.itemOnlineDesc = itemOnlineDesc;
        this.itemLink = itemLink;
        this.rja_status = rja_status;
    }

    public String getItemOnlineID() {
        return itemOnlineID;
    }

    public void setItemOnlineID(String itemOnlineID) {
        this.itemOnlineID = itemOnlineID;
    }

    public String getItemOnlineTitle() {
        return itemOnlineTitle;
    }

    public void setItemOnlineTitle(String itemOnlineTitle) {
        this.itemOnlineTitle = itemOnlineTitle;
    }

    public String getItemOnlineDesc() {
        return itemOnlineDesc;
    }

    public void setItemOnlineDesc(String itemOnlineDesc) {
        this.itemOnlineDesc = itemOnlineDesc;
    }

    public String getItemLink() {
        return itemLink;
    }
    public String rja_status() {
        return rja_status;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }
}
