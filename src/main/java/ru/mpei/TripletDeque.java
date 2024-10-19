package ru.mpei;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TripletDeque<T> implements Containerable, Iterable<T>{


    private static final int DEFAULT_QUEUE_SIZE = 1000;

    private final int maxSize;
    private Container<T> first;
    private Container<T> last;

    public TripletDeque(){
        this(DEFAULT_QUEUE_SIZE);
    }

    public TripletDeque(int maxSize) {
        first = new Container<>();
        last = first;
        this.maxSize = maxSize;
    }

    public void addLast(T value){
        if(value == null) throw new NullPointerException();
        if(size() >= maxSize){
            throw new RuntimeException("TripletDeque is full");
        }
        if( last.isFull() || last.getContent()[last.getContent().length -1] != null){
            Container<T> curr = last;
            // создание нового контейнера и связывание ссылок
            last = new Container<>();
            curr.setNext(last);
            last.setPrev(curr);

            last.addLast(value);
        }
        else last.addLast(value);
    }
    public void addFirst(T value){
        if(value == null) throw new NullPointerException();
        if(size() >= maxSize){
            throw new RuntimeException("TripletDeque is full");
        }
        if( first.isFull() || first.getContent()[0] != null) {
            Container<T> curr = first;
            // создание нового контейнера и связыание ссылок
            first = new Container<>();
            curr.setPrev(first);
            first.setNext(curr);

            first.addFirst(value);
        }
        else first.addFirst(value);
    }

    public boolean offerLast(T value){
        try{
            addLast(value);
            return true;
        } catch (RuntimeException e){
            return false;
        }
    }
    public boolean offerFirst(T value){
        try{
            addFirst(value);
            return true;
        } catch (RuntimeException e){
            return false;
        }
    }

    public T removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        T elem = last.removeLast();
        if (last.isEmpty() && last != first){
            last = last.getPrev();
            last.setNext(null);
        }
        return elem;
    }

    public T removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        T elem = first.removeFirst();
        if (first.isEmpty() && first != last){
            first = first.getNext();
            first.setPrev(null);
        }

        return elem;
    }

    public T pollLast(){
        try{
            return removeLast();
        } catch (NoSuchElementException e){
            return null;
        }
    }
    public T pollFirst(){
        try{
            return removeFirst();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public T getLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return last.getLast();
    }
    public T getFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return first.getFirst();
    }

    public T peekFirst(){
        try {
            return getFirst();
        } catch (NoSuchElementException e){
            return null;
        }
    }
    public T peekLast(){
        try {
            return getLast();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public boolean removeFirstOccurrence(Object o){
        if(o != null) {

            Container<T> curr = new Container<>();
            curr.setNext(first);
            while (curr.hasNext()){
                curr = curr.getNext();
                for (int i = 0; i < curr.getContent().length; i++) {
                    if(o.equals(curr.getContent()[i])){
                        curr.removeByIndexWithLeftShift(i);
                        if (last.isEmpty() && last != first){
                            last = last.getPrev();
                            last.setNext(null);
                        }
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean removeLastOccurrence(Object o){
        if(o != null) {

            Container<T> curr = new Container<>();
            curr.setPrev(last);
            while (curr.hasPrev()){
                curr = curr.getPrev();
                for (int i = curr.getContent().length - 1; i >= 0 ; i--) {
                    if(o.equals(curr.getContent()[i])){
                        curr.removeByIndexWithLeftShift(i);
                        if (last.isEmpty() && last != first){
                            last = last.getPrev();
                            last.setNext(null);
                        }
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean add(T elem){
        addLast(elem);
        return true;
    }

    public boolean offer(T elem){
        offerLast(elem);
        return true;
    }

    public T remove(){
        return removeFirst();
    }

    public T poll(){
        return pollFirst();
    }

    public T element(){
        return getFirst();
    }

    public T peek(){
        return peekFirst();
    }

    public boolean addAll(Collection<? extends T> c){
        if(c.isEmpty()) return false;
        for(T elem: c){
            addLast(elem);
        }
        return true;
    }
    public void push(T value){
        addFirst(value);
    }
    public void pop(){
        removeFirst();
    }

    public boolean contains(Object o){
        if(o != null){
            for(Object thisObj: this){
                if(o.equals(thisObj)) return true;
            }
        }
        return false;
    }

    public boolean remove(Object o){
        return removeFirstOccurrence(o);
    }

    public void clear(){
        for (int i = 0; i < size(); i++) {
            removeLast();
        }
    }

    public Iterator<T> iterator(){
        return new TripletDequeIterator();
    }

    public Iterator<T> descendingIterator(){
        throw new UnsupportedOperationException();
    }

    // Реализация итератора TripletDeque
    private class TripletDequeIterator implements Iterator<T>{
        Container<T> cursor;
        int cursorElementIndex;
        int remaining = size();
        TripletDequeIterator(){
            cursor = first;

            if(cursor.isEmpty()) cursorElementIndex = 0;
            else{
                for (int i = 0; i < cursor.getContent().length; i++) {
                    if(cursor.getContent()[i] != null) {
                        cursorElementIndex = i;
                        break;
                    }
                }
            }
        }
        @Override
        public boolean hasNext() {
            if(cursorElementIndex == cursor.getContent().length){
                if(cursor.hasNext()){
                    cursorElementIndex = 0;
                    cursor = cursor.getNext();
                    return cursor.getContent()[cursorElementIndex] != null;
                }
            }
            else{
                return cursor.getContent()[cursorElementIndex] != null;
            }
            return false;
        }

        @Override
        public T next() {
            if(remaining <= 0){
                throw new NoSuchElementException();
            }
            if(cursorElementIndex == cursor.getContent().length){
                cursor = cursor.getNext();
                cursorElementIndex = 0;
            }
            T elem =  (T) cursor.getContent()[cursorElementIndex];
            cursorElementIndex++;
            return elem;
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size() {
        Container<T> current = first;
        int size = first.size();
        for (Container<T> c = first; c.hasNext(); c = c.getNext()){
            size += c.getNext().size();
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Container<T> curr = first;
        stringBuilder.append(first);
        while(curr.hasNext()){
            curr = curr.getNext();
            stringBuilder.append("\n").append(curr);
        }
        return stringBuilder.toString();
    }

    @Override
    public Object[] getContainerByIndex(int cIndex) {
        Container<T> container = new Container<>();
        container.setNext(first);
        int i = 0;
        while (i <= cIndex){
            container = container.getNext();
            i++;
        }

        return container == null ? null : container.getContent();
    }
}
