package com.blog.permission.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ServerException extends RuntimeException {

    private Integer errorCode;
    private String errorMsg;

    public ServerException(){
        super();
    }

    public ServerException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


}
