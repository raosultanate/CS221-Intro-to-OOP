import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {

	private SLLNode<T> head; // point to the first node
	private SLLNode<T> tail; // points to the last node
	private int count; // keeps track of nodes in the LinkedList.
	private int modCount;

	public IUSingleLinkedList() {

		head = null;
		tail = null;
		count = 0;
		modCount = 0;

	}

	@Override
	public void addToFront(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else if (!isEmpty()) {
			newNode.setNext(head);
			head = newNode;

		}
		count++;
		modCount++;

	}

	@Override
	public void addToRear(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);
		if ((isEmpty())) {
			head = newNode;
			tail = newNode;

		} else if (!isEmpty()) {
			tail.setNext(newNode);
			tail = newNode;
		}

		count++;
		modCount++;
	}

	@Override
	public void add(T element) {

		addToRear(element);

	}

	@Override
	public void addAfter(T element, T target) {

		SLLNode<T> targetNode = head;
		while (targetNode != null && !targetNode.getElement().equals(target)) {
			targetNode = targetNode.getNext();
		}
		if (targetNode == null) {
			throw new NoSuchElementException();
		}
		SLLNode<T> newNode = new SLLNode<T>(element);
		newNode.setNext(targetNode.getNext());
		targetNode.setNext(newNode);
		if (targetNode == tail) {
			tail = newNode;
		}
		count++;
		modCount++;

	}

	@Override
	public void add(int index, T element) {

		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException();

		}
		SLLNode<T> current = head;
		SLLNode<T> newNode = new SLLNode<T>(element);

		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}

		if (index == count) {
			tail = newNode;
		}
		if (index == 0) {
			newNode.setNext(head);
			head = newNode;
		}

		else {

			SLLNode<T> next = current.getNext();
			current.setNext(newNode);
			newNode.setNext(next);
		}
		count++;
		modCount++;

	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = head.getElement();
		head = head.getNext();
		if (count == 1) {
			tail = null;
		}
		modCount++;
		count--;
		return retVal;
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T temp = tail.getElement();
		if (head == tail) {
			head = tail = null;

		} else {

			SLLNode<T> prev = head;

			while (prev.getNext() != tail) {
				prev = prev.getNext();
			}

			prev.setNext(null);
			tail = prev;
		}
		count--;
		modCount++;
		return temp;

	}

	@Override
	public T remove(T element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		boolean found = false;
		SLLNode<T> previous = null;
		SLLNode<T> current = head;

		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if (!found) {
			throw new NoSuchElementException();
		}

		if (size() == 1) { // only node
			head = tail = null;
		} else if (current == head) { // first node
			head = current.getNext();
		} else if (current == tail) { // last node
			tail = previous;
			tail.setNext(null);
		} else { // somewhere in the middle
			previous.setNext(current.getNext());
		}

		count--;
		modCount++;

		return current.getElement();
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();

		}

		if (index == 0) {
			return removeFirst();

		}
		if (index == count - 1) {
			return removeLast();
		}

		SLLNode<T> current = head;
		SLLNode<T> toBeRemoved = null;
		SLLNode<T> after = null;
		T temp = null;

		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}
		temp = current.getNext().getElement();
		toBeRemoved = current.getNext();
		after = current.getNext().getNext();
		current.setNext(after);
		toBeRemoved.setNext(null);

		count--;
		modCount++;
		return temp;
	}

	@Override
	public void set(int index, T element) {
		int trigger = 0;
		modCount++;

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();

		}
		if (index == 0) {
			trigger++;
			head.setElement(element);
		}
		if (index == count - 1) {
			trigger++;
			tail.setElement(element);
		}

		if (trigger == 0) {
			SLLNode<T> current = head;

			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			current.setElement(element);

		}

	}

	@Override
	public T get(int index) {
		SLLNode<T> temp1 = head;
		modCount++;

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();

		} else {
			for (int i = 0; i < index; i++) {
				temp1 = temp1.getNext();
			}

		}

		return temp1.getElement();
	}

	@SuppressWarnings("unused")
	@Override
	public int indexOf(T element) {
		modCount++;
		SLLNode<T> current = head;
		int index;
		for (int i = 0; i < count; i++) {

			if ((element.equals(current.getElement()))) {

				index = i;
				return index;

			}
			current = current.getNext();
		}
		return -1;

	}

	@Override
	public T first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		modCount++;
		return head.getElement();

	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		modCount++;

		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		modCount++;
		SLLNode<T> current = head;
		for (int i = 1; i <= count; i++) {

			if (current.getElement() == target) {
				return true;
			}
			current = current.getNext();
		}
		return false;

	}

	@Override
	public boolean isEmpty() {
		modCount++;
		if (count == 0) {
			return true;

		}
		return false;
	}

	@Override
	public int size() {
		modCount++;

		return count;
	}

	@Override
	public Iterator<T> iterator() {

		return new SLLIterator();

	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();

	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private SLLNode<T> nextNode;
		private int iterModCount;
		boolean canRemove;
		private int curIndex;

		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			// TODO
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			return (nextNode != null);
		}

		@Override
		public T next() {
			// TODO
			if (!hasNext())
				throw new NoSuchElementException();
			T retVal = nextNode.getElement();
			nextNode = nextNode.getNext();
			canRemove = true;
			curIndex++;
			return retVal;

		}

		@Override
		public void remove() {
			// TODO
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			if (!canRemove) {
				throw new IllegalStateException();
			}

			SLLNode<T> previous = null;
			SLLNode<T> current = head;

			for (int i = 0; i < curIndex - 1; i++) {
				previous = current;
				current = current.getNext();
			}

			if (current == head) {
				head = nextNode;
			} else if (current == tail) {
				tail = previous;
				previous.setNext(null);
			} else if (current == head && current == tail) {
				head = tail = null;
			} else {
				previous.setNext(current.getNext());
			}

			curIndex--;
			modCount++;
			count--;
			iterModCount = modCount;
			canRemove = false;

		}
	}

}
