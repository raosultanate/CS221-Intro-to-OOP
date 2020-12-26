import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator. As
 * written uses Mergesort algorithm.
 *
 * @author danielraos
 */
public class Sort {
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. As
	 * configured, uses WrappedDLL. Must be changed if using your own
	 * IUDoubleLinkedList class.
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() {
		return new WrappedDLL<T>(); // TODO: replace with your IUDoubleLinkedList for
		// extra-credit

	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface using
	 * compareTo() method defined by class of objects in list. DO NOT MODIFY THIS
	 * METHOD
	 * 
	 * @param <T>  The class of elements in the list, must extend Comparable
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 * @see IndexedUnsortedList
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) {
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface using given
	 * Comparator. DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>  The class of elements in the list
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 * @param c    The Comparator used
	 * @see IndexedUnsortedList
	 */
	public static <T> void sort(IndexedUnsortedList<T> list, Comparator<T> c) {
		mergesort(list, c);
	}

	/**
	 * Mergesort algorithm to sort objects in a list that implements the
	 * IndexedUnsortedList interface, using compareTo() method defined by class of
	 * objects in list. DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>  The class of elements in the list, must extend Comparable
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 */
	static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list) {

		if (list.size() <= 1) { // checks if the list size at base Case
		} else {
			int size = list.size(); // saves the size initially and at each recursion.
			int halfSize = list.size() / 2 - 1; // halfSize of the list, especially if uneven
			WrappedDLL<T> rightHalf = new WrappedDLL<T>(); // creates rightHalf initially and at each
																			// recursion.
			WrappedDLL<T> leftHalf = new WrappedDLL<T>(); // creates leftHalf initially and at each
																			// recursion.
			// loop runs to empty the list.
			for (int i = 0; i < size; i++) {
				if (i <= halfSize)
					leftHalf.add(list.remove(0)); // elements gets stored at left.
				else
					rightHalf.add(list.remove(0)); // elements gets stored at right
			}
			mergesort(rightHalf); // recursive call at right half of the list.
			mergesort(leftHalf); // recursive call at lefthalf of the list.

			// while loops helps in putting all the elements to the list in the sorted
			// order.
			while (!leftHalf.isEmpty() || !rightHalf.isEmpty()) {

				if (rightHalf.isEmpty()) // when rightHalf is empty
					list.add(leftHalf.remove(0));
				else if (leftHalf.isEmpty())
					list.add(rightHalf.remove(0)); // when lefttHalf is empty
				else if (leftHalf.get(0).compareTo(rightHalf.get(0)) < 0)
					list.add(leftHalf.remove(0)); // when leftHalf is smaller than rightHalf
				else if (leftHalf.get(0).compareTo(rightHalf.get(0)) == 0) {
					list.add(rightHalf.remove(0)); // when the elements are same.
					list.add(leftHalf.remove(0));
				} else
					list.add(rightHalf.remove(0)); // when rightHalf is smaller than leftHalf

			}

		}
	}

	/**
	 * Mergesort algorithm to sort objects in a list that implements the
	 * IndexedUnsortedList interface, using the given Comparator. DO NOT MODIFY THIS
	 * METHOD SIGNATURE
	 * 
	 * @param <T>  The class of elements in the list
	 * @param list The list to be sorted, implements IndexedUnsortedList interface
	 * @param c    The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c) {

		if (list.size() <= 1) { // checks if the list size at base Case
		} else {
			int size = list.size(); // saves the size initially and at each recursion.
			int halfSize = list.size() / 2 - 1; // halfSize of the list, especially if uneven
			WrappedDLL<T> rightHalf = new WrappedDLL<T>(); // creates rightHalf initially and at each
																			// recursion.
			WrappedDLL<T> leftHalf = new WrappedDLL<T>(); // creates leftHalf initially and at each
																			// recursion.
			// loop runs to empty the list.
			for (int i = 0; i < size; i++) {
				if (i <= halfSize)
					leftHalf.add(list.remove(0)); // elements gets stored at left.
				else
					rightHalf.add(list.remove(0)); // elements gets stored at right
			}
			mergesort(rightHalf, c); // recursive call at right half of the list.
			mergesort(leftHalf, c); // recursive call at lefthalf of the list.

			// while loops helps in putting all the elements to the list in the sorted
			// order.
			while (!leftHalf.isEmpty() || !rightHalf.isEmpty()) {
				if (rightHalf.isEmpty())
					list.add(leftHalf.remove(0)); // when rightHalf is empty
				else if (leftHalf.isEmpty())
					list.add(rightHalf.remove(0)); // when lefttHalf is empty
				else if (c.compare(leftHalf.get(0), rightHalf.get(0)) < 0)
					list.add(leftHalf.remove(0));  // when leftHalf is smaller than rightHalf
				else if (c.compare(leftHalf.get(0), rightHalf.get(0)) == 0) {
					list.add(rightHalf.remove(0)); // when the elements are same.
					list.add(leftHalf.remove(0));  // when the elements are same.
				} else
					list.add(rightHalf.remove(0));  // when rightHalf is smaller than leftHalf

			}
		}

	}
}
