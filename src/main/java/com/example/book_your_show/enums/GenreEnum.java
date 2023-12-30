package com.example.book_your_show.enums;

public enum GenreEnum {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    POLITICAL("Political"),
    ROMANTIC("Romantic"),
    SCI_FI("Sci-Fi"),
    SOCIAL("Social"),
    SUPERNATURAL("Supernatural"),
    THRILLER("Thriller");
    private final String displayName;
    GenreEnum(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return displayName;
    }
}
