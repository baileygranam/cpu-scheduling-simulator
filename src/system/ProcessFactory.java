package system;

import java.util.Random;

/**
 * The Process Factory class is utilized in generating
 * processes for simulations.
 * 
 * @author Bailey Granam
 */
public class ProcessFactory extends Thread
{	
	Random random = new Random();
	Clock clock = new Clock();
	Queue<Process> processQueue;
	int numberOfProcesses;
	
	/**
	 * Method to instantiate the Process Factory.
	 * @param processQueue - Queue to store created processes.
	 * @param numberOfProcesses - Number of processes to generate.
	 */
	public ProcessFactory(Queue<Process> processQueue, int numberOfProcesses) {
		this.processQueue = processQueue;
		this.numberOfProcesses = numberOfProcesses;
	}
	
	/** 
	 * Method to run the thread and generate processes. 
	 */
    public void run()
    { 
    	double interArrivalTime = 0;
    	double prevArrivalTime = 0;
    	
        for(int i = 0; i < numberOfProcesses; ++i)
        {	
        	double arrivalTime = System.nanoTime();
        	
        	clock.delay(interArrivalTime);
        	
            double burstTime = generateBurstTime();
            
            double priority = (Settings.PRIORITY) ? generatePriority() : 0;
            
            double initialTime = System.nanoTime();
            
        	arrivalTime = (initialTime - arrivalTime) + prevArrivalTime;
        	
            Process process = new Process(i, burstTime, arrivalTime, System.nanoTime(), priority); 
            
        	processQueue.enqueue(process);
        	
        	interArrivalTime = generateInterArrivalTime(Settings.LAMBDA);
        	
        	prevArrivalTime = arrivalTime;
        }
    }
   
    /**
     * Method to generate the time between arrivals. 
     * @param lambda - Arrival Rate
     * @return double - Interarrival time
     */
    private double generateInterArrivalTime(double lambda) {
    	return -(Math.log(random.nextDouble()) / lambda); 
    }
    
    /**
     * Method to generate burst time
     * @return double - Burst time
     */
    private double generateBurstTime() {
    	Random rand = new Random();
    	double val = rand.nextDouble();
    	if (val <= Settings.CPU_BOUND) { 
    		return rand.nextInt((10 - 5) + 1) + 5;
    	} else {
    		return rand.nextInt((4 - 1) + 1) + 1;
    	}
    }
    
    /**
     * Method to generate a random priority.
     * @return int - Priority
     */
    public int generatePriority() {
    	return random.nextInt(11);
    } 
}