package model;

public class ExtraProcess {

    private String name;
    private int duration;

    public ExtraProcess(String name, int duration){
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
