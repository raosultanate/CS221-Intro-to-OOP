import java.util.Iterator;

/**
 * 
 * @author danielrao
 *
 */
public class ClockSimulation {
	
	private final static long day = 86400;
	private final static long week = 604800;
	private final static long month = 2592000;
	private final static long year = 31536000;
	private static Bag<Clock> bag;

	public static void main(String[] args) {

		bag = new Bag<Clock>();
		bag.setCapacity(5);

		bag.add(new Sundial());
		bag.add(new AtomicClock());
		bag.add(new CuckooClock());
		bag.add(new GrandfatherClock());
		bag.add(new WristWatch());

		simulateTime();

	}

	public static void simulateTime() {
		/**
		 * processes and displays time before the time starts
		 */

		System.out.println("Times before start: ");
		Iterator<Clock> iterate0 = bag.iterator();
		while (iterate0.hasNext()) {
			Clock clock = iterate0.next();

			clock.display();
			clock.reset();
		}
		/**
		 * processes and displays time after one day.
		 */
		System.out.println("");
		System.out.println("After one day: ");
		Iterator<Clock> iterate1 = bag.iterator();
		while (iterate1.hasNext()) {
			Clock clock = iterate1.next();

			for (int i = 0; i <= day; i++) {
				clock.tick();

			}
			clock.display();
			clock.reset();
		}
		/**
		 * processes and displays time after one day.
		 */
		System.out.println("");
		System.out.println("After one week: ");
		Iterator<Clock> iterate2 = bag.iterator();
		while (iterate2.hasNext()) {
			Clock clock = iterate2.next();

			for (int i = 0; i <= week; i++) {
				clock.tick();

			}
			clock.display();
			clock.reset();
		}
		/**
		 * processes and displays time after one month.
		 */
		System.out.println("");
		System.out.println("After one month: ");
		Iterator<Clock> iterate3 = bag.iterator();
		while (iterate3.hasNext()) {
			Clock clock = iterate3.next();

			for (int i = 0; i <= month; i++) {
				clock.tick();

			}
			clock.display();
			clock.reset();
		}
		/**
		 * processes and displays time after one year.
		 */
		System.out.println("");
		System.out.println("After one year: ");
		Iterator<Clock> iterate4 = bag.iterator();
		while (iterate4.hasNext()) {
			Clock clock = iterate4.next();

			for (int i = 0; i <= year; i++) {
				clock.tick();

			}
			clock.display();
			clock.reset();
		}

	}

}
