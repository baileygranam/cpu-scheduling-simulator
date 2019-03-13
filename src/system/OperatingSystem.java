package system;

public class OperatingSystem {
	
	/* The queue of processes currently in the system. */
	private Queue<Process> processQueue = new Queue<Process>(Settings.PRIORITY); 
	private Queue<Process> finishedQueue = new Queue<Process>(); 
	private CPU[] cpu = new CPU[Settings.NUMBER_OF_CPUS];
	private Scheduler[] scheduler;
	private ProcessFactory processFactory;
	
	/* Metrics used for measuring efficiency. */
	double waitTime            = 0;
	double turnaroundTime      = 0;
	double responseTime        = 0;
	double contextSwitches     = 0;
	double throughput          = 0;
	
	/**
	 * Method to run the operating system simulation.
	 */
	public void boot() {
		for(int z = 0; z < Settings.NUMBER_OF_SIMULATIONS; z++) {
			processFactory = new ProcessFactory(processQueue, Settings.NUMBER_OF_PROCESSES);
			scheduler = new Scheduler[Settings.NUMBER_OF_CPUS];
			for(int i = 0; i < Settings.NUMBER_OF_CPUS; i++) {
				cpu[i] = new CPU(1);
				scheduler[i] = new Scheduler(processQueue, finishedQueue, cpu[i], this);
			}
			double s1 = System.nanoTime();
			processFactory.start();
			
			for(int i = 0; i < Settings.NUMBER_OF_CPUS; i++ ) {
				scheduler[i].start();
			}

		    for (Scheduler scheduler: scheduler) {
		    	try {
					scheduler.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		    double s2 = System.nanoTime();

		    for(int k = 0; k < Settings.NUMBER_OF_CPUS; k++) {
				waitTime += scheduler[k].getWaitTime();
				turnaroundTime += scheduler[k].getTurnaroundTime();
				responseTime += scheduler[k].getResponseTime();
				contextSwitches += scheduler[k].getContextSwitches();
			}
			throughput += (s2 - s1);
		}
		System.out.println("\n---- Averages of CPU Scheduler ----\n");
		System.out.println("Wait : " + (waitTime/1000000)/(Settings.NUMBER_OF_SIMULATIONS*Settings.NUMBER_OF_PROCESSES));
		System.out.println("Turn: " + (turnaroundTime/1000000)/(Settings.NUMBER_OF_SIMULATIONS*Settings.NUMBER_OF_PROCESSES));
		System.out.println("Context: " + (contextSwitches/(Settings.NUMBER_OF_SIMULATIONS)));
		System.out.println("Throughput: " + ((Settings.NUMBER_OF_SIMULATIONS*Settings.NUMBER_OF_PROCESSES)/(throughput/1000000)));
		System.out.println("Response: " + (responseTime/1000000)/(Settings.NUMBER_OF_SIMULATIONS*Settings.NUMBER_OF_PROCESSES));
	}
	
	public boolean isFinished() {
		return (!processFactory.isAlive() && processQueue.isEmpty());
	}
	
    public static void main(String[] args) {
    	OperatingSystem OS = new OperatingSystem();
		OS.boot();
    }
}
