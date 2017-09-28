package com.intranet.model.yelp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sindhya on 11/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessRegion {

    @JsonProperty("region")
    Region region;

    @JsonProperty("businesses")
    List<Business> businessList;


    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }
}
