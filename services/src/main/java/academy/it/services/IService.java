package academy.it.services;

import java.util.List;

public interface IService<T> {

	T save(T t);

	T find(Integer id);

	void update(T t);

	void delete(Integer id);

	List<T> findAll();
}
