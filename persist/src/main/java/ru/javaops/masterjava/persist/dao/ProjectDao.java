package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class ProjectDao implements AbstractDao {
    @Override
    @SqlUpdate("TRUNCATE projects CASCADE")
    public abstract void clean();

    @SqlUpdate("INSERT INTO projects (name, description) VALUES (:name, :description)")
    abstract void insert(@BindBean Project project);

    @SqlQuery("SELECT * FROM projects WHERE name = :name")
    public abstract List<Project> get(@Bind("name") String name);
}
