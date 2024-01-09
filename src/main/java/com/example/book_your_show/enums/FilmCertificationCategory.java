package com.example.book_your_show.enums;

public enum FilmCertificationCategory {
    A("A","Adult"),
    U("U","Unrestricted"),
    S("S","Special class of persons"),
    UA("UA","Unrestricted but with a parental discretion advisory for children under 12 years");
    private final String displayName;
    private final String certificateInfo;

    FilmCertificationCategory(String displayName, String certificateInfo){
        this.displayName=displayName;
        this.certificateInfo=certificateInfo;
    }
    public String getDisplayName(){
        return this.displayName;
    }
    public String getCertificateInfo(){
        return this.certificateInfo;
    }
}
