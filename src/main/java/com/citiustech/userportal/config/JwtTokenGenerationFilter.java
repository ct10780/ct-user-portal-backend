//package com.citiustech.userportal.config;
//
//import com.citiustech.userportal.constant.SecurityConstant;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.crypto.SecretKey;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//public class JwtTokenGenerationFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(null != authentication){
//            SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes(StandardCharsets.UTF_8));
//            String jwt = Jwts.builder().setIssuer("CT Portal").setSubject("Jwt Token")
//                    .claim("username", authentication.getName())
//                    .claim("authority", populateAuthority(authentication.getAuthorities()))
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(new Date().getTime() + 300000))
//                    .signWith(secretKey)
//                    .compact();
//            response.setHeader(SecurityConstant.JWT_HEADER, jwt);
//        }
//        filterChain.doFilter(request, response);
//
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request){
//        return !request.getServletPath().equals("/user");
//    }
//
//    private String populateAuthority(Collection<? extends GrantedAuthority> collection){
//        Set<String> authoritieSet = new HashSet<>();
//        for(GrantedAuthority grantedAuthority : collection){
//            authoritieSet.add(grantedAuthority.getAuthority());
//        }
//        return String.join(",", authoritieSet);
//    }
//}
