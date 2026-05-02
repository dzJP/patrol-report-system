package com.jakob.patrol.dto;

import jakarta.validation.constraints.NotBlank;

public class RoundRequest {

    @NotBlank(message = "Location must not be empty")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}