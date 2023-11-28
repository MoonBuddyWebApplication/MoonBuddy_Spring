package com.project.moonbuddy.auth.filter;

import com.project.moonbuddy.auth.model.JwtProvider;
import com.project.moonbuddy.auth.model.UserDetailsServiceImpl;
import com.project.moonbuddy.auth.model.UserPrincipal;
import com.project.moonbuddy.auth.util.CookieUtils;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        String jwt = null;

        Optional<Cookie> jwtCookie = CookieUtils.getCookie(request, "access_token");
        if (jwtCookie.isPresent()){
            jwt = jwtCookie.get().getValue();
            username = jwtProvider.extractUsername(jwt);
        }
        /*
         * 토큰에서 username을 정상적으로 추출할 수 있고
         * SecurityContextHolder 내에 authentication 객체(이전에 인증된 정보)가 없는 상태인지를 검사한다.
         */
        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(username);

            // 토큰이 유효하다면
            if(jwtProvider.validateToken(jwt, userPrincipal.getUsername())){
                // 새로운 인증 정보를 생성
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 인증 정보를 SecurityContextHolder에 저장
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
