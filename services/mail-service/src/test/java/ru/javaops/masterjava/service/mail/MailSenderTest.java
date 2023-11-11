package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class MailSenderTest {
    @Test
    public void sendEmail() {
        DBITestProvider.initDBI();
        MailSender.sendMail(ImmutableList.of(new Addressee("wojtk.maksim@gmail.com", "Maksim")), null, "SUBJECT", "HELLO THERE");
    }
}
