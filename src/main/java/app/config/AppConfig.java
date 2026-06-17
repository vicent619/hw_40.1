package app.config;

import tools.jackson.databind.json.JsonMapper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("app")
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(HttpMessageConverters.ServerBuilder builder) {
        JsonMapper jsonMapper = JsonMapper.builder().build();
        builder.withJsonConverter(new JacksonJsonHttpMessageConverter(jsonMapper));
    }
}
