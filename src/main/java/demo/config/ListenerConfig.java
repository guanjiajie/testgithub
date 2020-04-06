package demo.config;

import demo.listener.CountListener;
import demo.listener.RequestListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {

    @Bean
    public ServletListenerRegistrationBean<CountListener> countListenerServletListenerRegistrationBean(){
        return new ServletListenerRegistrationBean<>(new CountListener());
    }

    //@Bean
    public ServletListenerRegistrationBean<RequestListener> requestListenerServletListenerRegistrationBean(){
        return new ServletListenerRegistrationBean<>(new RequestListener());
    }
}
