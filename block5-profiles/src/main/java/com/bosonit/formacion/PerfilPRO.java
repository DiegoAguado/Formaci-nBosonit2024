package com.bosonit.formacion;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Data
@Profile("PRO")
public class PerfilPRO implements PerfilInterface{
    @Value("${spring.profiles.active:PRO}")
    private String profileName;
    @Value("${bd.url}")
    private String bdUrl;
}
