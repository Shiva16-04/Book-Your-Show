package com.example.book_your_show.enums;

public enum FilmCertificationCategory {
    A("Adult"),
    U("Unrestricted"),
    S("Special class of persons"),
    UA("Unrestricted but with a parental discretion advisory for children under 12 years");
    private final String certificateInfo;
    FilmCertificationCategory(String certificateInfo){
        this.certificateInfo=certificateInfo;
    }
    public String getCertificateInfo(){
        return this.certificateInfo;
    }
}
