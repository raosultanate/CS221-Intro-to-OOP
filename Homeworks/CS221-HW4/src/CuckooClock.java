import java.text.DecimalFormat;

/**
 * 
 * @author danielrao
 *
 */
public class CuckooClock extends Clock {
	/**
	 * Constructs the Cuckooclock and passes its clocktype and drift from the
	 * subclass constructor to the parent class constructor
	 */
	public CuckooClock() {
		super(ClockType.mechanical, 0.000694444);
		// TODO Auto-generated constructor stub
	}

	/**
	 * displays time and drift based on its clocktype.
	 */
	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("0.00");
		double totalDrift = time.getTotalDrift();
		System.out.println(getClockType() + ", Cuckoo Clock, " + "time, " + "[" + time.formattedTime() + "], "
				+ "total drift = " + df.format(totalDrift));

	}
}
