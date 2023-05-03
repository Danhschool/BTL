package Final;

import java.util.ArrayList;

public interface DataAccessObject<T> {
	public int insert(T t) ;
	
	public ArrayList<T> selectSort() ;
	
}
