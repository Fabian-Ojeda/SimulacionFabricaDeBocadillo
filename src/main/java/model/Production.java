package model;

import java.util.ArrayList;

public class Production {

    private static int minutesWork = 480;
    private static int minutesNextDay = 960;
    private int days;
    private ArrayList<Station> arrayStations;
    private int iteratorProcess;
    private int lastStart;
    private int lastEnd;
    private int timeAdvanceDay;
    private int pastMinutes;
    private int excedentMinutesStartingDay;
    public Production(){
        arrayStations = new ArrayList<>();
        iteratorProcess = 1;
        days = 1;
        lastStart=0;
        lastEnd=0;
        timeAdvanceDay=0;
        pastMinutes = 0;
        excedentMinutesStartingDay = 0;
    }

    public void generateStations(int valueRecept, int valueCooking, int valueMold, int valueCutting, int valueLabeled){
        Station recept = new Station("Recepción, lavado y preparación", valueRecept);
        Station cooking = new Station("Despulpado y Cocción", valueCooking);
        cooking.setExtraProcess(new ExtraProcess("Cocinado", 45));
        Station mold = new Station("Punteo y Moldeo", valueMold);
        mold.setExtraProcess(new ExtraProcess("Enfriamiento", 2880));
        Station cutting = new Station("Corte y empacado", valueCutting);
        cutting.setExtraProcess(new ExtraProcess("secamiento", 720));
        Station labeled = new Station("Embalado y etiquetado", valueLabeled);
        arrayStations.add(recept);
        arrayStations.add(cooking);
        arrayStations.add(mold);
        arrayStations.add(cutting);
        arrayStations.add(labeled);
    }

    public void startProduction(){
        days = 1;
        int total =0;
        String status = "";
        while (days <= 25){
            System.out.println("\n\n");
            if(pastMinutes-minutesNextDay>0) {
                excedentMinutesStartingDay = pastMinutes-minutesNextDay;
                if (excedentMinutesStartingDay-minutesWork>0){
                    pastMinutes = excedentMinutesStartingDay-minutesWork;
                    status="waiting";
                    days++;
                    System.out.println("Pasamos a otro dia, se iniciara el dia "+days);
                } else {
                    pastMinutes = 0;
                    lastStart = excedentMinutesStartingDay;
                    status = "continue";
                }
            }else {
                status="sameJourney";
                if (lastStart>minutesWork){
                    lastStart=0;
                    pastMinutes=0;
                }
            }

            switch (status){
                case "waiting":
                    System.out.println("se debe seguir esperando, la información actual es la siguiente:\nminutos pasados: "+pastMinutes+
                            "\nUltima salida: "+lastStart);
                    break;
                case "continue":

                    arrayStations.get(iteratorProcess-1).setStart(lastStart+1);
                    arrayStations.get(iteratorProcess-1).executeStation();
                    timeAdvanceDay = arrayStations.get(iteratorProcess-1).getEnd();
                    lastStart = timeAdvanceDay;
                    System.out.println("inicio: "+arrayStations.get(iteratorProcess-1).getStart()+ " "+arrayStations.get(iteratorProcess-1).getName()+" con tiempo "+timeAdvanceDay);
                    if (iteratorProcess==5){
                        total++;
                        iteratorProcess = 1;
                    }else {
                        iteratorProcess++;
                    }
                    System.out.println("Información de la ejecución: \nminutos pasados: "+pastMinutes+"\ntiempo avanzado: "+timeAdvanceDay+"\nultima salida: "+lastStart);
                    if (timeAdvanceDay > minutesWork){
                        pastMinutes = timeAdvanceDay-minutesWork;
                        days++;
                        System.out.println("Pasamos a otro dia, se iniciara el dia "+days);
                    }
                    break;
                case "sameJourney":
                    if(lastStart==0){
                        arrayStations.get(iteratorProcess-1).setStart(0);
                    }else {
                        arrayStations.get(iteratorProcess-1).setStart(lastStart+1);
                    }
                    arrayStations.get(iteratorProcess-1).executeStation();
                    timeAdvanceDay = arrayStations.get(iteratorProcess-1).getEnd();
                    lastStart = timeAdvanceDay;

                    System.out.println("inicio: "+arrayStations.get(iteratorProcess-1).getStart()+ " "+arrayStations.get(iteratorProcess-1).getName()+" con tiempo "+timeAdvanceDay);
                    System.out.println("Información de la ejecución: \nminutos pasados: "+pastMinutes+"\ntiempo avanzado: "+timeAdvanceDay+"\nultima salida: "+lastStart);
                    if (timeAdvanceDay > minutesWork){
                        pastMinutes = timeAdvanceDay-minutesWork;
                        days++;
                        System.out.println("Pasamos a otro dia, se iniciara el dia "+days);
                    }
                    if (iteratorProcess==5){
                        total++;
                        iteratorProcess = 1;
                    }else {
                        iteratorProcess++;
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println("Pasados  "+days+ " dias se obtiene un total de "+total+ " cajas producidas");
    }

    private void executeStation(int index){


    }
}
