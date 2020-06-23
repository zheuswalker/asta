package com.example.stirl.greenlight;

public class OnlineModel {

    private String itemOnlineID;
    private String itemOnlineTitle;
    private String itemOnlineDesc;
    private String itemLink;

    public OnlineModel() {
    }

    public OnlineModel(String itemOnlineID, String itemOnlineTitle, String itemOnlineDesc, String itemLink) {
        this.itemOnlineID = itemOnlineID;
        this.itemOnlineTitle = itemOnlineTitle;
        this.itemOnlineDesc = itemOnlineDesc;
        this.itemLink = itemLink;
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

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }
}
