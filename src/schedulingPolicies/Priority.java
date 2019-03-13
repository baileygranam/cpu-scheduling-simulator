package schedulingPolicies;

import system.Process;
import system.Queue;
import system.Scheduler;

public class Priority extends SchedulingPolicy {
	
	public Priority(Scheduler scheduler, Queue<Process> processQueue) {
		super(scheduler, processQueue);
	}

	public void schedule() {
		
		Process process = getProcess();
		
		if(process == null) return;
		
		double burst = process.getRemainingTime();
		
		scheduler.dispatch(process, burst);
	}
	
	public boolean getStatus(Process process) {
		Process temp = processQueue.peek();
		if(temp == null) return false;
		return (temp.getPriority() > process.getPriority());
	}
}
