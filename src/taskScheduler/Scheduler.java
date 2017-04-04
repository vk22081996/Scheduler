package taskScheduler;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;

public class Scheduler {
	public PriorityQueue<String> tasksQueue = new PriorityQueue<>();
	Scheduled scheduledTasks;
	public Scheduler(Scheduled scheduledTasks) {
		// TODO Auto-generated constructor stub
		this.scheduledTasks = scheduledTasks;
	}
	public void scheduleTask(String time){
		tasksQueue.add(time);
	}
	public void removeTask(String time){
		tasksQueue.remove(time);
	}
	public PriorityQueue<String> getAllAlarms() {
		return tasksQueue;
	}
	public void start(){
		while(true){
			while(!this.tasksQueue.isEmpty()){
				try {
					Thread.sleep(getSleepTime());
					tasksQueue.poll();
					scheduledTasks.scheduledTaskTriggered();
				} catch (InterruptedException e) {
					//do nothing
				} 
				
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
	}
	}
	public long getSleepTime(){
		if(this.tasksQueue.isEmpty()){
			return 0;
		}
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		Date date2;
		try {
			date2 = dateFormat.parse(tasksQueue.peek());
			Date date1 = dateFormat.parse(dateFormat.format(date));
			long sleepTime = date2.getTime() - date1.getTime();
			return sleepTime;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
}