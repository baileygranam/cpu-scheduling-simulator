package system;

public class Settings {
	
	/** Simulation Settings. */
	
	/* Number of processes to simulate. */
	public final static int NUMBER_OF_PROCESSES = 200;
	
	/* Number of CPUs to simulate. */
	public final static int NUMBER_OF_CPUS = 1;
	
	/* Number of times to execute simulations. */
	public final static int NUMBER_OF_SIMULATIONS = 250;
	
	/* Lambda for arrival rate. */
	public final static double LAMBDA = 0.5;
	
	/* Process burst time bounds. */
	public final static double CPU_BOUND = 0.5;
	
	/* Debug Mode. */
	public static final boolean DEBUG_MODE = false;
	
	/* Round Robin Quantum. */
	public final static int QUANTUM = 2;
	
	/* Set the queue to standard or priority. */
	public final static boolean PRIORITY = true;
}
