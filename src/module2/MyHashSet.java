package module2;

import java.util.Objects;

public class MyHashSet<E> {
    private static final int INITIAL_CAPACITY = 16;
    private Node<E>[] buckets;

    private static class Node<E> {
        final E value;
        Node<E> next;

        Node(E key, Node<E> next) {
            this.value = key;
            this.next = next;
        }
    }

    public MyHashSet() {
        buckets = new Node[INITIAL_CAPACITY];
    }

    private int getIndex(E element) {
        if (element == null) return 0;
        return Math.abs(element.hashCode() % INITIAL_CAPACITY);
    }

    public boolean add(E element) {
        int index = getIndex(element);
        Node<E> head = buckets[index];

        Node<E> current = head;
        while (current != null) {
            if (Objects.equals(current.value, element)) {
                return false;
            }
            current = current.next;
        }

        Node<E> newNode = new Node<>(element, head);
        buckets[index] = newNode;
        return true;
    }

    public boolean remove(E element) {
        int index = getIndex(element);
        Node<E> head = buckets[index];
        Node<E> previous = null;
        Node<E> current = head;

        while (current != null){
            if(Objects.equals(current.value, element)){
                if(previous == null) buckets[index] = current.next;
                else previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Node<E> head : buckets) {
            Node<E> current = head;
            while (current != null) {
                sb.append(current.value).append(", ");
                current = current.next;
            }
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        return sb.append("}").toString();
    }

    public static void main(String[] args) {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("orange");
        System.out.println(set);
        set.remove("banana");
        System.out.println(set);
    }
}
