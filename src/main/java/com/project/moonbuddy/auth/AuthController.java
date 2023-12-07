package com.project.moonbuddy.auth;

import com.project.moonbuddy.auth.dto.AuthDTO;
import com.project.moonbuddy.auth.model.JwtProvider;
import com.project.moonbuddy.auth.model.UserPrincipal;
import com.project.moonbuddy.exception.AuthenticationFailedException;
import com.project.moonbuddy.user.model.UserService;
import com.project.moonbuddy.auth.util.CookieUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    // 로그인
    @PostMapping
    public ResponseEntity authenticationUsernamePassword(@Valid @RequestBody AuthDTO authorizationRequest, HttpServletRequest request, HttpServletResponse response){
        log.info("id ; {}",authorizationRequest.getUsername());
        log.info("pw ; {}",authorizationRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequest.getUsername(), authorizationRequest.getPassword()));
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            log.info("username => {}", userPrincipal.getUsername());
            String status = generateTokenCookie(userPrincipal, request, response);
            log.info("loginUser => {}", userPrincipal.getId());
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("아이디 또는 패스워드가 틀렸습니다.");
        }
    }
    private String generateTokenCookie(UserPrincipal userPrincipal, HttpServletRequest request, HttpServletResponse response) {
        final int cookieMaxAge = jwtProvider.getTokenExpirationDate().intValue();
        //https 프로토콜인 경우 secure 옵션사용
        boolean secure = request.isSecure();
        CookieUtils.addCookie(response, "access_token", jwtProvider.generateToken(userPrincipal.getUsername()), true, secure, cookieMaxAge);
        return "SUCCESS";
    }

    // 로그아웃
    /* 토큰 쿠키를 삭제하는 컨트롤러 (로그아웃) */
    @PostMapping("/logout")
    public ResponseEntity<?> expiredToken(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "access_token");
        CookieUtils.deleteCookie(request, response, "JSESSIONID");
        //CookieUtils.deleteCookie(request, response, StatelessCSRFFilter.CSRF_TOKEN);
        return ResponseEntity.ok("SUCCESS");
    }
}
