import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array based implementation of the IList interface, a simple list ADT.
 * 
 * @author Daniel Rao
 *
 * @param <T> generic type of the objects in the list
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private T[] list; // array holding objects in list
	private int count; // number of objects in list / first open position in array
	private int capacity; // capacity of the array
	private static final int DEFAULT_CAPACITY = 10;
	private int modCount; // number of modifications to list

	/**
	 * Default constructor
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList() {
		capacity = DEFAULT_CAPACITY;
		list = (T[]) (new Object[capacity]);
		count = 0;
		modCount = 0;
	}

	/**
	 * Adds the specified item to the rear of this list.
	 *
	 * @param item the item to be added to the rear of the list
	 */

	@Override
	public void add(T item) {
		checkCapacity();
		list[count] = item;
		count++;
		modCount++;

	}

	/**
	 * Inserts the specified item at the specified index.
	 * 
	 * @param pos  the position into the array to which the item is to be inserted.
	 * @param item the item to be inserted into the array
	 * @throws IndexOutOfBoundsException if the position is out of range (pos < 0 ||
	 *                                   pos >= count)
	 */
	public void outOfBounds(int pos) {

		if (pos < 0 || pos >= count) {
			throw new IndexOutOfBoundsException();
		}
	}

	public void insert(int pos, T item) {
		checkCapacity();
		outOfBounds(pos);
		// int i= pos;
		for (int i = count; i > pos; i--) {

			list[i] = list[i - 1];
		}

		list[pos] = item;
		count++;
		modCount++;

	}

	/**
	 * Checks whether there is enough capacity to add more objects, if not, doubles
	 * capacity of array to add more items to it.
	 */

	private void checkCapacity() {
		if (count == capacity) {
			capacity *= 2;
			list = Arrays.copyOf(list, capacity);
		}
	}

	/**
	 * Replace the item at the specified position with the given item.
	 * 
	 * @param pos  the position of the item to replace
	 * @param item the replacement item to be set into the list
	 * @throws IndexOutOfBoundsException if the position is out of range (pos < 0 ||
	 *                                   pos >= count)
	 */

	@Override
	public void set(int pos, T item) {
		outOfBounds(pos);
		list[pos] = item;
		modCount++;

	}

	/**
	 * Returns a reference to the element at the specified position.
	 *
	 * @param pos the position to which the reference is to be retrieved from
	 * @return the element at the specified position
	 * @throws IndexOutOfBoundsException if the position is out of range (pos < 0 ||
	 *                                   pos >= size)
	 */

	@Override
	public T get(int pos) {
		outOfBounds(pos);
		return list[pos];
	}

	/**
	 * Removes and returns the element at the specified position.
	 *
	 * @param pos the position of the element to be retrieved
	 * @return the element at the given position
	 * @throws IndexOutOfBoundsException if the position is out of range (pos < 0 ||
	 *                                   pos >= size)
	 */
	@Override
	public T remove(int pos) {
		outOfBounds(pos);
		T removedItem = list[pos];
		list[pos] = null;
		for (int i = pos; i < count; i++) {
			list[i] = list[i + 1];

		}
		count--;
		modCount++;
		return removedItem;
	}

	/**
	 * Removes and returns the first item from the list matching the specified item.
	 *
	 * @param item the item to be removed from the list
	 * @return removed item.
	 * @throws NoSuchElementException if item is not in this list
	 */
	@Override
	public T remove(T item) {
		int i = indexOf(item);
		if (i == -1) {
			throw new NoSuchElementException();
		}
		return remove(i);

	}

	/**
	 * Returns the number of items in this list.
	 *
	 * @return the integer representation of number of items in this list
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Returns the index of the first item from the list matching the specified
	 * element.
	 *
	 * @param item the item for the index is to be retrieved.
	 * @return the integer position for this item or -1 if element is not in the
	 *         list.
	 */
	@Override
	public int indexOf(T item) {

		for (int i = 0; i < count; i++) {

			if (list[i].equals(item)) {
				return i;
			}

		}
		return -1;

	}

	/**
	 * Returns true if this list contains no items.
	 *
	 * @return true if this list contains no items.
	 */
	@SuppressWarnings("unchecked")
	public void makeEmpty() {
		capacity = DEFAULT_CAPACITY;
		list = (T[]) (new Object[capacity]);
		count = 0;
		modCount++;
	}

	/**
	 * returns the instantiated iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	/**
	 * Iterator for the List ADT
	 * 
	 * @author Daniel Rao
	 *
	 */
	private class ListIterator implements Iterator<T> {
		private int next; // index of next item to be returned
		private int itrModCount; // number of modifications when initialize iterator
		private boolean canRemove; // flag for precondition on remove method

		/**
		 * Default constructor, iteration starts at first item
		 */
		public ListIterator() {
			next = 0;
			itrModCount = modCount;
			canRemove = false;
		}

		/**
		 * returns true if there are more items to be iterated over, else returns false.
		 */
		@Override
		public boolean hasNext() {
			return (next < count);
		}

		/**
		 * moves over to the next element if there are any to be moved over to the next.
		 */
		@Override
		public T next() {
			checkModifications();

			if (!hasNext()) {
				throw new NoSuchElementException("Can't call next, no more items in list");
			}
			T item = list[next];
			next++;
			canRemove = true;
			return item;
		}

		/**
		 * finds the item in the list to be removed and updates the list.
		 */

		@Override
		public void remove() {
			if (canRemove == false) {
				throw new IllegalStateException();
			}
			// T[] copy = Arrays.copyOf(list, capacity-1);
			for (int i = next - 1; i < count; i++) {
				list[i] = list[i + 1];
			}
			// list[count-1] = null;
			next--;
			itrModCount++;
			modCount++;
			count--;
			canRemove = false;
		}

		/**
		 * Check whether modifications done to list
		 */
		private void checkModifications() {
			if (modCount != itrModCount) {
				throw new ConcurrentModificationException("Changes made to list");
			}
		}
	}

	/**
	 * Adds the specified element to the front of this list.
	 *
	 * @param element the element to be added to the front of this list
	 */
	@Override
	public void addToFront(T element) {
		checkCapacity();
		
		if(isEmpty()) {
			list[0] = element;
		}

		for (int i = count; i > 0; i--) {

			list[i] = list[i - 1];
		}
		
		list[0] = element;
		count++;
		

	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the rear of this list
	 */
	@Override
	public void addToRear(T element) {

		checkCapacity();
		list[count] = element;
		count++;
		modCount++;

	}

	/**
	 * Adds the specified element after the specified target.
	 *
	 * @param element the element to be added after the target
	 * @param target  the target is the item that the element will be added after
	 * @throws NoSuchElementException if target element is not in this list
	 */
	@Override
	public void addAfter(T element, T target) {
		checkCapacity();

		int targetIndex = indexOf(target); // indexOF
		if (targetIndex == -1 || count < targetIndex) {
			throw new NoSuchElementException();
		}

		for (int i = count; i > targetIndex; i--) {
			list[i] = list[i - 1];
		}

		list[targetIndex + 1] = element;

		count++;
		modCount++;

	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the rear of the list
	 */
	@Override
	public void add(int index, T element) {
		checkCapacity();
		if (index == -1 || count < index) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = count ; i > index ; i--) {
			
			list[i] = list[i-1];

		}
		list[index] = element;
		modCount++;
		count++;

	}

	/**
	 * Removes and returns the first element from this list.
	 * 
	 * @return the first element from this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T removeFirst() {

		if (count == 0) {
			throw new NoSuchElementException();
		}

		T firstElement1 = list[0];
		for (int i = 1; i < count; i++) {
			list[i - 1] = list[i];
		}
		count--;
		modCount++;

		return firstElement1;
	}

	/**
	 * Removes and returns the last element from this list.
	 *
	 * @return the last element from this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T removeLast() {
		if (count == 0) {
			throw new NoSuchElementException();
		} else
			modCount++;
		T lastIndex = list[count - 1];
		T[] copy = Arrays.copyOf(list, capacity - 1);
		for (int i = 0; i < capacity - 1; i++) {
			copy[i] = list[i];
		}
		list = copy;
		count--;
		return lastIndex;
	}

	/**
	 * Returns a reference to the first element in this list.
	 *
	 * @return a reference to the first element in this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T first() {
		if (list[0] == null) {
			throw new NoSuchElementException();
		} else
			return list[0];

	}

	/**
	 * Returns a reference to the last element in this list.
	 *
	 * @return a reference to the last element in this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T last() {
		if (count == 0) {
			throw new NoSuchElementException();
		}

		return list[count - 1];

	}

	/**
	 * Returns true if this list contains the specified target element.
	 *
	 * @param target the target that is being sought in the list
	 * @return true if the list contains this element, else false
	 */
	@Override
	public boolean contains(T target) {

		for (int i = 0; i < count; i++) {
			if (list[i] == target) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {

		if (count == 0) {
			return true;
		}
		return false;

	}

	/**
	 * Returns a ListIterator for the elements in this list.
	 *
	 * @return a ListIterator over the elements in this list
	 *
	 * @throws UnsupportedOperationException if not implemented
	 */
	@Override
	public java.util.ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a ListIterator for the elements in this list, with the iterator
	 * positioned before the specified index.
	 *
	 * @return a ListIterator over the elements in this list
	 *
	 * @throws UnsupportedOperationException if not implemented
	 */
	@Override
	public java.util.ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

}
