package com.example.book_your_show.enums;

public enum Format {
    TWO_DIMENSION("2D"),
    THREE_DIMENSION("3D"),
    FOURTH_DIMENSION("4DX");
    private final String displayName;
    Format(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return displayName;
    }

}
