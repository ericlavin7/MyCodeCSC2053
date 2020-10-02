
//@Eric Lavin

public class RecursiveLinkedCollection<T> implements CollectionInterface<T> {
	
	LLNode<T> front;
	int size;
	
	RecursiveLinkedCollection() {
		front = null;
		size = 0;
	}

	@Override
	public boolean add(T element) {
		LLNode<T> newNode = new LLNode<>(element);
		LLNode<T> temp = front;
		temp = recAdd(temp, element);
		
		if (temp == null) {
			temp = newNode;
			front = temp;
			return true;
		}
		else {
			temp.setLink(newNode);
			return true;
		}
	}
	

	private LLNode<T> recAdd(LLNode<T> node, T info){
		
		//just return the node that has the setNext to null so the regular add method can simply just add it
		
		if (node == null) {
			return node;
		}
		
		if (node.getLink() == null) {
			return node;
		}
		else {
			return recAdd(node.getLink(), info);
		}
	}
	
	@Override
	public boolean remove(T element) {
		
		LLNode<T> temp = front;
		temp = recRemove(temp, element);
		
		if (temp == front) {
			front = front.getLink();
		}
		
		if (temp.getLink().getInfo() == element) {
			temp.setLink(temp.getLink().getLink());
		}
		
		return true;
	}
	
	private LLNode<T> recRemove(LLNode<T> node, T info){
	
		if (node.getInfo().equals(info)) {
			return node;
		}
		
		if (node.getLink().getInfo().equals(info)) {
			return node;
		}
		else {
			return recRemove(node.getLink(), info);
		}
	}
	

	@Override
	public T get(T target) {
		LLNode<T> temp = front;
		temp = recGet(temp, target);
		if (temp != null) {
		return temp.getInfo();
		}
		else {
		return null;
		}
	}
	
	private LLNode<T> recGet(LLNode<T> node, T info) {
		if (node == null) {
			return node;
		}
		if (node.getInfo().equals(info)) {
			return node;
		}
		else {
			return recGet(node.getLink(), info);
		}
		
	}


	@Override
	public boolean contains(T target) {
		Boolean isValid;
		LLNode<T> temp = front;
		isValid = recContains(temp, target);
		return isValid;
		
	}
	
	private boolean recContains(LLNode<T> node, T target) {
		if (node.getInfo().equals(target)) {
			return true;
		}
		if (node.getLink() == null) {
			return false;
		}
		else {
			return recContains(node.getLink(), target);
		}
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public int size() {
		LLNode<T> temp = front;
		size = 0;
		size = recSize(temp, size);
		
		return size;
	}
	
	private int recSize(LLNode<T> node, int counter) {
		
		if (node != null) {
			counter = counter + 1;
			return recSize(node.getLink(), counter);
		}
		return counter;
	}
	
	public String toString() {
		if (front==null) {
			return "";
		}
		String ret = front.getInfo().toString();
		
		LLNode<T> temp = front.getLink();
		while(temp!=null) {
			ret += "," + temp.getInfo().toString();
			temp=temp.getLink();
		}
		return ret;
	}

}
