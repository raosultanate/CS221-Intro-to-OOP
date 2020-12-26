import java.text.DecimalFormat;
/**
 * 
 * @author danielrao
 *
 */
public class AtomicClock extends Clock {
/**
 * Constructs the atomic clock and passes its clocktype and drift from the subclass constructor to the parent class constructor
 */
	public AtomicClock() {
		super(ClockType.quantum, 0);

	}

	/**
	 * displays time and drift based on its clocktype.
	 */
	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("0.00");
		double totalDrift = time.getTotalDrift();
		System.out.println(getClockType() + ", Atomic Clock, " + "time " + "[" + time.formattedTime() + "], "
				+ "total drift = " + df.format(totalDrift));

	}

}
