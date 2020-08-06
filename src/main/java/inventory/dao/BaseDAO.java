package inventory.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import inventory.model.Pagging;

public interface BaseDAO<T> {

	public List<T> findAll(String queryStr, Map<String, Object> mapParams,Pagging pagging);
	
	T findById(Class<T> t, Serializable id);
	
	List<T> findByProperty(String property, Object obj);
	
	void save(T instance);
	
	void update(T instance);
	
}
