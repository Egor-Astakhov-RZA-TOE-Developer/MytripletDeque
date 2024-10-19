package ru.mpei;

public class Container<E> {
    private static final int CAPACITY = 5;
    private final E[] content;
    private Container<E> next;
    private Container<E> prev;
    private int startIndex;
    private int endIndex;


    public Container() {
        this.content = (E[]) new Object[CAPACITY];
        this.startIndex = 0;
        this.endIndex = 0;
    }

    boolean isFull(){
        for (E elem: content){
            if (elem == null){
                return false;
            }
        }
        return true;
    }

    boolean isEmpty(){
        for (E elem: content){
            if (elem != null){
                return false;
            }
        }
        return true;
    }

    public void setNext(Container<E> next) {
        this.next = next;
    }

    public void setPrev(Container<E> prev) {
        this.prev = prev;
    }

    public void addLast(E value) {
        content[size()] = value;
    }

    public void addFirst(E value) {
        content[CAPACITY - 1 - size()] = value;
    }

    public E getLast(){
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        if(content[0] == null) return content[CAPACITY - 1];
        return content[size() - 1];
    }

    public E getFirst(){
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        if(content[0] == null) return content[CAPACITY - size()];
        return content[0];
    }

    public E removeByIndexWithLeftShift(int i){
        E res = content[i];
        content[i] = null;
        for (int j = i; j < content.length - 1; j++) {
            content[j] = content[j+1];
        }
        if(this.hasNext()){
            content[content.length - 1] = this.next.content[0];
        } else content[content.length - 1] = null;

        Container<E> curr = this;
        while (curr.hasNext()){
            curr = curr.next;
            for (int j = 0; j < CAPACITY - 1; j++) {
                curr.content[j] = curr.content[j + 1];
            }
            if (curr.hasNext()){
                curr.content[CAPACITY - 1] = curr.next.content[0];
            } else curr.content[CAPACITY - 1] = null;
        }
        return res;
    }
    private E removeByIndexWithRightShift(int i){
        E res = content[i];
        content[i] = null;
        for (int j = i; j > 0; j--) {
            content[j] = content[j-1];
        }
        if(this.hasPrev()){
            content[0] = this.prev.content[content.length - 1];
        } else content[0] = null;


        Container<E> curr = this;
        while (curr.hasPrev()){
            curr = curr.prev;
            for (int j = CAPACITY-1; j > 0; j--) {
                curr.content[j] = curr.content[j-1];
            }
            if (curr.hasPrev()){
                curr.content[0] = curr.prev.content[CAPACITY - 1];
            } else curr.content[0] = null;
        }
        return res;
    }
    /*public E removeByIndex(int i){
        if(content[0] == null) return removeByIndexWithRightShift(i);
        return removeByIndexWithLeftShift(i);
    }*/


    public E removeLast(){
        int index = 0;
        for (int i = content.length - 1; i >= 0; i--) {
            if (content[i] != null){
                index = i;
                break;
            }
        }
        return removeByIndexWithLeftShift(index);
    }

    public E removeFirst(){ // dequeSize необходим для сдвига элементов
        int index = 0;
        for (int i = 0; i < content.length; i++) {
            if (content[i] != null){
                index = i;
                break;
            }
        }
        return removeByIndexWithRightShift(index);
    }
    /*public E removeByIndex(int i){
        if(content[0] == null || isFull()) return removeByIndexWithRightShift(i);
        return removeByIndexWithLeftShift(i);
    }*/


    public boolean hasNext() {
        return next != null;
    }
    public boolean hasPrev() {
        return prev != null;
    }

    public Container<E> getNext() {
        return next;
    }

    public Container<E> getPrev() {
        return prev;
    }
    public int size(){
        int size = 0;
        for(Object o: content){
            if(o != null) size++;
        }
        return size;
    }

    public Object[] getContent() {
        return (Object[]) content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(Object o: content){
            sb.append(o).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
