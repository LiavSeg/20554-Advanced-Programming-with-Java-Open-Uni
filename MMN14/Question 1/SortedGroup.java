import java.util.ArrayList;
import java.util.Iterator;

public class SortedGroup<E extends Comparable<E>>  {
	private ArrayList<E> _group;
	
	public SortedGroup() {
		_group = new ArrayList<E>();
	}
	
	
	public void add(E element) {
		int size  =_group.size();
		
		if(size==0) {
			_group.add(element);
			return;
		}
		else {
			for(int index = 0;index<size;index++) {
				if(_group.get(index).compareTo(element)>=0) {
					_group.add(index, element);
					return;
				}
			}
		_group.add(element);
		}
	}
	
	
	public int remove(E element) {
		int count = 0;
		int index = 0;
		while(index<_group.size())	{
			if(_group.get(index).equals(element)) {
				_group.remove(element);
				count++;
			}
			else
				index++;
		}
		return count;
	}
	public Iterator<E> iterator(){
		return _group.iterator();
	}

}

