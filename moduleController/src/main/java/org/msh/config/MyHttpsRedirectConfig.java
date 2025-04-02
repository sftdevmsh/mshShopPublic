package org.msh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyHttpsRedirectConfig {

    @Value("${server.port}")
    Integer httpsPortNumber;

    @Value("${my.server.acceptable.request.from}")
    Integer requestFromPortNumber;

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> redirectHttpToHttps()
    {
        return factory -> factory.addConnectorCustomizers(connector ->
            {
                connector.setScheme("http");
                connector.setPort(requestFromPortNumber);
                connector.setRedirectPort(httpsPortNumber);
                connector.setSecure(true);
            });
    }

}
