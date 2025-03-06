package org.delivery.api.config.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     *
     * @param objectMapper <-스프링 빈에서 자동으로 찾아준 objectmapper bean(등록해둔 ObjectMapper class의 빈)
     * @return 스웨거에도 objectMapper로 json 형식 변환
     */
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }

}
