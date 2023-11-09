package ru.javaops.masterjava.persist;

import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.Project;

import static ru.javaops.masterjava.persist.model.GroupType.CURRENT;

public class ProjectTestData {
    public static final Project topKotlin = new Project("TopKotlin", "TopKotlin project");
    public static final Group topKotlin01 = new Group("topkotlin01", "TopKotlin", CURRENT);
}
