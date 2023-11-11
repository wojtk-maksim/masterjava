package ru.javaops.masterjava.upload;

import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.GroupDao;
import ru.javaops.masterjava.persist.dao.ProjectDao;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.Project;
import ru.javaops.masterjava.persist.model.type.GroupType;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.util.Map;

@Slf4j
public class ProjectAndGroupProcessor {
    private final ProjectDao projectDao = DBIProvider.getDao(ProjectDao.class);
    private final GroupDao groupDao = DBIProvider.getDao(GroupDao.class);

    public void process(StaxStreamProcessor processor) throws XMLStreamException {
        Map<String, Project> projects = projectDao.getAsMap();
        Map<String, Group> groups = groupDao.getAsMap();
        while (processor.startElement("Project", "Projects")) {
            String projectName = processor.getAttribute("name");
            Project project = projects.get(projectName);
            if (project == null) {
                project = new Project(processor.getAttribute("name"), processor.getElementValue("description"));
                log.info("Insert project " + project);
                project.setId(projectDao.insertGeneratedId(project));
            }

            while (processor.startElement("Group", "Project")) {
                String groupName = processor.getAttribute("name");
                if (!groups.containsKey(groupName)) {
                    Group group = new Group(groupName, GroupType.valueOf(processor.getAttribute("type")), project.getId());
                    log.info("To project " + project + " insert group " + group);
                    groupDao.insertGeneratedId(group);
                }
            }
        }
    }
}
