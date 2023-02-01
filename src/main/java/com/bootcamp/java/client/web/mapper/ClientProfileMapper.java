package com.bootcamp.java.client.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bootcamp.java.client.domain.ClientProfile;
import com.bootcamp.java.client.web.model.ClientProfileModel;

@Mapper(componentModel = "spring")
public interface ClientProfileMapper {

	ClientProfile modelToEntity(ClientProfileModel model);

	ClientProfileModel entityToModel(ClientProfile event);

	@Mapping(target = "id", ignore = true)
	void update(@MappingTarget ClientProfile entity, ClientProfile updateEntity);
}

