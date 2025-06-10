package ru.netology.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.profile.DevProfile;
import ru.netology.profile.ProductionProfile;
import ru.netology.profile.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(
            value = "netology.profile.dev",
            havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnMissingBean(name = "devProfile")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
