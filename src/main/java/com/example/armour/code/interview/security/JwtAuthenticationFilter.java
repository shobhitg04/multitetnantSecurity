package com.example.armour.code.interview.security;

import com.example.armour.code.interview.constant.JWTConstants;
import com.example.armour.code.interview.mastertenant.config.DBContextHolder;
import com.example.armour.code.interview.mastertenant.entity.MasterTenant;
import com.example.armour.code.interview.mastertenant.service.MasterTenantService;
import com.example.armour.code.interview.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    MasterTenantService masterTenantService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(JWTConstants.HEADER_STRING);
        String username = null;
        String audience = null; //tenantOrClientId
        String authToken = null;
        if (header != null && header.startsWith(JWTConstants.TOKEN_PREFIX)) {
            authToken = header.replace(JWTConstants.TOKEN_PREFIX,"");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                audience = jwtTokenUtil.getAudienceFromToken(authToken);
                MasterTenant masterTenant = masterTenantService.findByClientId(Integer.valueOf(audience));
                if(null == masterTenant){
                    log.error("An error during getting tenant name");
                    throw new BadCredentialsException("Invalid tenant and user.");
                }
                DBContextHolder.setCurrentDb(masterTenant.getDbName());
            } catch (IllegalArgumentException ex) {
                log.error("An error during getting username from token", ex);
            } catch (ExpiredJwtException ex) {
                log.warn("The token is expired and not valid anymore", ex);
            } catch(SignatureException ex){
                log.error("Authentication Failed. Username or Password not valid.",ex);
            }
        } else {
            log.warn("Couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                log.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
