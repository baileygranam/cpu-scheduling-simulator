package system;
import java.util.Comparator;

/**
 * The ProcessComparator class implements a Comparator of type Process.
 * This is used in sorting an array of processes by arrival time and priority.
 * 
 * @author Bailey Granam
 * 
 */
public class ProcessComparator implements Comparator<Process>
{
	/* Compare Process p1 against process p2. */
	public int compare(Process p1, Process p2) 
	{
		return Double.compare(p2.getPriority(), p1.getPriority());
    }
}
