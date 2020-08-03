package inventory.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {

	List<T> findAll();
	
	T findById(Class<T> t, Serializable id);
	
	List<T> findByProperty(String property, Object obj);
	
	void save(T instance);
	
	void update(T instance);
	
}
