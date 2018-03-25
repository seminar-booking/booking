package com.example.member.log.entity;

import tech.sollabs.gjall.ApiLog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tbl_api_logs")
public class ApiRequest implements Serializable {

    @Id
    private UUID id;

    @Column(length = 200, nullable = false)
    private String uri;

    @Column(length = 7, nullable = false)
    private String method;

    @Column(nullable = false)
    private int httpStatus;

    @Column(length = 500)
    private String requestHeader;

    @Column(length = 1000)
    private String requestBody;

    @Column(length = 500)
    private String responseHeader;

    @Column(length = 3000)
    private String responseBody;

    @Column
    private long requestAcceptedAt;

    @Column
    private long requestFinishedAt;

    public ApiRequest() {
    }

    public ApiRequest(ApiLog apiLog) {
        this.id = apiLog.getId();
        this.uri = apiLog.getUri();
        this.method = apiLog.getMethod();
        this.httpStatus = apiLog.getHttpStatus();
        this.requestHeader = apiLog.getRequestHeader();
        this.requestBody = apiLog.getRequestBody();
        this.responseHeader = apiLog.getResponseHeader();
        this.responseBody = apiLog.getResponseBody();
        this.requestAcceptedAt = apiLog.getRequestAcceptedAt();
        this.requestFinishedAt = apiLog.getRequestFinishedAt();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public long getRequestAcceptedAt() {
        return requestAcceptedAt;
    }

    public void setRequestAcceptedAt(long requestAcceptedAt) {
        this.requestAcceptedAt = requestAcceptedAt;
    }

    public long getRequestFinishedAt() {
        return requestFinishedAt;
    }

    public void setRequestFinishedAt(long requestFinishedAt) {
        this.requestFinishedAt = requestFinishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiRequest that = (ApiRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
