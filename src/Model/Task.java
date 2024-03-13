package Model;

public class Task {
    private static int lastid=0;
    private int id;
    private int arrivalTime;
    private int serviceTime;
    public Task(int a, int s)
    {
        id=++lastid;
        arrivalTime=a;
        serviceTime=s;
    }

    public  int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }


    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}
