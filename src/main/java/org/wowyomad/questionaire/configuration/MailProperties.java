package org.wowyomad.questionaire.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
public class MailProperties {
    private String host = "";
    private String port = "";
    private String username = "";
    private String password = "";
}
