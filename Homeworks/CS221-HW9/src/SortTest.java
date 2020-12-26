
import java.util.Comparator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for Sort class.
 * @author CS221 
 */

public class SortTest 
{		
	// arrays of reverse sorted lists
	public static final Integer[] SortedArray_1 = {Sort_TestCase.A};
	public static final Integer[] SortedArray_2 = {Sort_TestCase.A, Sort_TestCase.B};
	public static final Integer[] SortedArray_2_DupA = {Sort_TestCase.A, Sort_TestCase.A};
	public static final Integer[] SortedArray_3 = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.C};
	public static final Integer[] SortedArray_3_DupA = {Sort_TestCase.A, Sort_TestCase.A, Sort_TestCase.B};
	public static final Integer[] SortedArray_3_DupB = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.B};
	public static final Integer[] SortedArray_4 = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.C, Sort_TestCase.D};
	public static final Integer[] SortedArray_4_DupC = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.C, Sort_TestCase.C};
	public static final Integer[] RevSortedArray_2 = {Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] RevSortedArray_2_DupA = {Sort_TestCase.A, Sort_TestCase.A};
	public static final Integer[] RevSortedArray_3 = {Sort_TestCase.C, Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] RevSortedArray_3_DupA = {Sort_TestCase.B, Sort_TestCase.A, Sort_TestCase.A};
	public static final Integer[] RevSortedArray_3_DupB = {Sort_TestCase.B, Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] RevSortedArray_4 = {Sort_TestCase.D, Sort_TestCase.C, Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] RevSortedArray_4_DupC = {Sort_TestCase.C, Sort_TestCase.C, Sort_TestCase.B, Sort_TestCase.A};
	
	// arrays of lists
	public static final Integer[] EmptyArray = {}; 
	public static final Integer[] Array_A = {Sort_TestCase.A};
	public static final Integer[] Array_AB = {Sort_TestCase.A, Sort_TestCase.B};
	public static final Integer[] Array_BA = {Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] Array_AA = {Sort_TestCase.A, Sort_TestCase.A};
	public static final Integer[] Array_ACB = {Sort_TestCase.A, Sort_TestCase.C, Sort_TestCase.B};
	public static final Integer[] Array_ABC = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.C};
	public static final Integer[] Array_BAC = {Sort_TestCase.B, Sort_TestCase.A, Sort_TestCase.C};
	public static final Integer[] Array_BCA = {Sort_TestCase.B, Sort_TestCase.C, Sort_TestCase.A};
	public static final Integer[] Array_CAB = {Sort_TestCase.C, Sort_TestCase.A, Sort_TestCase.B};
	public static final Integer[] Array_CBA = {Sort_TestCase.C, Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] Array_AAB = {Sort_TestCase.A, Sort_TestCase.A, Sort_TestCase.B};
	public static final Integer[] Array_ABA = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] Array_BAA = {Sort_TestCase.B, Sort_TestCase.A, Sort_TestCase.A};
	public static final Integer[] Array_ABB = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.B};
	public static final Integer[] Array_BAB = {Sort_TestCase.B, Sort_TestCase.A, Sort_TestCase.B};
	public static final Integer[] Array_BBA = {Sort_TestCase.B, Sort_TestCase.B, Sort_TestCase.A};
	public static final 	Integer[] Array_BDAC = {Sort_TestCase.B, Sort_TestCase.D, Sort_TestCase.A, Sort_TestCase.C};
	public static final Integer[] Array_CADB = {Sort_TestCase.C, Sort_TestCase.A, Sort_TestCase.D, Sort_TestCase.B};
	public static final 	Integer[] Array_DCBA = {Sort_TestCase.D, Sort_TestCase.C, Sort_TestCase.B, Sort_TestCase.A};
	public static final Integer[] Array_ABCC = {Sort_TestCase.A, Sort_TestCase.B, Sort_TestCase.C, Sort_TestCase.C};
	public static final Integer[] Array_CACB = {Sort_TestCase.C, Sort_TestCase.A, Sort_TestCase.C, Sort_TestCase.B};
	public static final Integer[] Array_CCBA = {Sort_TestCase.C, Sort_TestCase.C, Sort_TestCase.B, Sort_TestCase.A};

	// Sorted list for searching with indexes
	private static final Object[][] INDEXED_SORTED_ELEMENTS = { 	{0, Sort_TestCase.A}, 
																											{1, Sort_TestCase.B}, 
																											{2, Sort_TestCase.C}, 
																											{3, Sort_TestCase.D}, 
																											{4, Sort_TestCase.E}, 
																											{5, Sort_TestCase.F}, 
																											{6, Sort_TestCase.G}, 
																											{7, Sort_TestCase.H}, 
																											{8, Sort_TestCase.I}, 
																											{9, Sort_TestCase.J}, 
																											{10, Sort_TestCase.K}, 
																											{11, Sort_TestCase.L}, 
																											{12, Sort_TestCase.M}, 
																											{13, Sort_TestCase.N}
																									   };
	// Reverse-sorted list for searching with indexes
	private static final Object[][] INDEXED_REVERSE_SORTED_ELEMENTS = { 		{0, Sort_TestCase.P},
																															{1, Sort_TestCase.O}, 
																															{2, Sort_TestCase.N},
																															{3, Sort_TestCase.M},
																															{4, Sort_TestCase.L},
																															{5, Sort_TestCase.K},
																															{6, Sort_TestCase.J},
																															{7, Sort_TestCase.I},
																															{8, Sort_TestCase.H}, 
																															{9, Sort_TestCase.G},
																															{10, Sort_TestCase.F},
																															{11, Sort_TestCase.E},
																															{12, Sort_TestCase.D}, 
																															{13, Sort_TestCase.C}
																													  };

	// size of a not so big list 	
	private static final int BIG_SIZE = 20000;
	
	// comparator for testing, sorts elements in descending order  
	private Comparator<Integer> comparator; 
	// name of list implementation 
	private Sort_TestCase.ListType type; 
	
	/**
	 * Set list implementation and 
	 * initialize Comparator for tests.
	 * @param listType - String representation of list type 
	 */
	@BeforeTest
	@Parameters("listType")		
	public void init(String listType)
	{
		if(listType.equals("WrappedDLL"))
		{
			type =Sort_TestCase.ListType.wrapped;
		}
		else if(listType.equals("DoubleLinkedList"))
		{
			type = Sort_TestCase.ListType.dll;
		}
		comparator = Sort_TestCase.comparator(); 
	}
	
	/**
	 * Test: sort() - try to sort an empty list. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_newList()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(EmptyArray, type), Sort_TestCase.newList(EmptyArray, type));
	}
	
	/**
	 * Test: sort() - try to sort an empty list using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_newList()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(EmptyArray, type), Sort_TestCase.newList(EmptyArray, type), comparator);		
	}

	/**
	 * Test: sort() - sort a list with one element (A). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_A()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_A, type), Sort_TestCase.newList(SortedArray_1, type));
	}
	
	/**
	 * Test: sort() - sort a list with one element (A) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_A()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_A, type), Sort_TestCase.newList(SortedArray_1, type), comparator);		
	}
	
	/**
	 * Test: sort() - sort a list with two elements (AB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_AB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_AB, type), Sort_TestCase.newList(SortedArray_2, type));
	}

	/**
	 * Test: sort() - sort a list with two elements (AB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_AB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_AB, type), Sort_TestCase.newList(RevSortedArray_2, type), comparator);
	}
	
	/**
	 * Test: sort() - sort a list with two elements (BA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BA, type), Sort_TestCase.newList(SortedArray_2, type));
	}
	
	/**
	 * Test: sort() - sort a list with two elements (BA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BA, type), Sort_TestCase.newList(RevSortedArray_2, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with two repeated elements (AA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_AA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_AA, type), Sort_TestCase.newList(RevSortedArray_2_DupA, type));
	}
	
	/**
	 * Test: sort() - sort a list with two repeated elements (AA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_AA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_AA, type), Sort_TestCase.newList(RevSortedArray_2_DupA, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (ABC). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_ABC()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_ABC, type), Sort_TestCase.newList(SortedArray_3, type));
	}
	
	/**
	 * Test: sort() - sort a list with three elements (ABC) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_ABC()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_ABC, type), Sort_TestCase.newList(RevSortedArray_3, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (ACB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_ACB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_ACB, type), Sort_TestCase.newList(SortedArray_3, type));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (ACB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_ACB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_ACB, type), Sort_TestCase.newList(RevSortedArray_3, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BAC). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BAC()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BAC, type), Sort_TestCase.newList(SortedArray_3, type));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (BAC) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BAC()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BAC, type), Sort_TestCase.newList(RevSortedArray_3, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BCA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BCA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BCA, type), Sort_TestCase.newList(SortedArray_3, type));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (BCA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BCA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BCA, type), Sort_TestCase.newList(RevSortedArray_3, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (CAB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CAB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CAB, type), Sort_TestCase.newList(SortedArray_3, type));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (CAB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CAB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CAB, type), Sort_TestCase.newList(RevSortedArray_3, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (CBA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CBA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CBA, type), Sort_TestCase.newList(SortedArray_3, type));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (CBA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CBA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CBA, type), Sort_TestCase.newList(RevSortedArray_3, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (AAB) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_AAB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_AAB, type), Sort_TestCase.newList(SortedArray_3_DupA, type));

	}

	/**
	 * Test: sort() - sort a list with three elements (AAB) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_AAB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_AAB, type), Sort_TestCase.newList(RevSortedArray_3_DupA, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (ABA) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_ABA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_ABA, type), Sort_TestCase.newList(SortedArray_3_DupA, type));
	}

	/**
	 * Test: sort() - sort a list with three elements (ABA) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_ABA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_ABA, type), Sort_TestCase.newList(RevSortedArray_3_DupA, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BAA) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BAA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BAA, type), Sort_TestCase.newList(SortedArray_3_DupA, type));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (BAA) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BAA()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BAA, type), Sort_TestCase.newList(RevSortedArray_3_DupA, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BAB) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BAB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BAB, type), Sort_TestCase.newList(SortedArray_3_DupB, type));

	}

	/**
	 * Test: sort() - sort a list with three elements (BAB) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BAB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BAB, type), Sort_TestCase.newList(RevSortedArray_3_DupB, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with four elements (BDAC). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BDAC()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BDAC, type), Sort_TestCase.newList(SortedArray_4, type));

	}

	/**
	 * Test: sort() - sort a list with four elements (BDAC) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BDAC()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_BDAC, type), Sort_TestCase.newList(RevSortedArray_4, type), comparator);
	}

	/**
	 * Test: sort() - sort a list with four elements (CADB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CADB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CADB, type), Sort_TestCase.newList(SortedArray_4, type));

	}
	
	/**
	 * Test: sort() - sort a list with four elements (CADB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CADB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CADB, type), Sort_TestCase.newList(RevSortedArray_4, type), comparator);
	}
	
	/**
	 * Test: sort() - sort a list with four elements (CACB) with repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CACB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CACB, type), Sort_TestCase.newList(SortedArray_4_DupC, type));

	}
	
	/**
	 * Test: sort() - sort a list with four elements (CACB) with repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CACB()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(Array_CACB, type), Sort_TestCase.newList(RevSortedArray_4_DupC, type), comparator);
	}
	
	/**
	 * Test: sort() - sort a big list with randomly generated elements
	 * and runs in less than 15 milliseconds 
	 * Expected Result: No exceptions. 
	 */
	@Test
	public void testSort_BigList()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(BIG_SIZE, type));		
	}
	
	/**
	 * Test: sort() - sort a big list with randomly generated elements using a Comparator
	 * and runs in less than 15 milliseconds. 
	 * Expected Result: No exceptions.  
	 */
	@Test
	public void testSortComparator_BigList()
	{
		Sort_TestCase.sort(Sort_TestCase.newList(BIG_SIZE, type), comparator);	
	}
	
	
	//********** Data Providers ***************************
	/**
	 * Data: index of an element, and the Integer element at that location,
	 * sorted in ascending order.
	 * 
	 * @return 2D array of indexes, Integer elements at that index 
	 */
	   @DataProvider
	   public static Object[][] indexedSortedElements() 
	   {
	      return INDEXED_SORTED_ELEMENTS; 
	   }
	
	/**
	 * Data: index of an element, and the Integer element at that location,
	 * sorted in descending order.
	 * 
	 * @return 2D array of indexes, Integer elements at that index 
	 */
	   @DataProvider
	   public static Object[][] indexedReverseSortedElements() 
	   {
	      return INDEXED_REVERSE_SORTED_ELEMENTS; 
	   }
}
