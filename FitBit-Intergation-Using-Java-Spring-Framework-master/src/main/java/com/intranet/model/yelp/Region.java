package com.intranet.model.yelp;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by sindhya on 11/1/16.
 */
public class Region {

    @JsonProperty("span")
    private Map<String,String> span;

    @JsonProperty("center")
    private Map<String,String> center;

    public Map<String, String> getSpan() {
        return span;
    }

    public void setSpan(Map<String, String> span) {
        this.span = span;
    }

    public Map<String, String> getCenter() {
        return center;
    }

    public void setCenter(Map<String, String> center) {
        this.center = center;
    }
}
