package com.example.core.log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RequestLog implements Serializable {

    private static final SimpleDateFormat REQUEST_ID_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    private String id;
    private String uri;
    private String method;
    private int httpStatus;
    private String sessionId;
    private String client;
    private String requestHeader;
    private String requestBody;
    private String responseBody;
    private String user;

    public RequestLog() {
        this.id = REQUEST_ID_DATE_FORMAT.format(
                new Date()) + "_" + UUID.randomUUID().toString().substring(19).replace("-", "");
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void addQueryString(String queryString) {
        this.uri += "?";
        this.uri += queryString;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
                "id='" + id + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", httpStatus=" + httpStatus +
                ", sessionId='" + sessionId + '\'' +
                ", client='" + client + '\'' +
                ", requestHeader='" + requestHeader + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
