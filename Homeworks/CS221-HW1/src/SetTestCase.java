import org.testng.Assert;

/**
 * A test case class for implementations of the SimpleSet ADT. 
 * 
 * @author CS221
 */
public class SetTestCase 
{
	// named elements for use in tests
	public static final Character A = new Character('A');
	public static final Character B = new Character('B');
	public static final Character C = new Character('C');
	public static final Character D = new Character('D');
	public static final Character E = new Character('E');
	public static final Character F = new Character('F');
	
	/**
	 * Tests add() method on given set. 
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param element - Character to add
	 */
	public static void add(SimpleSet<Character> set, Character element)
	{
		set.add(element);
	}

	/**
	 * Tests remove() method on given set.
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param element - Character to remove
	 */
	public static void remove(SimpleSet<Character> set, Character element)
	{
		Character retVal = set.remove(element);
		Assert.assertEquals(retVal, element);
	}

	/**
	 * Tests contains() method on a given set. 
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param element - Character value
	 * @param expectedResult - boolean value
	 */
	public static void contains(SimpleSet<Character> set, Character element, boolean expectedResult)
	{
		boolean result = set.contains(element);
		Assert.assertEquals(result, expectedResult);
	}

	/**
	 * Tests isEmpty() method on a given set.
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param expectedResult - boolean value
	 */
	public static void isEmpty(SimpleSet<Character> set, boolean expectedResult)
	{
		boolean result = set.isEmpty();
		Assert.assertEquals(result, expectedResult);
	}

	/**
	 * Tests size() method on a given set.
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param expectedSize - int value
	 */
	public static void size(SimpleSet<Character> set, int expectedSize)
	{
		int size = set.size(); 
		Assert.assertEquals(size, expectedSize);
	}

	/**
	 * Tests toString() method on given set, confirms that default address output has been overridden
	 * 
	 * @param set - implementation of the SimpleSet interface
	 */
	public static void toString(SimpleSet<Character> set)
	{
		String str = set.toString();
		char lastChar = str.charAt(str.length() - 1);
		Assert.assertFalse(str.contains("@") && !str.contains(" ") && Character.isLetter(str.charAt(0)) && (Character.isDigit(lastChar) || (lastChar >= 'a' && lastChar <= 'f')));
	}

}// end class SetTestCase
