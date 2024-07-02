package org.wowyomad.questionaire.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Profile("prod")
@PropertySource(value = "classpath:application-secret.properties", ignoreResourceNotFound = true)
public class MailConfiguration {

    private final Environment env;

    public MailConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public JavaMailSender javaMailSender(MailProperties mailProperties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(Integer.parseInt(mailProperties.getPort()));
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean
    public MailProperties mailProperties() {
        MailProperties mailProperties = new MailProperties();
        mailProperties.setHost(env.getProperty("settings.mail.host", ""));
        mailProperties.setPort(env.getProperty("settings.mail.port", ""));
        mailProperties.setUsername(env.getProperty("settings.mail.username", ""));
        mailProperties.setPassword(env.getProperty("settings.mail.password", ""));
        return mailProperties;
    }
}
