package schedulingPolicies;

import system.Process;
import system.ProcessComparator;
import system.Queue;
import system.Scheduler;

public abstract class SchedulingPolicy {
	
	protected Scheduler scheduler;
	protected Queue<Process> processQueue;
	protected ProcessComparator processComparator = new ProcessComparator();
	
	public SchedulingPolicy(Scheduler scheduler, Queue<Process> processQueue) {
		this.scheduler = scheduler;
		this.processQueue = processQueue;
	}
	
	public abstract void schedule();
	
	public boolean getStatus(Process process) {
		return false;
	}
	
	public Process getProcess() {
		
		Process process;
		
		synchronized(this) {
			process = processQueue.dequeue();
		}
		
		return process;
	}
	
}
