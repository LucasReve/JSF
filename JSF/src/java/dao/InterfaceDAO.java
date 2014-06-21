package dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author Lucas
 */
public interface InterfaceDAO <T> {
    
    void salvar (T entity);
    void atualizar (T entity);
    void excluir (T entity);
    void merge (T entity);
    
    T getEntity (Serializable id);
    T getEntityByDetachedCriteria (DetachedCriteria criteria);
    
    List <T> getEntities();
    List <T> getListyDetachedCriteria(DetachedCriteria criteria);
     
}
