package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.Group;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class GroupDao implements AbstractDao {
    @Override
    @SqlUpdate("TRUNCATE groups")
    public abstract void clean();

    @SqlUpdate("INSERT INTO groups (name, project, type) VALUES (:name, :project, CAST(:type AS group_type))")
    abstract void insert(@BindBean Group group);

    @SqlQuery("SELECT * FROM groups WHERE name = :name")
    public abstract List<Group> get(@Bind("name") String name);
}
