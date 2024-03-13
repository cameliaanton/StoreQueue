package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {

        int min=500000;
        for(Server server: servers)
        {
            if(server.getWaitingPeriod().get()<min)
            {
                min=server.getWaitingPeriod().get();

            }
        }
        int flag=0;
        for(Server server: servers) {
            if (flag==0 && server.getWaitingPeriod().get() == min) {
                server.addTask(t);
                flag=1;
            }
        }
    }
}
