import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing for SimpleSet interface implementation: 
 * Tests for Scenario: no set -> constructor -> [ ]
 *
 * @author CS221
 */
public class TestSimpleSet_1
{
	// SimpleSet running tests on 
	private SimpleSet<Character> set; 
	// elements for testing 
	private Character ELEMENT = SetTestCase.E; 
	private Character INVALID_ELEMENT = SetTestCase.F; 
	
	// variable for tests 
	private final int SIZE = 0; 
	
	/**
	 * Reinitializes set before each test. 
	 */
	@BeforeMethod 
	public void initializeSet()
	{
		set = new ArraySet<Character>(); 
	}

	/**
	 * Test: toString() - String representation of set 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testToString()
	{
		SetTestCase.toString(set);
	}

	/**
	 * Test: size() - number of elements in set 
	 * Expected Result: SIZE (0)
	 */
	@Test
	public void testSize()
	{
		SetTestCase.size(set, SIZE);
	}

	/**
	 * Test: isEmpty() - whether set is empty
	 * Expected Result: False
	 */
	@Test
	public void testIsEmpty()
	{
		SetTestCase.isEmpty(set, true);
	}

	/**
	 * Test: contains(INVALID_ELEMENT) 
	 * - whether set contains element (F) not in set
	 * Expected Result: False
	 */
	@Test
	public void testContains_invalidElement()
	{
		SetTestCase.contains(set, INVALID_ELEMENT, false);
	}
	
	/**
	 * Test: add(ELEMENT) - adds element (E) to set
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testAdd_element()
	{
		SetTestCase.add(set, ELEMENT);
	}
	
	/**
	 * Test: remove(INVALID_ELEMENT) 
	 * - tries to remove element (F) not in set from set
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testRemove_invalidElement()
	{
		SetTestCase.remove(set, INVALID_ELEMENT);
	}
} // end SimpleSetTest_1
