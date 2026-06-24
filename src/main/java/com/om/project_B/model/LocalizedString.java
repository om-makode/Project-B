package com.om.project_B.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class LocalizedString {
    
    @JsonProperty("en")
    private String en;
    
    @JsonProperty("mr")
    private String mr;
    
    @JsonProperty("hi")
    private String hi;

    // CRITICAL: Jackson requires a no-arguments constructor
    public LocalizedString() {}

    public LocalizedString(String en, String mr, String hi) {
        this.en = en;
        this.mr = mr;
        this.hi = hi;
    }

    public String getEn() { return en; }
    public void setEn(String en) { this.en = en; }

    public String getMr() { return mr; }
    public void setMr(String mr) { this.mr = mr; }

    public String getHi() { return hi; }
    public void setHi(String hi) { this.hi = hi; }
}