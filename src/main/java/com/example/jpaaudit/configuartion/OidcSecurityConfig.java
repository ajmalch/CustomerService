package com.example.jpaaudit.configuartion;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;

@Configuration
@ConditionalOnProperty(value = "auth.mode", havingValue = "oidc")
@EnableWebSecurity
public class OidcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(authRequest -> authRequest.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2->
                        oauth2.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter()));


    }

    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());
        return converter;
    }

    public static class CustomJwtGrantedAuthoritiesConverter implements
            Converter<Jwt, Collection<GrantedAuthority>>{

        final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        //TODO At the moment we are not doing anything specific.
        // But, this can be used to add authorities to user
        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            return jwtGrantedAuthoritiesConverter.convert(jwt);

        }
    }

}
