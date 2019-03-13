package schedulingPolicies;

import system.Scheduler;
import system.Queue;
import system.Process;

public class FirstComeFirstServe extends SchedulingPolicy {
	
	public FirstComeFirstServe(Scheduler scheduler, Queue<Process> processQueue) {
		super(scheduler, processQueue);
	}
	
	public void schedule() {
		Process process = getProcess();
		if(process == null) return;
		scheduler.dispatch(process, process.getBurstTime());
	}
}