package bg.softuni.fooddelivery.config;

import bg.softuni.fooddelivery.service.ClosedInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final ClosedInterceptor closedInterceptor;

    public InterceptorConfig(ClosedInterceptor closedInterceptor) {
        this.closedInterceptor = closedInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(closedInterceptor);
    }
}
