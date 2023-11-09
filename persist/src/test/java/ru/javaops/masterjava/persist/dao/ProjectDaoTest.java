package ru.javaops.masterjava.persist.dao;

import org.junit.Test;
import ru.javaops.masterjava.persist.ProjectTestData;

import static org.junit.Assert.assertEquals;

public class ProjectDaoTest extends AbstractDaoTest<ProjectDao> {
    public ProjectDaoTest() {
        super(ProjectDao.class);
    }

    @Test
    public void insert() {
        dao.clean();
        dao.insert(ProjectTestData.topKotlin);
        assertEquals(ProjectTestData.topKotlin, dao.get("TopKotlin").get(0));
    }
}
