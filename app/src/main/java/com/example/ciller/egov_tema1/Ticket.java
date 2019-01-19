package com.example.ciller.egov_tema1;

public class Ticket {
    private String name;
    private String email;
    private String telephone;
    private String category;
    private String discount;
    private String price;
    private boolean isPhoto;
    private boolean isVideo;
    private boolean isAudio;
    private String total;
    private Integer day;
    private Integer month;
    private Integer year;


    public Ticket(String name, String email, String telephone, String category, String discount, String price, boolean isPhoto, boolean isVideo, boolean isAudio, String total, Integer day, Integer month, Integer year) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.category = category;
        this.discount = discount;
        this.price = price;
        this.isPhoto = isPhoto;
        this.isVideo = isVideo;
        this.isAudio = isAudio;
        this.total = total;
        this.day = day;
        this.month = month;
        this.year = year;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isPhoto() {
        return isPhoto;
    }

    public void setPhoto(boolean photo) {
        isPhoto = photo;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public boolean isAudio() {
        return isAudio;
    }

    public void setAudio(boolean audio) {
        isAudio = audio;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
