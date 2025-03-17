package bondarev.sergey.send.mail.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    Integer port;
    String host;
    Boolean sslEnabled;
    String protocol;
    MailSmtpProperties propertiesMailSmtp;

    @Data
    public static class MailSmtpProperties {
        Boolean auth;
        Boolean starttlsEnable;
        Integer connectTimeout;
        Integer timeout;
        Integer writeTimeout;
    }

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername("username");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", propertiesMailSmtp.getAuth());
        props.put("mail.smtp.starttls.enable", propertiesMailSmtp.getStarttlsEnable());
        props.put("mail.smtp.connectiontimeout", propertiesMailSmtp.getConnectTimeout());
        props.put("mail.smtp.timeout", propertiesMailSmtp.getTimeout());
        props.put("mail.smtp.writetimeout", propertiesMailSmtp.getWriteTimeout());

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

}
