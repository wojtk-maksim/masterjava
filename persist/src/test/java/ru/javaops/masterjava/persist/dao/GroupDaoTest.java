package ru.javaops.masterjava.persist.dao;

import org.junit.Test;
import ru.javaops.masterjava.persist.DBIProvider;

import static org.junit.Assert.assertEquals;
import static ru.javaops.masterjava.persist.ProjectTestData.topKotlin;
import static ru.javaops.masterjava.persist.ProjectTestData.topKotlin01;

public class GroupDaoTest extends AbstractDaoTest<GroupDao> {
    ProjectDao projectDao;

    public GroupDaoTest() {
        super(GroupDao.class);
        projectDao = DBIProvider.getDao(ProjectDao.class);
    }

    @Test
    public void insert() {
        projectDao.clean();
        projectDao.insert(topKotlin);
        dao.insert(topKotlin01);
        assertEquals(topKotlin01, dao.get("topkotlin01").get(0));
    }
}
