package repository;

import java.sql.SQLException;
import java.util.List;

// Usamos <T> para que sea genérico y sirva para cualquier modelo
public interface Repository<T> {

    List<T> findAll() throws SQLException;

    T getById(Integer id) throws SQLException;

    void save(T t) throws SQLException;

    void delete(Integer id) throws SQLException;
}