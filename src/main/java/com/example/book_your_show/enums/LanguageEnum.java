package com.example.book_your_show.enums;

public enum LanguageEnum {
    BENGALI("Bengali"),
    ENGLISH("English"),
    HINDI("Hindi"),
    KANNADA("Kannada"),
    MALAYALAM("Malayalam"),
    TAMIL("Tamil"),
    TELUGU("Telugu");
    private final String displayName;
    LanguageEnum(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return displayName;
    }
}
