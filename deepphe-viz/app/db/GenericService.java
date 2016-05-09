package db;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
/**
 * Created by harry on 5/8/16.
 */
public abstract class GenericService<T> implements Service<T>{

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
    private Session session = Neo4JOgmWrapper.getInstance().getNeo4JSession();

    @Override
    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(), DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return session.load(getEntityType(), id, DEPTH_ENTITY);
    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }

    @Override
    public T createOrUpdate(T entity) {
        session.save(entity, DEPTH_ENTITY);
        return find(((Node) entity).getId());
    }

    public abstract Class<T> getEntityType();
}
