package com.jakob.patrol.dto;

import jakarta.validation.constraints.NotBlank;

public class IncidentRequest {

    @NotBlank(message = "Description must not be empty")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}