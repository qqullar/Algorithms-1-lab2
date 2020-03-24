import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front +++
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("adding null");

        Node oldFirst = first;

        first = new Node();

        first.item = item;
        first.next = oldFirst;

        if (isEmpty()) last = first;
        else first.next.prev = first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("adding null");

        // makin' last prev
        Node oldLast = last;
        last = new Node();

        last.item = item;
        last.next = null;
        last.prev = oldLast;

        if (isEmpty()) first = last;
        else last.prev.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("remove from empty deque");

        Item item = first.item;

        // here if sz == 1 then here
        // all pointers makes null
        size--;

        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            first = first.next;
            first.prev = null; // loitering
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("remove from empty deque");

        Item item = last.item;
        size--;
        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            last = last.prev;
            last.next = null; // loitering
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("no more items to return");

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    // unit testing (required)
    public static void main(String[] args) {
        DequerTester.test();
    }
}
