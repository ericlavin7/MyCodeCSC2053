import java.util.Iterator;

public class ArrayListIterator<T> implements Iterator<T>{
    private T[] elements;
    private int index = -1;

    public ArrayListIterator(T[] elements){
        this.elements = elements;
    }

    @Override
    public boolean hasNext(){
        return index < elements.length-1;
    }

    @Override
    public T next(){
        if(!hasNext()){
            return null;
        }
        return elements[++index];
    }


}