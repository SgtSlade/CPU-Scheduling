package com.sgtslade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler {
    private List<Process> queue = new ArrayList<>();
    private List<Process> finished = new ArrayList<>();
    private List<Process> incoming = new ArrayList<>();
    private List<Process> history = new ArrayList<>();
    private int curTime = 0;
    private CPUAlg algorithm;

    public void run(){
        while (needsProcessing()){
            advance();
        }
        displayInformation();
    }

    public void pullToQueue(Process p){
        incoming.remove(p);
        queue.add(p);
    }

    public void moveToFinished(Process p){
        queue.remove(p);
        finished.add(p);
    }

    public void pullProcesses(){
        List<Process> toPull = incoming.stream().filter(process -> process.getArrivalTime()==curTime).collect(Collectors.toList());
        if(toPull.size()>0){
            toPull.forEach(this::pullToQueue);
        }
    }

    public void advance() {
        pullProcesses();
        algorithm.run();
        curTime++;
    }

    public void displayInformation(){
        System.out.println("Final statistics");
        System.out.println("Name: " + algorithm.getName());
        System.out.println("Description: " + algorithm.getDescription());
        System.out.println("History: " + history);
        System.out.println("Processes...");
        finished.stream().sorted(Comparator.comparing(Process::getId)).forEach(Process::printInformation);
    }

    public List<Process> getIncoming() {
        return incoming;
    }

    public List<Process> getQueue() {
        return queue;
    }

    public int getTime() {
        return curTime;
    }

    public List<Process> getHistory() {
        return history;
    }

    public boolean needsProcessing(){
        return !(queue.size()==0&&incoming.size()==0);
    }

    public void setAlgorithm(CPUAlg algorithm){
        this.algorithm = algorithm;
        algorithm.setScheduler(this);
    }
}
