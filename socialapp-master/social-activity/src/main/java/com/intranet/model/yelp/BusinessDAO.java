package com.intranet.model.yelp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yelp.YelpAPI;

import org.springframework.web.servlet.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sindhya on 10/31/16.
 */
public class BusinessDAO {

    List<Business> businessList =new ArrayList<>();

    public BusinessDAO(){

    }


    public List list(String location){
        YelpAPI yelpAPI=new YelpAPI();
        String searchResponse= yelpAPI.searchRestaurantByLocation(location);

        BusinessRegion businessRegion=null;
        String outputResponse=null;
        ObjectMapper objectMapper=new ObjectMapper();
        try {

            businessRegion=objectMapper.readValue(searchResponse,BusinessRegion.class);
            //businessRegion=objectMapper
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
            //outputResponse=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(businessRegion);

            businessList=businessRegion.businessList;
            System.out.println("business  :"+businessRegion.businessList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return businessList;
    }
}
