package system;

import java.util.Random;

import schedulingPolicies.*;

public class Scheduler extends Thread {

	Priority schedulingPolicy;
	Queue<Process> processQueue, finishedQueue;
	CPU cpu;
	OperatingSystem operatingSystem;
	
	double processesServed    = 0.00001;
	int contextSwitches    = 0;
	double time			   = 0;
	double waitTime        = 0;
	double turnaroundTime  = 0;
	double responseTime    = -1;
	double throughput      = 0;
	double startTime       = 0;
	int id = (new Random()).nextInt(10);
	
	/**
	 * Constructor method.
	 * @param processQueue - Queue of processes to execute.
	 * @param cpu - CPU to execute a process.
	 * @param schedulingPolicy - Policy to handle scheduling.
	 */
	public Scheduler(Queue<Process> processQueue, Queue<Process> finishedQueue, CPU cpu, OperatingSystem operatingSystem) {
		this.schedulingPolicy = new Priority(this, processQueue);
		this.processQueue = processQueue;
		this.finishedQueue = finishedQueue;
		this.cpu = cpu;
		this.operatingSystem = operatingSystem;
	}

	/**
	 * Method to start the scheduling thread. Thread
	 * will continue to run until the operating system 
	 * terminates the scheduler.
	 */
	public void run() {
		startTime = System.nanoTime();
		while(!operatingSystem.isFinished()) {
			schedulingPolicy.schedule();
		}
		this.terminate();
	}
	
	/**
	 * Method to dispatch the process to the CPU to be executed.
	 * @param process - Process to execute.
	 * @param burst - Execution length.
	 */
	public void dispatch(Process process, double burst) {
		
		// If this is the first time the process is executing, set the response time.
		if(process.getResponseTime() == -1) {
			double responseTime = System.nanoTime() - process.getInitialTime();
			process.setResponseTime(responseTime);
			this.responseTime += responseTime;
		}
		 
		//Add the wait time to the process. This is calculated by taking the 
		// current time and subtracting from the last execution time. 
		double waitTime = System.nanoTime() - process.getLastExecuted();
		process.addWaitTime(waitTime);
		this.waitTime += waitTime;
				
		// Pass the process and burst time to the CPU.
		
		do {
			cpu.execute(process, 0.1);
		} while(!schedulingPolicy.getStatus(process) && process.remainingTime > 0);
		
		discharge(process);
	}
	
	/**
	 * Method to discharge the process from the scheduler. The process
	 * is either logged or re-added to the process queue.
	 * @param process
	 */
	public void discharge(Process process) {
		
		// If the process has finished then log its information otherwise re-enqueue the process.
		if(process.getRemainingTime() == 0) {
			turnaroundTime += process.getTurnaroundTime();
			if(Settings.DEBUG_MODE) finishedQueue.enqueue(process);
		} else {
			processQueue.enqueue(process);
		} 
	
		// Add a context switch as the process was switched.
		addContextSwitch();
		
		// After execution set the last time the process has been executed.
		process.setLastExecuted(System.nanoTime());
	}
	
	/**
	 * Method to return the system process queue.
	 * @return - Queue of processes.
	 */
	public Queue<Process> getProcessQueue() {
		return processQueue;
	}
	
	/**
	 * Method to preempt a process from the CPU.
	 */
	public void preempt() {
		cpu.interrupt();
	}
	
	/** 
	 * Returns the number of context switches of the scheduler.
	 * @return contextSwitches - The number of context switches of the scheduler.
	 */
	public int getContextSwitches() {
		return contextSwitches;
	}
	
	/**
	 * Adds a context switch to the scheduler's context switches counter.
	 */
	public void addContextSwitch() {
		contextSwitches++;
	}
	
	/**
	 * Returns the accumulative wait time of the scheduler.
	 * @return double - Accumulative wait time of the scheduler.
	 */
	public double getWaitTime() {
		return waitTime;
	}
	
	/**
	 * Returns the accumulative turn around time of the scheduler.
	 * @return double - Accumulative turn around time of the scheduler.
	 */
	public double getTurnaroundTime() {
		return turnaroundTime;
	}
	
	/**
	 * Returns the accumulative response time of the scheduler.
	 * @return double - Accumulative response time of the scheduler.
	 */
	public double getResponseTime() {
		return responseTime;
	}
	
	/**
	 * Returns the throughput of the scheduler.
	 * @return double - Throughput of the scheduler.
	 */
	public double getThroughput() {
		return processesServed/time;
	}
	
	/**
	 * Terminates the scheduler and sets the time it
	 * took for the scheduler to execute all processes.
	 * @param time - The time the scheduler finished all processes.
	 */
	public void terminate() {
		
		// Log the time the scheduler terminated.
		this.time = (System.nanoTime() - startTime)/1000000;
		
		if(Settings.DEBUG_MODE) {
			synchronized(this) {
				while(!finishedQueue.isEmpty()) {
					System.out.println(finishedQueue.dequeue().toString());
				}
			}
		}
	}
}
