package ru.javaops.masterjava.service.mail;

import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import ru.javaops.masterjava.config.Configs;
import ru.javaops.masterjava.persist.DBIProvider;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MailSender {
    private static final SimpleEmail simpleEmail;
    private static final MailDao mailDao = DBIProvider.getDao(MailDao.class);

    static {
        Config mailConfig = Configs.getConfig("email.conf", "email");
        simpleEmail = new SimpleEmail();
        simpleEmail.setHostName(mailConfig.getString("host"));
        simpleEmail.setSslSmtpPort(mailConfig.getString("port"));
        simpleEmail.setAuthentication(mailConfig.getString("username"), mailConfig.getString("password"));
        simpleEmail.setSSLOnConnect(mailConfig.getBoolean("useSSL"));
        try {
            simpleEmail.setFrom(mailConfig.getString("username"), mailConfig.getString("fromName"));
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
        simpleEmail.setStartTLSEnabled(mailConfig.getBoolean("useTLS"));
        simpleEmail.setDebug(mailConfig.getBoolean("debug"));
        simpleEmail.setCharset("UTF-8");
    }

    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        log.info("Send mail to \'" + to + "\' cc \'" + cc + "\' subject \'" + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));
        List<InternetAddress> addressees = to.stream()
                .map(a -> {
                    try {
                        return new InternetAddress(a.getEmail(), a.getName());
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        simpleEmail.setSubject(subject);
        try {
            simpleEmail.setMsg(body);
            simpleEmail.setTo(addressees);
            simpleEmail.setCc(addressees);
            simpleEmail.send();
            mailDao.insert(new Mail(LocalDateTime.now(), subject, body));
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }
}
