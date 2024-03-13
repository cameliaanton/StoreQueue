package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class SimulationManager implements Runnable {
    public int timeLimit=60;
    public int n;
    public int q;
    public int minArrival,maxArrival;
    public int minService,maxService;

    // public int maxProcessingTime=10;
    // public int minProcessingTime=2;
    //public int numberOfServers=3;
    //public int numberOfClients=100;
    public SelectionPolicy selectionPolicy =SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks= new LinkedList<Task>();
    private Random random= new Random();

    public SimulationManager(int n,int q,int minA,int maxA,int minS,int maxS,int timeL,String combo)
    {
        this.n=n;
        this.q=q;
        scheduler=new Scheduler(q,40);
        scheduler.changeStrategy(SelectionPolicy.valueOf(combo));
        this.minArrival=minA;
        this.maxArrival=maxA;
        this.minService=minS;
        this.maxService=maxS;
        this.timeLimit=timeL;
        generateNRandomTask();
        afisare();

    }
    void afisare()
    {
        for(int i=0;i<n;i++)
        {
            System.out.println(generatedTasks.get(i).getId()+" "+generatedTasks.get(i).getArrivalTime()+" "+ generatedTasks.get(i).getServiceTime());
        }
    }
    public void generateNRandomTask()
    {
        for(int i=0;i<n;i++)
        {
            int arrTime = random.nextInt(maxArrival-minArrival+1)+minArrival;
            // int arrTime= random.nextInt(timeLimit-2+1)+2;
            int servTime = random.nextInt(maxService-minService+1)+minService;
            System.out.println(arrTime+" "+servTime);
            generatedTasks.add( new Task(arrTime,servTime));
        }
        generatedTasks.sort(Comparator.comparing(Task::getArrivalTime));
    }
    @Override
    public void run() {
        FileWriter print,print1 = null;
        try {print = new FileWriter("text.txt");}
        catch (IOException e) {throw new RuntimeException(e);}
        try {print1=new FileWriter("output.txt");}
        catch (IOException e) {throw new RuntimeException(e);}
        int stopGeneratedTask=-1, currentTime=0;
        int peakHour=0,hour=0,sericeTime=0,serviceNumber=0,st=0;
        int waiting=0;
        ArrayList<Integer> waitingTimes = new ArrayList<Integer>();

        while(currentTime <timeLimit && currentTime!=stopGeneratedTask) {
            try {print.write("Time:"+currentTime+"\n");}
            catch (IOException e) {throw new RuntimeException(e);}
            if(generatedTasks.size()==1)
                stopGeneratedTask=generatedTasks.get(0).getArrivalTime()+generatedTasks.get(0).getServiceTime()+2;
            try {print.write("Waiting clients:");}
            catch (IOException e) {throw new RuntimeException(e);}
            for(Task t: generatedTasks) {
                try {print.write("("+t.getId()+","+t.getArrivalTime()+","+t.getServiceTime()+")"+",");}
                catch (IOException e) {throw new RuntimeException(e);}}
            try {print.write("\n");}
            catch (IOException e) {throw new RuntimeException(e);}
            int i=0,count=0;
            while(i < scheduler.getServers().size()) {
                try {
                    print.write("Queue " + i + ":");
                }
                catch (IOException e) {throw new RuntimeException(e);}
                Task[] tasks = scheduler.getServers().get(i).getTasks();
                if(scheduler.getServers().get(i).getTasks2().size()==0)
                    try {print.write("closed ");} catch (IOException e) {throw new RuntimeException(e);}
                count+=scheduler.getServers().get(i).getTasks2().size();
                for (Task task : tasks) {
                    if(task!=null) {
                      //  count++;
                        try {print.write("(" + task.getId() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ") ");}
                        catch (IOException e) {throw new RuntimeException(e);}
                    }
                }
                if(peakHour<count)
                {   peakHour=count;
                    hour=currentTime;
                }
                if(!scheduler.getServers().get(i).getTasks2().isEmpty()) {
                    Task t = (Task) scheduler.getServers().get(i).getTasks2().peek();
                    if(t.getServiceTime()==1) {
                        scheduler.getServers().get(i).getTasks2().remove();
                        sericeTime+= currentTime-t.getArrivalTime();
                        waiting+=t.getArrivalTime();
                        serviceNumber++;
                    }
                    else t.setServiceTime(t.getServiceTime()-1);
                }
                try { print.write("\n");} catch (IOException e) {throw new RuntimeException(e);}
                i++;
            }
            Iterator<Task> iterator = generatedTasks.iterator();
            while (iterator.hasNext()) {
                Task g = iterator.next();
                if (g.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(g);
                   // waiting += (Integer) scheduler.getServers().get(i).getWaitingPeriod().get();
                    iterator.remove();}
            }
            currentTime++;
            synchronized (this){
                try {wait(1000);} catch (InterruptedException e) {throw new RuntimeException(e);}}
        }
        try {print1.write("Peak Hour:"+peakHour+"\nHour:"+hour+"\n"+"Average waiting time:"+ (double)waiting/q+"\n"+"Average service time:"+ (double)sericeTime/serviceNumber+"\n");} catch (IOException e) {throw new RuntimeException(e);}
        try {print.close();print1.close();} catch (IOException e) {throw new RuntimeException(e);}
    }
    public static void main(String[] args) throws IOException {

        SimulationFrame frame= new SimulationFrame();
        int q,n,minA,maxA,timeL,minS,maxS;
        while (frame.butonn == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String combo=frame.getDropDown();
        try{
            n=Integer.parseInt(frame.getInputN());
            q=Integer.parseInt(frame.getInputQ());
            minA=Integer.parseInt(frame.getInputMinArrival());
            maxA=Integer.parseInt(frame.getInputMaxArrival());
            minS=Integer.parseInt(frame.getInputMinService());
            maxS=Integer.parseInt(frame.getInputMaxService());
            timeL=Integer.parseInt(frame.getInputTimeLimit());
        }
        catch(NumberFormatException e)
        {
            throw new NumberFormatException("Invalid input: not a number");
        }
        System.out.println(n+" "+q+" "+minA+" "+maxA+""+minS+" "+maxS+" "+timeL);
        SimulationManager gen=new SimulationManager(n,q,minA,maxA,minS,maxS,timeL,combo);
        Thread t=new Thread(gen);
        t.start();

    }
}
