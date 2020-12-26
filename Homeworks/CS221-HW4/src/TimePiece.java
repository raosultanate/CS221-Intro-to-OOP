/**
 * 
 * @author danielrao 
 * This interface gets implemented in the Clock class which
 * dictates the methods to be implemented.
 */
public abstract interface TimePiece {

	public void reset();

	/**
	 * This method will dictate the clock to be reset.
	 */

	public void tick();

	/**
	 * This method will dictate the clock to tick one second in the clock.
	 */

	public void display();
	/**
	 * This method will dictate the subclasses of the clock class to implement its
	 * own display method depending on its drift and clock type.
	 */

}
