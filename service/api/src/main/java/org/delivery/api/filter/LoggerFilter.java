package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

/**
 * 로그 출력
 */
@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var request = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var response = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(request, response); //dpfilter를 기준으로 위: 실행전 ,아래: 실행 후

        //request header, body 정보 찍어주는게 best(근데 별도의 class 만들어야 함으로..)

        //request 정보
        var headerNames = request.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey->{
            var headerValue = request.getHeader(headerKey);

            //authorixation-token : ???, user-agent : ???/n
            headerValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        var requestBody = new String(request.getContentAsByteArray());
        var uri = request.getRequestURI();
        var method = request.getMethod();

        log.info(">>>>>>>>uri : {}, method : {}, header : {}, body : {}",uri, method, headerValues, requestBody);

        //response 정보
        var responseHeaderValue = new StringBuilder();
        response.getHeaderNames().forEach(headerKey->{
            var headerValue = request.getHeader(headerKey);
            responseHeaderValue.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");

        });

        var responseBody = new String(response.getContentAsByteArray());
        log.info(">>>>>>>>uri : {}, method : {}, response : {}, body : {}",uri, method, responseBody, responseHeaderValue);

        //response body 이미 읽어서 비워져있으므로, 다시 넣어줘야함!!!!!중요
        response.copyBodyToResponse();

    }
}
