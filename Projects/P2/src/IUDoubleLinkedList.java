import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * IUDoubleLinkedList functionalities.
 * 
 * @author danielrao
 * @param <T> - class of objects stored in the list
 *
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

	private DLLNode<T> head; // reference to the beginning of the List.
	private DLLNode<T> tail; // reference to the ending of the list.
	private int count; // keeps track of number of nodes in the list.
	private int modCount; // keeps track of the number of modifications made to the list.

	/**
	 * Default constructor, when the list gets instantiated.
	 */
	public IUDoubleLinkedList() {
		head = tail = null;
		count = 0;
		modCount = 0;
	}

	/**
	 * Adds the specified element to the front of this list.
	 *
	 * @param element the element to be added to the front of this list
	 */
	@Override
	public void addToFront(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		// case 1: empty case
		if (isEmpty()) {
			head = tail = newNode;
			count++;
			modCount++;

		} else {
			// case 2: general case
			newNode.setNext(head); // right link to head
			head.setPrev(newNode); // left link back to newNode
			head = newNode;
			count++;
			modCount++;
		}

	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the rear of this list
	 */
	@Override
	public void addToRear(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		// when the list is not empty
		if (isEmpty()) {
			head = tail = newNode;
			count++;
			modCount++;
		} else {
			// doesn't work if empty
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
			count++;
			modCount++;
		}

	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the rear of the list
	 */
	@Override
	public void add(T element) {
		// works similar to add to rear
		addToRear(element);

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

		// grabs the index of the the element that is to be targeted.
		int targetIndex = indexOf(target);
		// checks if the targetIndex of the element is a -1 and if so throws
		// NoSuchElementException.
		if (targetIndex == -1) {
			throw new NoSuchElementException("target not found.");
		}
		// calls the add method to add the element at index+1
		else {
			add(targetIndex + 1, element);
		}

	}

	/**
	 * Inserts the specified element at the specified index.
	 * 
	 * @param index   the index into the array to which the element is to be
	 *                inserted.
	 * @param element the element to be inserted into the array
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index > size)
	 */
	@Override
	public void add(int index, T element) {
		// case 1: checks for index out of bounds if so throws the exception.
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("Invaid Index");
		}

		DLLNode<T> newNode = new DLLNode<T>(element);
		DLLNode<T> oneBefore = head; // saves the reference for the previous node
		DLLNode<T> oneAfter = null; // saves reference of one node after where we want to add

		// case 2: when adding at index 0 which means adding the element to the front of
		// the list.
		if (index == 0) {
			addToFront(element);
		}

		// case 3: when index == count adding the element to the rear of the list.
		else if (index == count) {
			addToRear(element);
		}

		// case 4: when index == count - 1 (adding at right before the tail, where in
		// this this case we do not update the referenc)
		else if (index == count - 1) {

			oneBefore = tail.getPrev();
			oneBefore.setNext(newNode); // connects newNode to node before it.
			newNode.setPrev(oneBefore); // connects node before back to newNode.
			newNode.setNext(tail); // connects newNode to node after it (which is tail).
			tail.setPrev(newNode); // connects node after (which is tail) back to newNode.
			count++;
			modCount++;
		}

		// case 5: Works in all other general cases.
		else {

			for (int i = 0; i < index - 1; i++) {
				oneBefore = oneBefore.getNext();
			}

			oneAfter = oneBefore.getNext();
			oneBefore.setNext(newNode); // connects newNode to node before it
			newNode.setPrev(oneBefore); // connects node before back to newNode
			newNode.setNext(oneAfter); // connects newNode to node after it
			oneAfter.setPrev(newNode); // connects node after back to newNode
			count++;
			modCount++;

		}
	}

	/**
	 * Removes and returns the first element from this list.
	 * 
	 * @return the first element from this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T removeFirst() {
		// case 1: when the list is empty.
		if (isEmpty()) {
			throw new NoSuchElementException("No Element in the list");
		}
		// saves reference of node we're removing
		DLLNode<T> secondNode = head.getNext();
		// saves the element at head to be returned.
		T temp = head.getElement();
		// case 2: when there is only one node in the list.
		if (count == 1) {
			head = tail = null;
			count--;
			modCount++;
			return temp;
		}
		// case 3: functions in all other general cases.
		else {

			head.setNext(null);
			secondNode.setPrev(null);
			head = secondNode;
			count--;
			modCount++;
			return temp;
		}
	}

	/**
	 * Removes and returns the last element from this list.
	 *
	 * @return the last element from this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T removeLast() {
		// case 1: functions when the list is empty.
		if (isEmpty()) {
			throw new NoSuchElementException("No Element in the List");
		}
		// case 2: when there is only one Node, calls the method removeFirst().
		else if (count == 1) {
			return removeFirst(); // uses same logic for the same case in removeFirst()
		}
		// Saves the reference to previous node so the list doesn't break
		DLLNode<T> secondLast = tail.getPrev();
		// Saves the Element at tail to be returned.
		T temp = tail.getElement();
		// case 3: when count (node count) = 2
		if (count == 2) {
			head.setNext(null);
			tail = head;
			count--;
			modCount++;
			return temp;
		}
		// case 4: In all other cases this code is executed.
		else {
			tail.setPrev(null);
			secondLast.setNext(null);
			tail = secondLast;
			count--;
			modCount++;
			return temp;
		}

	}

	/**
	 * Removes and returns the first element from the list matching the specified
	 * element.
	 *
	 * @param element the element to be removed from the list
	 * @return removed element
	 * @throws NoSuchElementException if element is not in this list
	 */
	@Override
	public T remove(T element) {

		// grabs the index of the element to be removed be calling IndexOf method.
		int targetIndex = indexOf(element);
		// if the index is equal to -1 than it is determined that the element does not
		// exist in the list.
		if (targetIndex == -1) {
			throw new NoSuchElementException("No Such Element in the List");
		}
		// calls the method remove(int index) and returns the element that is to be
		// removed from the list.
		return remove(targetIndex);
	}

	/**
	 * Removes and returns the element at the specified index.
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size)
	 */
	@Override
	public T remove(int index) {
		DLLNode<T> before = head;
		DLLNode<T> toRemove = null;
		DLLNode<T> after = toRemove;
		T temp = null;

		// case 1: checks if the user provided bounds are valid.
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("Invaid Index");
		}
		// case 2: if index is at 0 than it removes the first element by calling
		// removeFirst() and returns the element.
		else if (index == 0) {
			return removeFirst();
		}
		// case 3: if index is at count -1 that means we are at tail in the list so that
		// means removeLast() can be called to get rid of the node and return the
		// element.
		else if (index == count - 1) {
			return removeLast();
		}
		// case 4: In all the other cases this code is executed.
		else {
			for (int i = 0; i < index - 1; i++) {
				before = before.getNext();
			}
			toRemove = before.getNext();
			temp = toRemove.getElement();
			after = before.getNext().getNext();
			before.setNext(after);
			after.setPrev(before);
			toRemove.setNext(null);
			toRemove.setPrev(null);
			count--;
			modCount++;
			return temp;

		}

	}

	/**
	 * Replace the element at the specified index with the given element.
	 *
	 * @param index   the index of the element to replace
	 * @param element the replacement element to be set into the list
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size)
	 */
	@Override
	public void set(int index, T element) {
		// checks and sees if the index provided by the user are within bounds for the
		// operation to be performed.
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("Invaid Index");
		}
		// currents starts from head but it gets updated.
		DLLNode<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		// sets the element at the specified node with user defined element thats passed
		// in the parameter.
		current.setElement(element);
		modCount++;
	}

	/**
	 * Returns a reference to the element at the specified index.
	 *
	 * @param index the index to which the reference is to be retrieved from
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size)
	 */
	@Override
	public T get(int index) {
		// checks and sees if the index provided by the user are within bounds to be
		// able to grab the element at the specified indexed node.
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("Invaid Index");
		}
		// currents starts from head but it gets updated.
		DLLNode<T> current = head;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		// returns the element at the user specified indexed node.
		return current.getElement();
	}

	/**
	 * Returns the index of the first element from the list matching the specified
	 * element.
	 *
	 * @param element the element for the index is to be retrieved
	 * @return the integer index for this element or -1 if element is not in the
	 *         list
	 */
	@Override
	public int indexOf(T element) {
		// currents starts from head but it gets updated.
		DLLNode<T> current = head;
		int index = 0;

		for (int i = 0; i < count; i++) {
			// checks to see if the user provided element is equal to what element in the
			// current is at a certain iteration of the loop.
			if (element.equals(current.getElement())) {
				// updates my index.
				index = i;
				// returns my index if found.
				return index;
			}
			// current keeps updating as long as index is not found
			current = current.getNext();
		}
		// if the element in the list is not found the method returns -1.
		return -1;
	}

	/**
	 * Returns a reference to the first element in this list.
	 *
	 * @return a reference to the first element in this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T first() {
		// if the list is empty than throws NoSuchElementException()
		if (isEmpty()) {
			throw new NoSuchElementException("No Element in the List");
		}
		// otherwise always returns the element at head.
		return head.getElement();
	}

	/**
	 * Returns a reference to the last element in this list.
	 *
	 * @return a reference to the last element in this list
	 * @throws NoSuchElementException if list contains no elements
	 */
	@Override
	public T last() {
		// if the list is empty than throws NoSuchElementException()
		if (isEmpty()) {
			throw new NoSuchElementException("No Element in the List");
		}
		// otherwise always returns the element at tail.
		return tail.getElement();
	}

	/**
	 * Returns true if this list contains the specified target element.
	 *
	 * @param target the target that is being sought in the list
	 * @return true if the list contains this element, else false
	 */
	@Override
	public boolean contains(T target) {
		// checks to see if the indexOf(T target) can grab the index of the user defined
		// target.
		int exists = indexOf(target);
		// if exists return a -1 method call returns false
		if (exists == -1) {
			return false;
		}
		// else true.
		return true;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		// if (count=0) it means that the list is empty.
		if (count == 0) {
			return true;
		}
		// otherwise its not.
		return false;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the integer representation of number of elements in this list
	 */
	@Override
	public int size() {
		// returns the number of nodes in the list.
		return count;
	}

	/**
	 * Returns an Iterator for the elements in this list.
	 *
	 * @return an Iterator over the elements in this list
	 */
	@Override
	public Iterator<T> iterator() {
		// returns the Iterator for the list.
		return new DLLIterator();
	}

	/**
	 * Returns a ListIterator for the elements in this list.
	 *
	 * @return a ListIterator over the elements in this list
	 *
	 * @throws UnsupportedOperationException if not implemented
	 */
	@Override
	public ListIterator<T> listIterator() {
		// returns the listIterator.
		return new DLLIterator();
	}

	/**
	 * Returns a ListIterator for the elements in this list, with the iterator
	 * positioned before the specified index.
	 *
	 * @return a ListIterator over the elements in this list
	 * @startingIndex The index at which from the iterator is instantiated.
	 * @throws UnsupportedOperationException if not implemented
	 */
	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		// returns the list iterator with a starting index.
		return new DLLIterator(startingIndex);
	}

	/**
	 * listIterator for the IUDoubleLinkedList class
	 *
	 * @author danielrao
	 *
	 */

	private class DLLIterator implements ListIterator<T> {
		public DLLNode<T> nextNode = head; // nextNode starts from head.
		public DLLNode<T> current = null; // current will get updated with each call of next previous add or remove.
		public int nextIndex; // keeps track at which index the nextNode is as we iterate through the list.
		public int itrModCount; // keeps track of all the modifications made through the listIterator.

		/**
		 * Default constructor, iterator a beginning of list
		 */
		public DLLIterator() {
			// passed 0 from the second constructor of listIterator which means we start at
			// head if the user instantiate iterator without providing any index to the
			// parameter.
			this(0);

		}

		/**
		 * Constructor for listIterator when starting at certain index.
		 * 
		 * @param nextIndex - keeps track at which index the nextNode is as we iterate
		 *                  through the list.
		 * @throws IndexOutOfBoundsException if the index is invalid.
		 */
		public DLLIterator(int nextIndex) {
			// Bounds Check if the user defined index is valid.
			if (nextIndex < 0 || nextIndex > count) {
				throw new IndexOutOfBoundsException("Invaid Index");
			}
			// Sync index and modCount.
			this.nextIndex = nextIndex;
			itrModCount = modCount;
			// Assignment or jump to that index.
			for (int i = 0; i < nextIndex; i++) {
				nextNode = nextNode.getNext();
			}

		}

		/**
		 * A simple method to check ConcurrentModificationException which can be used is
		 * other list iterator methods by just calling this method.
		 * 
		 * @throws ConcurrentModificationException if (itrModCount != modCount)
		 */
		public void modCheck() {
			// checks if the number of modification in the list is also equal to the
			// modifications made through the iterator.
			// if not throws ConcurrentModificationException().
			if (itrModCount != modCount) {
				throw new ConcurrentModificationException();
			}

		}

		/**
		 * @param e adds the element at the specified place in the list as the user
		 *          iterate through the list.
		 */
		@Override
		public void add(T e) {
			modCheck();
			current = null; // resets current to null.
			// Creates the node.
			DLLNode<T> newNode = new DLLNode<T>(e);

			// when the nextNode is equal to head, than in that case head is reassigned to
			// the newNode.
			if (nextNode == head) {
				head = newNode;
			}

			// checks if the nextNode is null, if so than the newNode is added after tail.
			if (nextNode == null) {
				newNode.setPrev(tail);
				tail = newNode; // tail gets updated.

			}

			// this is the general case of adding the newNode where the links are
			// reassigned.
			else {
				newNode.setNext(nextNode);
				newNode.setPrev(nextNode.getPrev());
				nextNode.setPrev(newNode);
			}

			count++;
			modCount++;
			itrModCount++;
			nextIndex++;

		}

		/**
		 * @return Returns true if this list iterator has more elements when traversing
		 *         the list in the forward direction.
		 */
		@Override
		public boolean hasNext() {
			// checks if nextNode is not equal to null.
			return (nextNode != null);
		}

		/**
		 * @return Returns true if this list iterator has more elements when traversing
		 *         the list in the reverse direction.
		 */
		@Override
		public boolean hasPrevious() {
			// checks if nextNode is not equal to head.
			return ((nextNode != head));
		}

		/**
		 * @returns Returns the next element in the list and advances the cursor
		 *          position. This method may be called repeatedly to iterate through
		 *          the list, or intermixed with calls to previous() to go back and
		 *          forth.
		 * @throws NoSuchElementException if nextNode is equal to null.
		 */
		@Override
		public T next() {
			// exception check.
			if (!hasNext()) {
				throw new NoSuchElementException("Element does not exist");
			}
			// saving the one to be returned.
			T temp = nextNode.getElement();
			// Assignment of current.
			current = nextNode;
			// points to the next node.
			nextNode = nextNode.getNext();
			// increments the index.
			nextIndex++;

			return temp;

		}

		/**
		 * @return Returns the index of the element that would be returned by a
		 *         subsequent call to next(). (Returns list size if the list iterator is
		 *         at the end of the list.)
		 */

		@Override
		public int nextIndex() {

			return nextIndex;
		}

		/**
		 * @return return Returns the previous element in the list and moves the cursor
		 *         position backwards. This method may be called repeatedly to iterate
		 *         through the list backwards, or intermixed with calls to next() to go
		 *         back and forth.
		 * @throws NoSuchElementException() if nextNode is equal to head.
		 */
		@Override
		public T previous() {
			// exception check.
			if (!hasPrevious()) {
				throw new NoSuchElementException("Element does not exist in the list.");
			}
			// when nextNode is equal to null it means that we are at the end of our list
			// in which case the current becomes the tail
			if (nextNode == null) {
				// current gets assigned to tail
				current = tail;
				// nextNode gets reassigned to current which is equal to tail.
				nextNode = current;
				// decrements the index.
				nextIndex--;
			}
			// general condition
			else {
				// nextNode gets updated to the one before.
				nextNode = nextNode.getPrev();
				// current gets reassigned.
				current = nextNode;
				// index gets decremented.
				nextIndex--;
			}

			return current.getElement();

		}

		/**
		 * @return Returns the index of the element that would be returned by a
		 *         subsequent call to previous().
		 */
		@Override
		public int previousIndex() {
			return (nextIndex - 1);
		}

		/**
		 * Removes from the list the last element that was returned by next() or
		 * previous() (optional operation). This call can only be made once per call to
		 * next or previous. It can be made only if add(E) has not been called after the
		 * last call to next or previous.
		 * 
		 * @throws IllegalStateException - if neither next nor previous have been
		 *                               called, or remove or add have been called after
		 *                               the last call to next or previous
		 */
		@Override
		public void remove() {
			modCheck();
			// checks if you are in the beginning of the list if so than, nextNode will
			// point to head and the current node will point to null in that case
			// illegalStateExcpetion() is thrown.
			if (current == null) {
				throw new IllegalStateException("call next()/previous() to remove the specified element.");
			}
			// if the current points to the head than the head will get updated.
			if (current == head) {
				head = head.getNext();
			}

			// general condition
			else {
				current.getPrev().setNext(current.getNext()); // if there is previous

			}
			// if current is equal to the tail than the tail gets reassigned.
			if (current == tail) {
				tail = tail.getPrev();
			}
			// general condition.
			else {

				current.getNext().setPrev(current.getPrev()); // if there is a next
			}
			if (nextNode == current) { // previous
				nextNode = nextNode.getNext(); // only for previous move
			} else {
				// decrementing index
				nextIndex--;

			}
			// reassigns current to null/ works like boolean
			current = null;
			count--;
			modCount++;
			itrModCount++;

		}

		/**
		 * Replaces the last element returned by next() or previous() with the specified
		 * element (optional operation). This call can be made only if neither remove()
		 * nor add(E) have been called after the last call to next or previous.
		 * 
		 * @throws IllegalStateException - if neither next nor previous have been
		 *                               called, or remove or add have been called after
		 *                               the last call to next or previous.
		 */

		@Override
		public void set(T e) {

			modCheck();
			// checks if current is null. works like a boolean condition.
			if (current == null) {
				throw new IllegalStateException("call nex() or previous() to set the specified element");
			}
			// reassigning user specified element to the current node we are on.
			current.setElement(e);
			modCount++;

		}

	}

}