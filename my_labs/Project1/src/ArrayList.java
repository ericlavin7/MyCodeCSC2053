import java.util.Iterator;

public class ArrayList<T> implements ListInterface<T>, Iterable<T>{
	
	T[] array;
	int size;
	
	@SuppressWarnings("unchecked")
	ArrayList() {
		array = (T[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void enlargeArray() {
		T[] temp = (T[]) new Object[size * 2];
		for(int i =0;i<array.length;i++) {
			temp[i] = (T) array[i];
		}
		array = temp;		
	}

	@Override
	public boolean add(T t) {
		if(size==array.length) {
			enlargeArray();
		}

		array[size] = t;
		++size;
		
		return true;
	}

	@Override
	public boolean contains(T t) {
		for (T element: array){
			if(t.equals(element)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}


	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public T get(int pos) throws ArrayIndexOutOfBoundsException {
		if(pos>=size || pos < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return array[pos];
	}

	@Override
	public void set(int pos, T t) throws ArrayIndexOutOfBoundsException {
		if(pos>=size || pos < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		array[pos] = t;
				
	}

	@Override
	public void add(int pos, T t) throws ArrayIndexOutOfBoundsException {
		if(size==array.length) {
			enlargeArray();
		}
		if(pos>=size || pos < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}			
		for (int i = size; i>pos; i--) {
			array[i] = array[i-1];
		}
		array[pos] = t;
		++size;
	}

	@Override
	public void remove(int pos) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		for (int i = pos; i < size-1; i++) {
			array[i] = array[i+1];
		}
		size--;
		
	}

	@Override
	public boolean remove(T t) {
		// TODO Auto-generated method stub
		for(int i = 0; i < size; i++) {
			if(array[i].equals(t)) {
				remove(i);
				return true;
			}
		}
		
		return false;
	
	}
	
	public String toString() {
		if(size==0) return "";
		String ret = array[0].toString();
		for (int i = 1; i<size; i++) {
			ret += "," + array[i].toString();
		}
		return ret;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator<T>(array);
	}

}
