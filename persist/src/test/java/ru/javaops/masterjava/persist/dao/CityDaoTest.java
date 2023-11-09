package ru.javaops.masterjava.persist.dao;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.javaops.masterjava.persist.CityTestData.spb;

public class CityDaoTest extends AbstractDaoTest<CityDao> {
    public CityDaoTest() {
        super(CityDao.class);
    }

    @Test
    public void insert() {
        dao.clean();
        dao.insert(spb);
        assertEquals(spb, dao.get("spb").get(0));
    }
}
