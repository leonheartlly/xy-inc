package br.com.zup.dao.exemplo;

import java.util.List;

/**
 * Implementa ações executadas por cada metodo do WS.
 * 
 * Esta classe faria comunicação com o banco através dos métodos do hibernate.
 * Uma vez que o DAO é genérico ele atenderia todas as classes com o mesmo
 * método permitindo ainda a implementação
 * de novas ações de acordo com o necessário.
 * 
 * @author lucas
 *
 * @param <T>
 *            entity
 * @param <K>
 *            id
 */
public abstract class GenericDAOExemplo<T, K> implements DAOExemplo<T, K>  {
	
    private Class<T> entityClass;
    protected EntityManager entityManager;

    public GenericDAOExemplo(Class<T> entityClass, EntityManager entityManger) {
        this.entityClass = entityClass;
        this.entityManager = entityManger;
    }

    public void insert(T entity) {
        entityManager.persist(entity);
    }
    
    public void insertAll(List<T> entities) {
        for (T t : entities) {
            entityManager.persist(t);
        }
    }

    public void remove(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    public void removeById(K id) {
        T entity = entityManager.find(entityClass, id);
        entityManager.remove(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public T findById(K id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> listAll() {
        return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t", entityClass)
                            .getResultList();
    }
}
