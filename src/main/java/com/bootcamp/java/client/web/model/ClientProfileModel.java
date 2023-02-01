package com.bootcamp.java.client.web.model;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientProfileModel {

    private String id;
    @NotNull
    private String nameProfile;
    @NotNull
    private String clientType;


    private Boolean active;
}
