package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public  class
ConcreteStrategyQueue implements  Strategy{

    @Override
    public void addTask(List<Server> servers, Task t) {
      //  Server serverQueue= new Server();
        int min=500000;
        for(Server server: servers)
        {
            if(server.getTasks().length<min)
            {
                min=server.getTasks().length;
              //  serverQueue=server;
            }
        }
        int flag=0;
        for(Server server: servers) {
            if (flag==0 && server.getTasks().length==min) {
                server.addTask(t);
                flag=1;
            }
        }


    }
}
