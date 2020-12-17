package com.sgtslade;

public class RR extends CPUAlg {
    private final int quant;
    private int curQuant;
    private Process processing;

    public RR(int quant) {
        setName("Round robin");
        setDescription("CPU Scheduling algorithm based on a fair amount of processing time assigned to each task before switching.");
        this.quant = quant;
        this.curQuant = quant;
    }

    @Override
    public void run() {
        if(processing==null){
            selectProcess();
        }
        process();
        if(processing.getRemaining()==0){
            finishProcessing();
        }else if(curQuant==0){
            interruptProcess();
        }

    }

    private void selectProcess(){
        Process p = getScheduler().getQueue().get(0);
        processing = p;
        if(p.getRemaining()==p.getDuration()){
            p.setResponseTime(getScheduler().getTime()-p.getArrivalTime()+1);
        }
    }

    private void process(){
        processing.decreaseRemaining();
        curQuant--;
        getScheduler().getHistory().add(processing);
        increaseWaits();
    }

    private void interruptProcess(){
        curQuant=quant;
        getScheduler().getQueue().remove(processing);
        getScheduler().getQueue().add(processing);
        processing=null;
    }

    private void increaseWaits(){
        getScheduler().getQueue().stream().filter(p->p!=processing).forEach(Process::increasewait);
    }

    private void finishProcessing(){
        curQuant = quant;
        getScheduler().moveToFinished(processing);
        processing.setFinishTime(getScheduler().getTime()- processing.getArrivalTime()+1);
        processing=null;
    }
}
