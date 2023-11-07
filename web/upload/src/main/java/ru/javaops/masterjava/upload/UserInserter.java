package ru.javaops.masterjava.upload;

import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.UserDao;
import ru.javaops.masterjava.persist.model.User;
import ru.javaops.masterjava.persist.model.UserFlag;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.JaxbUnmarshaller;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserInserter {
    private final JaxbParser jaxbParser = new JaxbParser(ObjectFactory.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final UserDao userDao = DBIProvider.getDao(UserDao.class);

    public void processAndInsert(final InputStream is, int chunkSize) throws XMLStreamException, JAXBException, InterruptedException {
        final StaxStreamProcessor processor = new StaxStreamProcessor(is);
        JaxbUnmarshaller unmarshaller = jaxbParser.createUnmarshaller();
        List<Callable<Void>> tasks = new ArrayList<>();
        List<User> users = new ArrayList<>(chunkSize);
        int i = 0;
        while (processor.doUntil(XMLEvent.START_ELEMENT, "User")) {
            ru.javaops.masterjava.xml.schema.User xmlUser = unmarshaller.unmarshal(processor.getReader(), ru.javaops.masterjava.xml.schema.User.class);
            final User user = new User(xmlUser.getValue(), xmlUser.getEmail(), UserFlag.valueOf(xmlUser.getFlag().value()));
            users.add(user);
            i++;
            if (i == chunkSize) {
                List<User> usersToInsert = users;
                tasks.add(() -> {
                    userDao.insertBatch(usersToInsert, chunkSize);
                    return null;
                });
                users = new ArrayList<>(chunkSize);
            }
        }
        if (!users.isEmpty()) {
            List<User> usersToInsert = users;
            tasks.add(() -> {
                userDao.insertBatch(usersToInsert, usersToInsert.size());
                return null;
            });
        }
        executorService.invokeAll(tasks);
    }
}
