package com.example.book_your_show.enums;

public enum ProfessionEnum {
    ACTOR("Actor"),
    ACTRESS("Actress"),
    CINEMATOGRAPHER("Cinematographer"),
    DIRECTOR("Director"),
    EDITOR("Editor"),
    MUSICIAN("Musician"),
    PRODUCER("Producer"),
    SINGER("Singer"),
    WRITER("Writer");
    private final String displayName;
    ProfessionEnum(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }
}
