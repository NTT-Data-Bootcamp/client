package com.bootcamp.java.client.service.exception;

import com.bootcamp.java.client.domain.dto.ClientTypeEnum;

public class InvalidNameClientException extends Exception {
    private static final long serialVersionUID = 1L;
    public InvalidNameClientException(String clientType) {    	
        super(clientType.trim() == ClientTypeEnum.PERSONNEL.getValue() ? "The client's name is empty" : "The client's business name is empty");
    }
}