package com.sgtslade;

import java.util.Comparator;

public class SJF extends CPUAlg {
    private Process processing;

    public SJF() {
        setName("Shortest job first");
        setDescription("CPU scheduling algorithm prioritizing shortest tasks first.");
    }

    @Override
    public void run() {
        if(processing==null){
            selectProcess();
        }
        process();
        if(processing.getRemaining()==0){
           finishProcessing();
        }
    }

    private void selectProcess(){
        processing = getScheduler().getQueue().stream().min(Comparator.comparing(Process::getRemaining).thenComparing(Process::getArrivalTime)).get();
        processing.setResponseTime(getScheduler().getTime()-processing.getArrivalTime()+1);
    }

    private void process(){
        processing.decreaseRemaining();
        getScheduler().getHistory().add(processing);
        increaseWaits();
    }

    private void finishProcessing(){
        getScheduler().moveToFinished(processing);
        processing.setFinishTime(getScheduler().getTime()- processing.getArrivalTime()+1);
        processing=null;
    }

    private void increaseWaits(){
        getScheduler().getQueue().stream().filter(p->p!=processing).forEach(Process::increasewait);
    }
}
