package com.uaihebert.uaimockserver.dto.model;

import java.util.List;

public class UaiResponseDTO {
    private int statusCode;

    private String body;
    private String contentType;

    private List<UaiHeaderDTO> headerList;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public List<UaiHeaderDTO> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(final List<UaiHeaderDTO> headerList) {
        this.headerList = headerList;
    }
}