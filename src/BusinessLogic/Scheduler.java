package BusinessLogic;
import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    private List<Server> servers= new LinkedList<Server>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    public Scheduler(int maxNoServers,int maxTasksPerServer)
    {
        for(int i=0;i<maxNoServers;i++)
        {
            Server servisor= new Server();
            servers.add(servisor);
            Thread thread = new Thread(servisor);
        }
    }
    public void changeStrategy(SelectionPolicy policy)
    {
        //apply strategy patter to instantiate the strategy with
        //the concrete strategy corresponding to policy
        if(policy== SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy= new ConcreteStrategyTime();
        }
    }
    public void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }
    public List<Server> getServers()
    {
        return servers;
    }
}
