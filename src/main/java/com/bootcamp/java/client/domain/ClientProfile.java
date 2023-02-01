package com.bootcamp.java.client.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nonapi.io.github.classgraph.json.Id;

@Data
@Builder
@ToString
@EqualsAndHashCode(of = { "nameProfile" })
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "client-profile")
public class ClientProfile {
	@Id
    private String id;
    
	@NotNull
    @Indexed(unique = true)
    private String nameProfile;
   
	@NotNull
    private String clientType;
	
    @NotNull
    private Boolean active;
}
