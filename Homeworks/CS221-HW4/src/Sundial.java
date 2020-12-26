import java.text.DecimalFormat;

public class Sundial extends Clock {
	/**
	 * Constructs the Sundial and passes its clocktype and drift from the subclass
	 * constructor to the parent class constructor
	 */
	public Sundial() {
		super(ClockType.natural, 0);
		
		
	}
	/**
	 * displays time and drift based on its clocktype.
	 */
	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("0.00");
		double totalDrift = time.getTotalDrift();
		
		System.out.println(getClockType() + ", Sundial Clock, " + " time " + "["+time.formattedTime()+"], " + "total drift = " + df.format(totalDrift));

	}



}
