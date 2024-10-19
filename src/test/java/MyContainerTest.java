import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mpei.Container;

import java.util.ArrayList;
import java.util.List;


class MyContainerTest {

    @Test
    void addFirst() {
        Container<Integer> container = new Container<>();
        container.addFirst(0);
        container.addFirst(1);
        container.addFirst(2);

        Integer[] expected = new Integer[5];

        Assertions.assertEquals(container.size(), 3);
    }

    @Test
    void addLastTest() {
        Container<Integer> container = new Container<>();
        container.addLast(0);
        container.addLast(1);
        container.addLast(2);
        container.addLast(3);


        Assertions.assertEquals(container.size(), 4);
    }

    @Test
    void addMixedTest() {
        Container<Integer> container = new Container<>();
        container.addLast(0);
        container.addLast(1);
        container.addLast(2);

        Assertions.assertEquals(container.size(), 3);
    }

//    @Test
    /*void removeByIndexTest() {
        Container<Integer> container = new Container<>();
        container.addLast(0);
        container.addLast(1);
        container.addLast(2);
        container.addLast(3);

        container.removeByIndex(1, container.size());
        container.removeByIndex(0, container.size());

        System.out.println();
    }
    @Test
    void removeByIndexTest2() {
        Container<Integer> container = new Container<>();
        container.addFirst(0);
        container.addFirst(1);
        container.addFirst(2);
        container.addFirst(3);

        container.removeByIndex(1);
        container.removeByIndex(3);
        System.out.println();
    }*/

    @Test
    void removeFirstTest() {
        Container<Integer> container = new Container<>();
        container.addFirst(0);
        container.addFirst(1);
        container.addFirst(2);
        container.addFirst(3);


        Assertions.assertEquals(3, container.getFirst());
    }
    @Test
    void removeLastTest() {
        Container<Integer> container = new Container<>();
        container.addLast(0);
        container.addLast(1);
        container.addLast(2);
        container.addLast(3);


        Assertions.assertEquals(3, container.getLast());
    }
}