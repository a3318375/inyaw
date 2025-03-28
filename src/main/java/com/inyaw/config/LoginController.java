package com.inyaw.config;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.SysUser;
import com.inyaw.sys.dto.SysUserDto;
import com.inyaw.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final JwtEncoder encoder;
    private final SysUserService sysUserService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public BaseResult token(@RequestBody SysUser user) {
        Authentication authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        ));
        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        String scope = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authResult.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        return BaseResult.success(this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody SysUserDto user) {
        sysUserService.save(user, true);
        // @formatter:on
        return BaseResult.success();
    }
}
