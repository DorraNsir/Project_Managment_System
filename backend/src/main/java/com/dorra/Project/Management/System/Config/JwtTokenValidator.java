package com.dorra.Project.Management.System.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
//
//        if (jwt != null && jwt.startsWith("Bearer ")) {
//            jwt = jwt.substring(7).trim(); // Trim the token to remove any extra whitespace
//
//            // Debugging: Log the JWT token
//            System.out.println("JWT Token in filter: " + jwt);
//
//            try {
//                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//                Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//
//                String email = claims.get("email", String.class);
//                List<String> authorities = claims.get("authorities", List.class);
//                List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
//
//                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (Exception e) {
//                throw new BadCredentialsException("Invalid token", e);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String jwt = request.getHeader(JwtConstant.JWT_HEADER);

    if (jwt != null && jwt.startsWith("Bearer ")) {
        jwt = jwt.substring(7).trim();

        // Debugging: Log the JWT token
        System.out.println("JWT Token in filter: " + jwt);

        try {
            SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
            Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

            String email = claims.get("email", String.class);
            List<String> authorities = claims.get("authorities", List.class);
            List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));

            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token", e);
        }
    }

    filterChain.doFilter(request, response);
}
}
