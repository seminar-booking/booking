package com.example.core.log;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;
        HttpServletResponse responseToUse = response;

        if (isIncludePayload() && isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request, getMaxPayloadLength());
            responseToUse = new ContentCachingResponseWrapper(response);
        }

        boolean shouldLog = shouldLog(requestToUse);
        RequestLog requestLog = null;

        if (shouldLog && isFirstRequest) {
            requestLog = getBeforeMessage(requestToUse);
            beforeRequest(requestToUse, requestLog.toString());
        }
        try {
            filterChain.doFilter(requestToUse, response);
        }
        finally {
            if (shouldLog && !isAsyncStarted(requestToUse)) {
                requestLog = getAfterMessage(responseToUse, requestLog);
                afterRequest(requestToUse, requestLog.toString());
            }
        }
    }

    private RequestLog getBeforeMessage(HttpServletRequest request) {

        RequestLog requestLog = new RequestLog();

        requestLog.setUri(request.getRequestURI());
        requestLog.setMethod(request.getMethod());

        if (isIncludeHeaders()) {
            requestLog.setRequestHeader(new ServletServerHttpRequest(request).getHeaders().toString());
        }

        if (isIncludeQueryString()) {
            String queryString = request.getQueryString();
            if (queryString != null) {
                requestLog.addQueryString(queryString);
            }
        }

        if (isIncludeClientInfo()) {
            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)) {
                requestLog.setClient(client);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                requestLog.setSessionId(session.getId());
            }
            String user = request.getRemoteUser();
            if (user != null) {
                requestLog.setUser(user);
            }
        }

        if (isIncludePayload()) {
            ContentCachingRequestWrapper wrapper =
                    WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    String payload;
                    try {
                        payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    }
                    catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }

                    requestLog.setRequestBody(payload);
                }
            }
        }

        return requestLog;
    }

    private RequestLog getAfterMessage(HttpServletResponse response, RequestLog beForeRequestLog) {

        RequestLog requestLog = beForeRequestLog;

        if (requestLog == null) {
            requestLog = new RequestLog();
        }

        requestLog.setHttpStatus(response.getStatus());

        if (isIncludePayload()) {
            ContentCachingResponseWrapper wrapper =
                    WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    String payload;
                    try {
                        payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    }
                    catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }

                    requestLog.setResponseBody(payload);
                }
            }
        }

        return requestLog;
    }

}
