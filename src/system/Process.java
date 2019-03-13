package system;

/**
 * The Process class simulates the structure of a process
 * in an operating system.
 * 
 * @author Bailey Granam
 */
public class Process {
	
	/* Variables */
	int processId;
	double burstTime      = 0;
	double initialTime    = 0;
	double lastExecuted   = 0;
	double arrivalTime    = 0;
	double priority       = 0;
	double waitTime       = 0;
	double responseTime   = -1;
	double turnaroundTime = 0;
	double remainingTime  = 0;
	
	/**
	 * Constructor to initialize the process.
	 * @param processId - ID of the process.
	 * @param burstTime - Burst time of the process.
	 * @param initialTime - The initial time the process was created.
	 * @param arrivalTime - Arrival time of the process.
	 */
	public Process(int processId, double burstTime, double arrivalTime, double initialTime, double priority) {
		this.processId = processId;
		this.burstTime = burstTime;
		this.remainingTime = burstTime;
		this.arrivalTime = arrivalTime;
		this.initialTime = initialTime;
		this.lastExecuted = initialTime;
		this.priority = priority;
	} 
	
	/**
	 * Returns the process's burst time.
	 * @return burstTime - The burst time of the process.
	 */
	public double getBurstTime() {
		return burstTime;
	}
	
	/**
	 * Returns the process's initial time.
	 * @return initalTime - The initial time of creation of the process.
	 */
	public double getInitialTime() {
		return initialTime;
	}
	
	/**
	 * Returns the process's arrival time.
	 * @return arrivalTime - The arrival time of the process.
	 */
	public double getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * Returns the process's priority.
	 * @return priority - The priority of the process.
	 */
	public double getPriority() {
		return priority;
	}
	
	/**
	 * Set the priority of the process.
	 * @param priority - The priority to set on the process.
	 */
	public void setPriority(double priority) {
		this.priority = priority;
	}
	
	/**
	 * Returns the process's wait time.
	 * @return waitTime - The wait time of the process.
	 */
	public double getWaitTime() {
		return waitTime;
	}
	
	/**
	 * Add wait time to the process.
	 * @param waitTime - The wait time to add to the process.
	 */
	public void addWaitTime(double waitTime) {
		this.waitTime += waitTime;
	}
	
	/**
	 * Returns the process's response time.
	 * @return responseTime - The response time of the process.
	 */
	public double getResponseTime() {
		return responseTime;
	}
	
	/**
	 * Set the response time of the process.
	 * @param responseTime - The response time to set on the process.
	 */
	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}
	
	/**
	 * Returns the process's turn around time.
	 * @return turnaroundTime - The turn around time of the process.
	 */
	public double getTurnaroundTime() {
		return turnaroundTime;
	}
	
	/**
	 * Set the turn around time of the process.
	 * @param turnaroundTime - The turn around time to set on the process.
	 */
	public void setTurnaroundTime(double turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
	
	/**
	 * Returns the process's remaining time to execute.
	 * @return remainingTime - The remaining time of the process to execute.
	 */
	public double getRemainingTime() {
		return remainingTime;
	}
	
	/**
	 * Subtract remaining time from the process.
	 * @param reaminingTime - The remaining time to subtract from the process.
	 */
	public void subtractRemainingTime(double remainingTime) {
		this.remainingTime -= remainingTime;
	}
	
	/**
	 * Method to set the reference point for the initial time.
	 * @param initialTime - The system time of which the process arrived.
	 */
	public void setInitialTime(double initialTime) {
		this.initialTime = initialTime;
	}
		
	/**
	 * Method to set the time the process last executed.
	 * @param lastExecuted - The time the process last executed.
	 */
	public void setLastExecuted(double lastExecuted) {
		this.lastExecuted = lastExecuted;
	}
	
	/**
	 * Method to get the time the process last executed.
	 * @return	lastExecuted - The time the process last executed.
	 */
	public double getLastExecuted() {
		return lastExecuted;
	}
	
	/**
	 * Returns the process's information.
	 * @return String - The information about the process.
	 */
	public String toString() {
		
		if(isError()) {
			System.out.println("Error: Negative values encountered.");
			System.exit(0);
		}
		
		return (
				" ID: " + processId + 
			    " Burst: " + burstTime + 
			    " Arrival: " + arrivalTime/1000000 + 
			    " Priority: " + priority +
			    " Wait: " + waitTime/1000000 +
			    " Remaining: " + remainingTime +
			    " Response: " + responseTime/1000000 +
			    " Turnaround: " + turnaroundTime/1000000
			    );
	}
	
	/**
	 * Checks each variable to see if it is negative. If a negative 
	 * variable occurs then a true boolean is returned, otherwise false.
	 * @return boolean 
	 */
	public boolean isError() {
		return (
				burstTime < 0 || 
				arrivalTime < 0 || 
				priority < 0 || 
				waitTime < 0 || 
				responseTime < 0 || 
				turnaroundTime < 0 || 
				remainingTime < 0
				);
	}

}