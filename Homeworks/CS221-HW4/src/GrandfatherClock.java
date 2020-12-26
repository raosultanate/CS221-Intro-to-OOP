import java.text.DecimalFormat;

public class GrandfatherClock extends Clock {
	/**
	 * Constructs the Grandfatherclock and passes its clocktype and drift from the subclass constructor to the parent class constructor
	 */
	public GrandfatherClock() {
		super(ClockType.mechanical, 0.000347222);
		// TODO Auto-generated constructor stub
	}

	/**
	 * displays time and drift based on its clocktype.
	 */
	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("0.00");
		double totalDrift = time.getTotalDrift();
		System.out.println(getClockType() + ", Grandfather Clock, " + "time, " + "[" + time.formattedTime() + "], "
				+ "total drift = " + df.format(totalDrift));
	}

}