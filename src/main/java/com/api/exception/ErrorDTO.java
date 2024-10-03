package com.api.exception;

import java.util.Date;

public class ErrorDTO {
    private String message;
    private Date date;
    private String uri;

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getUri() {
        return uri;
    }

    public ErrorDTO(String message, Date date, String uri) {
        this.message = message;
        this.date = date;
        this.uri = uri;
    }
}
