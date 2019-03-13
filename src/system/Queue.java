package system;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Queue<E> {
	
	private ConcurrentLinkedQueue<Process> queue = null;
	private PriorityBlockingQueue<Process> priorityQueue = null;

	public Queue() {
		queue = new ConcurrentLinkedQueue<Process>();
	}
	
	public Queue(boolean isPriorityQueue) {
		if(isPriorityQueue) {
			priorityQueue = new PriorityBlockingQueue<Process>(Settings.NUMBER_OF_PROCESSES, new ProcessComparator());
		} else {
			queue = new ConcurrentLinkedQueue<Process>();
		}
	}
	
    public void enqueue(Process process) {
    	if(queue != null) queue.add(process);
    	else priorityQueue.add(process);
    }

    public Process dequeue() {
	    return (queue != null) ? queue.poll(): priorityQueue.poll();
    }
  
    public boolean isEmpty() {
    	return (queue != null) ? queue.isEmpty(): priorityQueue.isEmpty();
    }

    public int size() {
    	return (queue != null) ? queue.size(): priorityQueue.size();
    }
  
    public Process peek() {
    	return (queue != null) ? queue.peek(): priorityQueue.peek();
    }
}
