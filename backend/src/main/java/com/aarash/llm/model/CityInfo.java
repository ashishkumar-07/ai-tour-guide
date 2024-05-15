package com.aarash.llm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityInfo {
    String city;
    String about;
    String history;
    List<Attraction> attraction; 
    List<String> architecture; 
    List<Attraction> cuisine;
    Location location;
    String weather;
    String gettingAround;
    List<String> moreInformation;

    public record Location (String latitude, String longitude) {
    }

    public record Attraction (String name, String information) {
    }
    public record Cuisine (String name, String information) {
    }
}

