
package db;

import org.neo4j.ogm.session.Session;

public interface Service<T> {

    Iterable<T> findAll();

    T find(Long id);

    void delete(Long id);

    T createOrUpdate(T object);

}