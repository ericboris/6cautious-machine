import java.util.*;

/**
 * Store a list of values of type E
 * 
 * @author author, modifications by Eric Boris
 * @version 10/10/2018
 */
public class ArrayList<E> implements Iterable<E> {
    /** elementData                 generic array of values */  
    private E[] elementData; // list of values
    /** size                        the current numer of elements in the list */
    private int size;        // current number of elements in the list

    /** DEFUALT_CAPACITY            the default size of the array */
    public static final int DEFAULT_CAPACITY = 50;

    // post: constructs an empty list of default capacity
    /**
     * a generic constructor
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    // pre : capacity >= 0 (throws IllegalArgumentException if not)
    // post: constructs an empty list with the given capacity
    /**
     * a constructor with capacity setting
     * 
     * @param   capacity            the initial size to make the array
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = (E[]) new Object[capacity];
        size = 0;
    }

    // post: returns the current number of elements in the list
    /**
     * get the size of the array
     * 
     * @return                      the size of the array
     */
    public int size() {
        return size;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: returns the value at the given index in the list
    /**
     * get the value at the given index of the array
     * 
     * @param   index               the index in the array
     * @return                      the value at the given index
     */
    public E get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // post: creates a comma-separated, bracketed version of the list
    /**
     * create a comma-separated, bracketed version of this object
     * 
     * @return                      a string of this object
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    // post : returns the position of the first occurrence of the given
    //        value (-1 if not found)
    /**
     * return position of the first occurrence of the give value
     * 
     * @param value                 the value to search for
     * @return                      the index of the value, -1 if not found
     */
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // post: returns true if list is empty, false otherwise
    /**
     * true if the array is empty, false otherwise
     * 
     * @return                      true if the array is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns true if the given value is contained in the list,
    //       false otherwise
    /**
     * returns true if the given value is contained in the list, false otherwise
     * 
     * @param   value               the value to look for
     * @return                      returns true if the given value is contained in the list
     *                              
     */
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    // post: appends the given value to the end of the list
    /**
     * add the value to the end of the array
     * 
     * @param   value               the value to add
     */
    public void add(E value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    // pre : 0 <= index <= size() (throws IndexOutOfBoundsException if not)
    // post: inserts the given value at the given index, shifting subsequent
    //       values right
    /**
     * add a value at the given index
     * 
     * @param   index               the position to add the value
     * @param   value               the value to add
     */
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ensureCapacity(size + 1);
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    /**
     * remove the element at the given index
     * 
     * @param   index               the index to remove from the array
     */
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: replaces the value at the given index with the given value
    /**
     * replace the value at the given index with the given value
     * 
     * @param   index               the value index to replace
     * @param   value               the value to replace with
     */
    public void set(int index, E value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: list is empty
    /**
     * clear the entire list
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    // post: appends all values in the given list to the end of this list
    /**
     * add all elements of given arraylist to this arraylist
     * 
     * @param   other               the other array to add to this one
     */
    public void addAll(ArrayList<E> other) {
        ensureCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    // post: returns an iterator for this list
    /**
     * return an iterator for this arraylist
     * 
     * @return                      an iterator
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    // post: ensures that the underlying array has the given capacity; if not,
    //       the size is doubled (or more if given capacity is even larger)
    /**
     * ensure the array has the given capacity, increase the capacity if not
     * 
     * @param   capacity            the capacity the array should have
     */
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = ((int) Math.round(elementData.length * 1.5)) + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    //       not a legal index of the current list
    /**
     * check whether the given index is valid
     * 
     * @param   index               the index to check
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    /**
     * An array list iterator class
     */
    private class ArrayListIterator implements Iterator<E> {
        /** position                the current position within the list */
        private int position;           // current position within the list
        /** removeOk                whether it's okay to remove now */
        private boolean removeOK;       // whether it's okay to remove now

        // post: constructs an iterator for the given list
        /**
         * construct an iterator for the given list
         */
        public ArrayListIterator() {
            position = 0;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        /**
         * return true if there are more elements left, false otherwise
         * 
         * @return                  whether there are more elements left
         */
        public boolean hasNext() {
            return position < size();
        }

        // pre : hasNext() (throws NoSuchElementException if not)
        // post: returns the next element in the iteration
        /**
         * return the next element of this array
         * 
         * @return                  the next element in the array
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementData[position];
            position++;
            removeOK = true;
            return result;
        }

        // pre : next() has been called without a call on remove (throws
        //       IllegalStateException if not)
        // post: removes the last element returned by the iterator
        /**
         * remove the current element
         */
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(position - 1);
            position--;
            removeOK = false;
        }
    }
}