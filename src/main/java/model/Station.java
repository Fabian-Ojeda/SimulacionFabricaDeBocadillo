package model;

public class Station {

    private String name;
    private int start;
    private int process;
    private int end;
    private ExtraProcess extraProcess;

    public Station(String name, int process){
        this.name = name;
        this.process = process;
    }

    public String getName() {
        return name;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void executeStation(){
        if (extraProcess!=null){
            end=start+process+extraProcess.getDuration();
        }else {
            end=start+process;
        }
    }

    public int getProcess() {
        return process;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public void setExtraProcess(ExtraProcess extraProcess) {
        this.extraProcess = extraProcess;
    }

    public boolean hasExtraProcess(){
        return extraProcess!=null;
    }
}
