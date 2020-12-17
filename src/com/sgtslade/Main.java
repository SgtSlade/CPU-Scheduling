package com.sgtslade;

public class Main {

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.getIncoming().add(new Process(1,0,4));
        scheduler.getIncoming().add(new Process(2,0,3));
        scheduler.getIncoming().add(new Process(3,0,5));
        SJF sjf = new SJF();
        RR rr = new RR(2);
        scheduler.setAlgorithm(rr);
        scheduler.run();
    }
}
