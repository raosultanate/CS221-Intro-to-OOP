/**
 * 
 * @author danielrao
 *
 */
public abstract class Clock implements TimePiece {
	private ClockType clocktype;
	protected Time time;

	public static enum ClockType {
		natural, mechanical, digital, quantum
	}

	/**
	 * 
	 * @param clocktype takes a parameter of clocktype passed in from its
	 *                  subclasses.
	 * @param drift     takes a parameter of drift passed in from its subclasses.
	 */
	public Clock(ClockType clocktype, double drift) {
		this.clocktype = clocktype;
		this.time = new Time(drift);
		this.time.resetToStartTime(); // mid-night time

	}

	/**
	 * 
	 * @return returns the clocktype
	 */
	public ClockType getClockType() {
		return clocktype;
	}

	/**
	 * 
	 * @param clocktype sets clocktype to a new clock type in case of an update.
	 */
	public void setClockType(ClockType clocktype) {
		this.clocktype = clocktype;
	}

	/**
	 * resets the time to the start time.
	 */
	public void reset() {
		time.resetToStartTime();

	}

	/**
	 * increments the time of the clock by each second.
	 */
	public void tick() {
		time.incrementTime();

	}

	/**
	 * abstract method which is implemented by its subclasses.
	 */
	public abstract void display();

}
