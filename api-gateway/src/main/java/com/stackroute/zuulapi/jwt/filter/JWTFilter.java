package com.stackroute.zuulapi.jwt.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)servletRequest;
        final HttpServletResponse response = (HttpServletResponse)servletResponse;
        final String authHeader = request.getHeader("Authorization");

        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request,response);
        }
        else{
            if(authHeader == null || !authHeader.startsWith("Bearer")){
                throw new ServletException("An exception occured");
            }
        }

        final String token = authHeader.substring(7);
        try {
            Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            //request.setAttribute("blog", servletRequest.getParameter("id"));

            filterChain.doFilter(request, response);
        }
        catch(JwtException e){
            forbidden();
            throw new JwtException("jwtoken tampered with !!");
        }
    }
    public ResponseEntity<?> forbidden(){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
