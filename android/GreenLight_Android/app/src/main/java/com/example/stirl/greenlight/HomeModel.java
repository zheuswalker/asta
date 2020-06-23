package com.example.stirl.greenlight;

public class HomeModel {

    private String itemHomeID;
    private String itemHomeTitle;
    private String itemHomeDesc;

    public HomeModel() {
    }

    public HomeModel(String itemHomeID, String itemHomeTitle, String itemHomeDesc) {
        this.itemHomeID = itemHomeID;
        this.itemHomeTitle = itemHomeTitle;
        this.itemHomeDesc = itemHomeDesc;
    }

    public String getItemHomeID() {
        return itemHomeID;
    }

    public void setItemHomeID(String itemHomeID) {
        this.itemHomeID = itemHomeID;
    }

    public String getItemHomeTitle() {
        return itemHomeTitle;
    }

    public void setItemHomeTitle(String itemHomeTitle) {
        this.itemHomeTitle = itemHomeTitle;
    }

    public String getItemHomeDesc() {
        return itemHomeDesc;
    }

    public void setItemHomeDesc(String itemHomeDesc) {
        this.itemHomeDesc = itemHomeDesc;
    }
}
