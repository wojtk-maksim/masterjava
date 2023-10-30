package ru.javaops.upload.web;

import jakarta.xml.bind.JAXBException;
import ru.javaops.upload.schema.User;
import ru.javaops.upload.util.JaxbParser;
import ru.javaops.upload.util.StaxStreamProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet("/upload")
@MultipartConfig
public class PayloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/upload.html").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(req.getPart("payload").getInputStream())) {
            JaxbParser jaxbParser = new JaxbParser(User.class);
            XMLStreamReader reader = processor.getReader();
            List<User> users = Collections.synchronizedList(new ArrayList<>());
            List<Callable<Void>> tasks = new ArrayList<>();
            ExecutorService executorService = Executors.newCachedThreadPool();
            while (reader.hasNext()) {
                reader.next();
                if (reader.isStartElement() && "User".equals(reader.getLocalName())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("<User xmlns=\"http://javaops.ru\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://javaops.ru payload.xsd\" flag=\"")
                            .append(reader.getAttributeValue(null, "flag"))
                            .append("\" city=\"")
                            .append(reader.getAttributeValue(null, "city"))
                            .append("\" email=\"")
                            .append(reader.getAttributeValue(null, "email"))
                            .append("\">")
                            .append(reader.getElementText())
                            .append("</User>");
                    tasks.add(() -> {
                        try {
                            users.add(jaxbParser.unmarshal(stringBuilder.toString()));
                            return null;
                        } catch (JAXBException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    });
                }
            }
            executorService.invokeAll(tasks);
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/html/users.jsp").forward(req, res);
        } catch (XMLStreamException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
