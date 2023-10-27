package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import jakarta.xml.bind.JAXBException;
import org.junit.Test;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainXml {

    @Test
    public void testProject() throws IOException, JAXBException {
        JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
        String projectId = "tj";
        Predicate<User> predicate = (user) -> {
            if ("tj".equals(projectId)) {
                return user.isTopjava();
            } else if ("mj".equals(projectId)) {
                return user.isMasterjava();
            }
            return false;
        };
        Payload payload = JAXB_PARSER.unmarshal(Resources.getResource("payload.xml").openStream());
        List<User> usersOnProject = payload.getUsers().getUser().stream()
                .filter(predicate)
                .sorted(Comparator.comparing(User::getFullName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
        usersOnProject.forEach(System.out::println);
    }

    @Test
    public void testProjectStax() throws IOException, XMLStreamException {
        String projectId = "tj";
        String project = "tj".equals(projectId) ? "topjava" : "masterjava";
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource("payload.xml").openStream())) {
            List<User> users = new ArrayList<>();
            XMLStreamReader reader = processor.getReader();
            outer:
            while (reader.hasNext()) {
                if (reader.next() == XMLEvent.START_ELEMENT && "Users".equals(reader.getLocalName())) {
                    while (reader.hasNext()) {
                        if (reader.next() == XMLEvent.END_ELEMENT && "Users".equals(reader.getLocalName())) {
                            break outer;
                        }
                        if (reader.isStartElement() && "User".equals(reader.getLocalName())
                                && ("true".equals(reader.getAttributeValue(null, project)))) {
                            String email = reader.getAttributeValue(null, "email");
                            String name = null;
                            while (reader.hasNext()) {
                                if (reader.next() == XMLEvent.START_ELEMENT && "fullName".equals(reader.getLocalName())) {
                                    name = reader.getElementText();
                                    break;
                                }
                            }
                            User user = new User();
                            user.setEmail(email);
                            user.setFullName(name);
                            users.add(user);
                        }
                    }
                }
            }
            users.stream()
                    .sorted(Comparator.comparing(User::getFullName).thenComparing(User::getEmail))
                    .forEach(System.out::println);
        }
    }

    @Test
    public void testHtml() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><table><tr><th>Name</th><th>Email</th></tr>");
        JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
        String projectId = "tj";
        Predicate<User> predicate = (user) -> {
            if ("tj".equals(projectId)) {
                return user.isTopjava();
            } else if ("mj".equals(projectId)) {
                return user.isMasterjava();
            }
            return false;
        };
        Payload payload = JAXB_PARSER.unmarshal(Resources.getResource("payload.xml").openStream());
        List<User> usersOnProject = payload.getUsers().getUser().stream()
                .filter(predicate)
                .sorted(Comparator.comparing(User::getFullName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
        usersOnProject.forEach(user ->
                sb.append("<tr><td>").append(user.getFullName())
                        .append("</td><td>").append(user.getEmail()).append("</td></tr>"));
        sb.append("</table></body></html>");
        System.out.println(sb);
    }

    @Test
    public void testXsltGroups() throws IOException, TransformerException {
        try (InputStream xslInputStream = Resources.getResource("groupsTable.xsl").openStream();
             InputStream xmlInputStream = Resources.getResource("payload.xml").openStream()) {

            XsltProcessor processor = new XsltProcessor(xslInputStream);
            processor.setParam("project", "tj");
            System.out.println(processor.transform(xmlInputStream));
        }
    }

}
