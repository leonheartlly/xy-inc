package br.com.zup.dao.exemplo;

import java.util.List;

/**
 * Interface responsável por gerar as ações do WS.
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
