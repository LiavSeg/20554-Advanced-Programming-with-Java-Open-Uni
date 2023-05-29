import java.util.Iterator;

public class SubSortedGroup {

	public static<E extends Comparable<E>> SortedGroup<E> reduce(SortedGroup<E> sGroup,E element){
		SortedGroup<E> newGroup = new SortedGroup<E>();
		Iterator<E> sGroupIterator = sGroup.iterator();	
		while(sGroupIterator.hasNext()) {
			E e = sGroupIterator.next();
			if(e.compareTo(element)==1)
				newGroup.add(e);
		}
		return newGroup;
	}
}
