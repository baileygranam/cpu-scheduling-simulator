package system;

public class Clock {
	
	private boolean isDelay = false;
	private double elapsedTime = 0;
	
	/**
	 * Method to return whether a delay is currently active.
	 * @return
	 */
	public boolean isDelay() {
		return isDelay;
	}
	
    /**
     * Method to cause a delay.
     * @param milliseconds - Number of milliseconds to delay.
     * @return elapsedTime - The time the delay lasted.
     */
    public double delay(double milliseconds) {
    	isDelay = true;
    	elapsedTime = 0;
    	
    	if(milliseconds <= 0) return 0;
    	
    	double startTime = System.nanoTime();
        double waitUntil = System.nanoTime() + (milliseconds * 1000000);
        while(waitUntil > System.nanoTime()) {
            ;
        }
        double endTime = System.nanoTime();
        
        elapsedTime = endTime - startTime;

        return elapsedTime;
    }
    
    public double getElapsedTime() {
    	return elapsedTime;
    }
    
}
