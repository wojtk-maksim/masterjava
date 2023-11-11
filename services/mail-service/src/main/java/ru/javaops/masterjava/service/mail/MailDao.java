package ru.javaops.masterjava.service.mail;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ru.javaops.masterjava.persist.dao.AbstractDao;

public abstract class MailDao implements AbstractDao {
    @Override
    @SqlUpdate("TRUNCATE mails")
    public abstract void clean();

    @SqlUpdate("INSERT INTO mails (datetime, subject, body) VALUES (:datetime, :subject, :body)")
    public abstract void insert(@BindBean Mail mail);
}
