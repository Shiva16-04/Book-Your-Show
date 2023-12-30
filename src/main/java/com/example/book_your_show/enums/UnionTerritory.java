package com.example.book_your_show.enums;

public enum UnionTerritory {
    ANDAMAN_NICOBAR_ISLANDS("Andaman and Nicobar Islands"),
    CHANDIGARH("Chandigarh"),
    DADRA_NAGARHAVELI_DAMANDIU("Dadra and Nagar Haveli and Daman & Diu"),
    NCT_OF_DELHI("NCT of Delhi"),
    JAMMU_KASHMIR("Jammu-Srinagar, Jammu-W"),
    LADAKH("Ladakh"),
    LAKSHADWEEP("Lakshadweep"),
    PUDUCHERRY("Puducherry");
    private final String displayName;
    UnionTerritory(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }
}
