import java.util.Comparator;
import java.util.ListIterator;
import java.util.Random;

import org.testng.Assert;

/**
 * Test case class for testing the methods of the Sort class,
 * using an instance of the IUDoubleLinkedList or WrappedList classes. 
 * @author CS221 
 */
public class Sort_TestCase
{
	// named elements for use in tests
	public static final Integer A = new Integer(1);
	public static final Integer B = new Integer(2);
	public static final Integer C = new Integer(3);
	public static final Integer D = new Integer(4);
	public static final Integer E = new Integer(5);
	public static final Integer F = new Integer(6);
	public static final Integer G = new Integer(7);
	public static final Integer H = new Integer(8);
	public static final Integer I = new Integer(9);
	public static final Integer J = new Integer(10);
	public static final Integer K = new Integer(11);
	public static final Integer L = new Integer(12);
	public static final Integer M = new Integer(13);
	public static final Integer N = new Integer(14);
	public static final Integer O = new Integer(15);
	public static final Integer P = new Integer(16);
	public static final Integer Q = new Integer(17);
	
	// max time for big list to pass tests
	public static final long MAX_TIME = 30000;   // in microseconds 
	// max value in random array 
	public static final int MAX_VALUE = 123; 
	// type of implementation using to store objects
	public enum ListType { wrapped, dll };  
	
	//*************** Test Methods ***********************************

	/**
	 * Tests sort method
	 * @param list - implementation of IndexedUnsortedList interface
	 * @param sortedList - implementation of IndexedUnsortedList interface
	 */
	public static void sort(IndexedUnsortedList<Integer> list, IndexedUnsortedList<Integer> sortedList) 
	{
		Sort.sort(list);
		Assert.assertTrue(equal(list, sortedList));
	}
	
	/**
	 * Tests sort method with a Comparator 
	 * @param list - implementation of IndexedUnsortedList interface
	 * @param sortedList - implementation of IndexedUnsortedList interface
	 * @param c - Comparator object 
	 */
	public static void sort(IndexedUnsortedList<Integer> list, IndexedUnsortedList<Integer> sortedList, Comparator<Integer> c) 
	{
		Sort.sort(list, c);
		Assert.assertTrue(equal(list, sortedList));
	}
	
	/**
	 * Tests runtime of sort method - 
	 * 		runs sort and determines whether it runs less than the max time allowed.  
	 * @param list - implementation of IndexedUnsortedList interface
	 * @param time - long value 
	 */
	public static void sort(IndexedUnsortedList<Integer> list) 
	{
		long startTime = System.currentTimeMillis(); 
		Sort.sort(list);
		long stopTime = System.currentTimeMillis(); 
		long duration = stopTime - startTime;
		Assert.assertTrue(duration < MAX_TIME);
	}
	
	/**
	 * Tests runtime of sort method with a Comparator - 
	 * runs sort with Comparator and determines whether it runs less than the max time allowed. 
	 * @param list - implementation of IndexedUnsortedList interface
	 * @param c - Comparator object 
	 * @param time - long value 
	 */
	public static void sort(IndexedUnsortedList<Integer> list, Comparator<Integer> c) 
	{
		long startTime = System.currentTimeMillis(); 
		Sort.sort(list, c);
		long stopTime = System.currentTimeMillis(); 
		long duration = stopTime - startTime;
		Assert.assertTrue(duration < MAX_TIME);
	}
	
	//*************** Utility Methods  ***********************************
	
	/**
	 * Compares contents, ordering of lists and returns whether they are the same
	 * @param list1 - implementation of IndexedUnsortedList interface
	 * @param list2 - implementation of IndexedUnsortedList interface
	 * @return same - true if lists contain same objects in same order, false otherwise
	 */
	private static boolean equal(IndexedUnsortedList<Integer> list1, IndexedUnsortedList<Integer> list2)
	{
		boolean same =true;
		ListIterator<Integer> itr1 = list1.listIterator();
		ListIterator<Integer> itr2 = list2.listIterator();
		
		while(itr1.hasNext() && same)
		{
			if(!itr1.next().equals(itr2.next()))
			{
				same = false; 
			}
		}	
		return same; 
	}
			
	/**
	 * Converts array of Integers to IndexedUnsortedList list with same elements 
	 * @param elements - array of Integers 
	 * @return list - IndexedUnsortedList implementation  
	 */
	public static IndexedUnsortedList<Integer> newList(Integer[] elements, ListType type) 
	{
		IndexedUnsortedList<Integer> list = null;
		
		if(type == ListType.wrapped)
		{
			list = new WrappedDLL<Integer>(); 
		}
//		else if(type == ListType.dll)
//		{
//			list = new IUDoubleLinkedList<Integer>();
//		}
		
		for (int i = 0; i < elements.length; i++) 
		{
			list.addToRear(elements[i]);
		}
		return list;
	}
	
	/**
	 * Creates a IndexedUnsortedList list of given size with random Integers. 
	 * @param size - int value  
	 * @return list - IndexedUnsortedList implementation  
	 */
	public static IndexedUnsortedList<Integer> newList(int size, ListType type) 
	{
		IndexedUnsortedList<Integer> list = null;
		
		if(type == ListType.wrapped)
		{
			list = new WrappedDLL<Integer>(); 
		}
//		else if(type == ListType.dll)
//		{
//			list = new IUDoubleLinkedList<Integer>();
//		}
		
		Random rand = new Random(MAX_VALUE);
		for (int i = 0; i < size; i++) 
		{
			list.addToRear(new Integer(rand.nextInt()));
		}
		return list;
	}

	/**
	 * Returns an instance of the ReverseComparator
	 * @return - ReverseComparator object 
	 */
	public static Comparator<Integer> comparator()
	{
		return new Sort_TestCase.ReverseComparator<>(); 
	}
	
	/**
	 * Reverses the ordering defined by the class. 
	 * @param <T extends Comparable<T>> - type of objects being compared 
	 */
	private static class ReverseComparator<T extends Comparable<T>> implements Comparator<T> 
	{
		@Override
		public int compare(T o1, T o2) 
		{
			return -(o1.compareTo(o2));
		}
	}
			
}
