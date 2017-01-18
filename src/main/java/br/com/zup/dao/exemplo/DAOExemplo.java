package br.com.zup.dao.exemplo;

import java.util.List;

/**
 * Interface respons�vel por gerar as a��es do WS.
 * 
 * @author lucas
 *
 * @param <T>
 * @param <K>
 */
public interface DAOExemplo <T, K> {

    void insert(T entity);

    void remove(T entity);

    void removeById(K id);

    void update(T entity);

    T findById(K id);

    List<T> listAll();

}
