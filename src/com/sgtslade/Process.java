package com.sgtslade;

public class Process {
    private final int arrivalTime;
    private final int id;
    private final int duration;
    private int remaining;
    private int waitingTime = 0;
    private int finishTime;
    private int responseTime;

    public Process(int id, int arrivalTime, int duration) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.remaining = duration;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void decreaseRemaining(){
        this.remaining--;
    }

    public void increasewait(){this.waitingTime++;}

    public void printInformation(){
        System.out.println(this);
        System.out.println("Waiting time - " + waitingTime);
        System.out.println("Response time - " + responseTime);
        System.out.println("Turnaround time - " + finishTime);
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getRemaining() {
        return remaining;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "P" + id;
    }
}
