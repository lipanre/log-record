package io.github.lipanre.logRecord.configuration;

import io.github.lipanre.logRecord.function.CustomFunctionRegistrar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LogRecordConfiguration {

    @Bean
    public CustomFunctionRegistrar registrar() {
        return new CustomFunctionRegistrar();
    }

}
