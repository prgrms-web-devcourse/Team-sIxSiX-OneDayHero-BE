package com.sixheroes.onedayheroapi.global.interceptor;

import com.sixheroes.onedayheroapplication.global.jwt.JwtProperties;
import com.sixheroes.onedayheroapplication.global.jwt.JwtTokenManager;
import com.sixheroes.onedayherocommon.error.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;
    private final JwtTokenManager jwtTokenManager;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        // 존재하지 않은 URL 로 접근할 경우.
        if (!(handler instanceof HandlerMethod)) {
            response.setStatus(404);
            return false;
        }

        var authorizationHeader = getAuthorization(request);

        if (validateAuthorizationHeaderIsValid(authorizationHeader)) {
            throw new IllegalStateException(ErrorCode.A_001.name());
        }

        try {
            var id = jwtTokenManager.getId(getAccessToken(authorizationHeader));
            request.setAttribute(jwtProperties.getClaimId(), id);

            return true;
        } catch (MalformedJwtException exception) {
            log.warn("잘못된 형식의 JWT 토큰입니다.");

            throw new IllegalStateException(ErrorCode.T_001.name());
        } catch (ExpiredJwtException exception) {
            log.warn("만료된 JWT 토큰입니다.");

            throw new IllegalStateException(ErrorCode.T_001.name());
        } catch (JwtException exception) {
            log.warn("JWT 토큰 에러 발생");

            throw new IllegalStateException(ErrorCode.T_001.name());
        }
    }

    private boolean validateAuthorizationHeaderIsValid(String header) {
        return header == null || !header.startsWith("Bearer ");
    }

    private String getAuthorization(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    private String getAccessToken(String header) {
        return header.split(" ")[1].trim();
    }
}
