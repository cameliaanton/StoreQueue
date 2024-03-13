package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private AtomicInteger waitingPeriod;
    private BlockingQueue<Task> tasks;

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public Server()
    {
        waitingPeriod = new AtomicInteger();
        tasks = new LinkedBlockingQueue<Task>();
    }

    public void addTask(Task newTask)
    {
        tasks.add(newTask);
        waitingPeriod.getAndIncrement();
    }
    public void run()
    {
        while(true)
        {
            //take next task from queue
            Task t= tasks.peek();
            //stop the thread for a time equal with the task's processing time
            if(t!=null)
            {
                int serviceTime=0;
                try {
                    serviceTime=t.getServiceTime();
                    //stop the thread for a time equal with the task's processing time
                    Thread.sleep(serviceTime);
                    //decrement the waitingPeriod
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(t.getServiceTime()==0)
                {
                    System.out.println("hfdlkgj");
                    tasks.remove();
                    waitingPeriod.getAndAdd(-serviceTime);
                }

            }
        }
    }
    public Task[] getTasks()
    {
        Task[] t= new Task[waitingPeriod.get()];
        tasks.toArray(t);
        return t;
    }
    public LinkedBlockingQueue getTasks2()
    {
        return (LinkedBlockingQueue) tasks;
    }
}
