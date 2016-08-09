package HandsOnExerciseCoreJava;
import java.sql.SQLException;
import java.util.List;

public interface IMyDAO <T> {
    
    public int add(T object);
    public int update(String name);
    public T find(String name);
    public int delete (String name);
    public List<T> findAll() throws SQLException;

}
