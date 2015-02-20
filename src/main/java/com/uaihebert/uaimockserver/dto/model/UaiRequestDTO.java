package com.uaihebert.uaimockserver.dto.model;

import java.util.List;

public class UaiRequestDTO {
    private String path;
    private String method;
    private String requiredContentType;

    private long holdRequestInMilli;

    private boolean isBodyRequired;

    private List<UaiHeaderDTO> requiredHeaderList;
    private List<UaiQueryParamDTO> requiredQueryParamList;

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getRequiredContentType() {
        return requiredContentType;
    }

    public void setRequiredContentType(final String requiredContentType) {
        this.requiredContentType = requiredContentType;
    }

    public long getHoldRequestInMilli() {
        return holdRequestInMilli;
    }

    public void setHoldRequestInMilli(final long holdRequestInMilli) {
        this.holdRequestInMilli = holdRequestInMilli;
    }

    public boolean isBodyRequired() {
        return isBodyRequired;
    }

    public void setBodyRequired(final boolean isBodyRequired) {
        this.isBodyRequired = isBodyRequired;
    }

    public List<UaiHeaderDTO> getRequiredHeaderList() {
        return requiredHeaderList;
    }

    public void setRequiredHeaderList(final List<UaiHeaderDTO> requiredHeaderList) {
        this.requiredHeaderList = requiredHeaderList;
    }

    public List<UaiQueryParamDTO> getRequiredQueryParamList() {
        return requiredQueryParamList;
    }

    public void setRequiredQueryParamList(final List<UaiQueryParamDTO> requiredQueryParamList) {
        this.requiredQueryParamList = requiredQueryParamList;
    }
}