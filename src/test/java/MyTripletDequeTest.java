import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mpei.TripletDeque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MyTripletDequeTest {
    @Test
    public void checkAddLastBy5Elements(){
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 5; i++) {
            tripletDeque.addLast(i + 1);
        }
        Assertions.assertEquals(5, tripletDeque.size());
        Assertions.assertEquals(5, tripletDeque.getLast());
        Assertions.assertEquals(1, tripletDeque.getFirst());
    }

    @Test
    public void checkAddLastBy9Elements(){
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 9; i++) {
            tripletDeque.addLast(i + 1);
        }

        Assertions.assertEquals(9, tripletDeque.size());
        Assertions.assertEquals(9, tripletDeque.getLast());
        Assertions.assertEquals(1, tripletDeque.getFirst());
    }
    @Test
    public void checkAddLastBy100Elements(){
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 100; i++) {
            tripletDeque.addLast(i + 1);
        }

        Assertions.assertEquals(100, tripletDeque.size());
        Assertions.assertEquals(100, tripletDeque.getLast());
        Assertions.assertEquals(1, tripletDeque.getFirst());
    }

    @Test
    public void checkAddFirstBy4Elements(){
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 4; i++) {
            tripletDeque.addFirst(i );
        }

        Assertions.assertEquals(0, tripletDeque.getLast());
        Assertions.assertEquals(3, tripletDeque.getFirst());
    }

    @Test
    public void checkAddFirstWithSize(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();
        int size = 33;
        for (int i = 0; i < size; i++) {
            tripletDeque.addFirst(Integer.toString(i));
        }


        Assertions.assertEquals(size, tripletDeque.size());
        Assertions.assertEquals("0", tripletDeque.getLast());
        Assertions.assertEquals("32", tripletDeque.getFirst());
    }

    @Test
    public void testIterator(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();
        int size = 10;
        tripletDeque.addFirst("Hi!");
        tripletDeque.addFirst("World!");
        tripletDeque.addLast("Chinazes!");

        for (int i = 0; i < size; i++) {
            tripletDeque.addLast(Integer.toString(i));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String s: tripletDeque){
            stringBuilder.append(s);
        }

        String string = "World!Hi!Chinazes!0123456789";


        for (String s : tripletDeque) {
            System.out.println(s);
        }

        /*for(String s: tripletDeque){
            System.out.println(s);
        }*/


//        Iterator<String> iterator = tripletDeque.iterator();
//
//        System.out.println(iterator.hasNext());
//        System.out.println(iterator.next());

        Assertions.assertEquals(string, stringBuilder.toString());
    }
    @Test
    public void testIterator2(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();
        int size = 10;
        tripletDeque.addLast("Chinazes!");
        tripletDeque.addFirst("Hi!");
        tripletDeque.addFirst("World!");


        for (int i = 0; i < size; i++) {
            tripletDeque.addLast(Integer.toString(i));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String s: tripletDeque){
            stringBuilder.append(s);
        }

        String string = "World!Hi!Chinazes!0123456789";


        for (String s : tripletDeque) {
            System.out.println(s);
        }

        Assertions.assertEquals(string, stringBuilder.toString());
    }
    @Test
    public void testRemoveMethods(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();

        tripletDeque.addFirst("Chinazes!");
        tripletDeque.addFirst("Hi!");
        tripletDeque.addFirst("World!");
        for (int i = 0; i < 5; i++) {
            tripletDeque.add("" + i);
        }
        tripletDeque.addLast("Сюрприз!");
        tripletDeque.addLast("Я последний!");

        Assertions.assertEquals("Я последний!", tripletDeque.removeLast());
        Assertions.assertEquals("World!", tripletDeque.removeFirst());
    }
    @Test
    public void testRemoveMethods2(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();

        tripletDeque.addFirst("Chinazes!");
        tripletDeque.addFirst("Hi!");
        tripletDeque.addFirst("World!");


        Assertions.assertEquals("Chinazes!", tripletDeque.removeLast());
        Assertions.assertEquals("Hi!", tripletDeque.removeLast());
        Assertions.assertEquals("World!", tripletDeque.removeLast());
        Assertions.assertEquals(true, tripletDeque.isEmpty());

    }

    @Test
    public void testAddAll(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();

        tripletDeque.addFirst("Chinazes!");
        tripletDeque.addFirst("Hi!");
        tripletDeque.addFirst("World!");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add("Name_"+i);
        }
        tripletDeque.addAll(list);

        Assertions.assertEquals("World!", tripletDeque.removeFirst());
        Assertions.assertEquals("Hi!", tripletDeque.removeFirst());
        Assertions.assertEquals("Chinazes!", tripletDeque.removeFirst());
        for(String s: list){
            Assertions.assertEquals(s, tripletDeque.removeFirst());
        }

    }

    @Test
    public void testRemoveFirstOccurrence(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();

        tripletDeque.addFirst("Chinazes!");
        tripletDeque.addFirst("Hi!");
        tripletDeque.addFirst("World!");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add("Name_"+i);
        }
        tripletDeque.addAll(list);

        tripletDeque.removeFirstOccurrence("Name_0");
        tripletDeque.removeFirstOccurrence("Name_11");
        tripletDeque.removeFirstOccurrence("Name_8");
        tripletDeque.removeFirstOccurrence("Name_10");
        tripletDeque.removeFirstOccurrence("Name_9");

        Assertions.assertEquals("World!", tripletDeque.removeFirst());
        Assertions.assertEquals("Hi!", tripletDeque.removeFirst());
        Assertions.assertEquals("Chinazes!", tripletDeque.removeFirst());
        Assertions.assertEquals("Name_7", tripletDeque.removeLast());
    }
    @Test
    public void testRemoveLastOccurrence(){
        TripletDeque<String> tripletDeque = new TripletDeque<>();

        List<String> list = new ArrayList<>();
        int size = 28;
        for (int i = 0; i < size; i++) {
            list.add("Name_"+i);
        }
        tripletDeque.addAll(list);

        for (int i = size - 1; i >= 0; i--) {
            tripletDeque.removeLastOccurrence("Name_"+i);
            if (i != 0) Assertions.assertEquals("Name_" + (i - 1), tripletDeque.getLast());
        }

        Assertions.assertEquals(0, tripletDeque.size());
    }
}