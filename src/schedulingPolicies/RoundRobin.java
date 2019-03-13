package schedulingPolicies;

import system.Process;
import system.Queue;
import system.Scheduler;
import system.Settings;

public class RoundRobin extends SchedulingPolicy {
	
	public RoundRobin(Scheduler scheduler, Queue<Process> processQueue) {
		super(scheduler, processQueue);
	}

	public void schedule() {
		
		Process process = getProcess();
		
		if(process == null) return;
		
		double burst = process.getRemainingTime();
		
		if(burst > Settings.QUANTUM) burst = Settings.QUANTUM;
		
		scheduler.dispatch(process, burst);
	}
}
