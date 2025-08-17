package module2;

import java.util.Arrays;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size = 0;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = array.length * 3 / 2 + 1;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    public void add(E element) {
        ensureCapacity();
        array[size] = element;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        E oldValue = (E) array[index];
        int elementMoved = size - index - 1;
        if (elementMoved > 0) System.arraycopy(array, index + 1,
                array, index, elementMoved);
        array[size] = null;
        size--;
        return oldValue;
    }

    public void addAll(MyArrayList<? extends E> collection) {
        for (int i = 0; i < collection.size; i++) {
            add(collection.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(", ");
        }
        if (size > 0) sb.setLength(sb.length() - 2);
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        MyArrayList<String> list1 = new MyArrayList<>();
        list1.add("apple");
        list1.add("banana");
        System.out.println(list1);
        System.out.println(list1.get(1));

        list1.remove(0);
        System.out.println(list1);

        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("orange");
        list2.add("peach");
        list1.addAll(list2);
        System.out.println(list1);
    }
}
