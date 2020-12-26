import java.text.DecimalFormat;

public class WristWatch extends Clock {
	/**
	 * Constructs the Sundial and passes its clocktype and drift from the subclass
	 * constructor to the parent class constructor
	 */
	public WristWatch() {
		super(ClockType.digital, 0.000034722);
		// TODO Auto-generated constructor stub
	}

	/**
	 * displays time and drift based on its clocktype.
	 */
	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("0.00");
		double totalDrift = time.getTotalDrift();
		System.out.println(getClockType() + ", Wristwatch Clock, " + " time " + "[" + time.formattedTime() + "], "
				+ "total drift = " + df.format(totalDrift));
	}

}
