package system;

public class CPU {
	
	private boolean isInterrupted = false;
	private boolean isRunning = false;
	private Clock clock = new Clock();
	
	/** Clock speed is used to simulate heterogeneous processors. */
	private double clockSpeed; 
		
	/**
	 * Initialize the CPU with a clock speed.
	 * @param speed
	 */
	public CPU(double clockSpeed) {
		this.clockSpeed = clockSpeed;
	}

	/**
	 * Method to execute a process for a given time.
	 * @param process - Process to execute.
	 * @return double - Execution time.
	 */
	public void execute(Process process, double burst) {
		isInterrupted = isRunning = false;
				
		double elapsed = 0;
		do {
			elapsed = clock.delay(burst * clockSpeed);
			break;
		} while(!isInterrupted);
		
		process.setTurnaroundTime(System.nanoTime() - process.getInitialTime());
		
		elapsed = (elapsed >= 0.01) ? (elapsed/clockSpeed)/1000000 : 0;
		
		if(process.getRemainingTime() > elapsed) {
			process.subtractRemainingTime(elapsed);
		} else {
			process.subtractRemainingTime(process.getRemainingTime());
		}
	}
	
	/** 
	 * Method to check if the CPU is running.
	 * @return 
	 */
	public boolean isRunning() {
		return isRunning;
	}
	
	/**
	 * Method to return the clock speed of the CPU.
	 */
	public double getClockSpeed() {
		return clockSpeed;
	}
	
	/**
	 * Method to check for an interruption.
	 */
	public boolean isInterrupted() {
		return isInterrupted;
	}
	
	/**
	 * Method to interrupt a process execution.
	 */
	public void interrupt() {
		this.isInterrupted = true;
	}
}
