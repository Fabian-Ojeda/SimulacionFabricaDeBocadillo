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
    private int totalProduction;
    private ArrayList<String> stages;

    public Production(){
        arrayStations = new ArrayList<>();
        iteratorProcess = 1;
        days = 1;
        lastStart=0;
        lastEnd=0;
        timeAdvanceDay=0;
        pastMinutes = 0;
        excedentMinutesStartingDay = 0;
        stages = new ArrayList<>();
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
        stages.clear();
        days = 1;
        totalProduction =0;
        String status = "";
        stages.add("Inicio de la simulación\n\nActividades del dia 1\n");
        while (days <= 25){
            stages.add("\n");
            //System.out.println("\n\n");
            if(pastMinutes-minutesNextDay>0) {
                excedentMinutesStartingDay = pastMinutes-minutesNextDay;
                if (excedentMinutesStartingDay-minutesWork>0){
                    pastMinutes = excedentMinutesStartingDay-minutesWork;
                    status="waiting";
                    days++;
                    //stages.add("Pasamos a otro dia, se iniciara el dia "+days+"\n");
                    //System.out.println("Pasamos a otro dia, se iniciara el dia "+days);
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
                    stages.add(("A pesar del paso de la noche, el proceso anterior requiere más tiempo, se debe seguir esperando y continuar el siguiente dia\nla tarea excede en "+pastMinutes+
                            " el tiempo de la jornada laboral\n\nActividades del dia "+days+"\n"));
                    /*System.out.println("se debe seguir esperando, la información actual es la siguiente:\nminutos pasados: "+pastMinutes+
                            "\nUltima salida: "+lastStart);*/
                    break;
                case "continue":

                    arrayStations.get(iteratorProcess-1).setStart(lastStart+1);
                    arrayStations.get(iteratorProcess-1).executeStation();
                    timeAdvanceDay = arrayStations.get(iteratorProcess-1).getEnd();
                    lastStart = timeAdvanceDay;
                    stages.add("Tarea: "+arrayStations.get(iteratorProcess-1).getName()+" Minuto de inicio: "+arrayStations.get(iteratorProcess-1).getStart()+ " minuto de finalización: "+timeAdvanceDay+"\n");
                    //stages.add("inicio: "+arrayStations.get(iteratorProcess-1).getStart()+ " "+arrayStations.get(iteratorProcess-1).getName()+" con tiempo "+timeAdvanceDay+"\n");
                    //System.out.println("inicio: "+arrayStations.get(iteratorProcess-1).getStart()+ " "+arrayStations.get(iteratorProcess-1).getName()+" con tiempo "+timeAdvanceDay);
                    if (timeAdvanceDay > minutesWork){
                        pastMinutes = timeAdvanceDay-minutesWork;
                        days++;
                        stages.add("El tiempo de ejecución de la tarea excede en "+pastMinutes +" minutos el tiempo de la jornada laboral, se debe continuar el siguiente dia\n\nActividades del dia "+days+"\n");
                        //System.out.println("Pasamos a otro dia, se iniciara el dia "+days);
                    }
                    if (iteratorProcess==5){
                        totalProduction++;
                        iteratorProcess = 1;
                        stages.add("\nEN ESTE PUNTO SE HA TERMINADO DE FABRICAR UNA TONELADA\n");
                    }else {
                        iteratorProcess++;
                    }
                    //stages.add("Información de la ejecución: \nminutos pasados: "+pastMinutes+"\ntiempo avanzado: "+timeAdvanceDay+"\nultima salida: "+lastStart+"\n");
                    //System.out.println("Información de la ejecución: \nminutos pasados: "+pastMinutes+"\ntiempo avanzado: "+timeAdvanceDay+"\nultima salida: "+lastStart);

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

                    stages.add("Tarea: "+arrayStations.get(iteratorProcess-1).getName()+" minuto de inicio: "+arrayStations.get(iteratorProcess-1).getStart()+ " minuto de finalización: "+timeAdvanceDay+"\n");
                    //stages.add("Información de la ejecución: \nminutos excedidos del tiempo laboral: "+pastMinutes+"\ntiempo avanzado: "+timeAdvanceDay+"\nultima salida: "+lastStart+"\n");

                    //System.out.println("inicio: "+arrayStations.get(iteratorProcess-1).getStart()+ " "+arrayStations.get(iteratorProcess-1).getName()+" con tiempo "+timeAdvanceDay);
                    //System.out.println("Información de la ejecución: \nminutos pasados: "+pastMinutes+"\ntiempo avanzado: "+timeAdvanceDay+"\nultima salida: "+lastStart);

                    if (timeAdvanceDay > minutesWork){
                        pastMinutes = timeAdvanceDay-minutesWork;
                        days++;
                        stages.add("El tiempo de ejecución de la tarea excede en "+pastMinutes +" minutos el tiempo de la jornada laboral, se debe continuar el siguiente dia\n\nActividades del dia "+days+"\n");
                        //System.out.println("Pasamos a otro dia, se iniciara el dia "+days);
                    }
                    if (iteratorProcess==5){
                        totalProduction++;
                        iteratorProcess = 1;
                        stages.add("\nEN ESTE PUNTO SE HA TERMINADO DE FABRICAR UNA TONELADA\n");
                    }else {
                        iteratorProcess++;
                    }
                    break;
                default:
                    break;
            }
        }
        stages.add("Pasados  "+(days-1)+ " dias se obtiene un total de "+totalProduction+ " cajas producidas");
        //System.out.println("Pasados  "+days+ " dias se obtiene un total de "+total+ " cajas producidas");
    }

    private void executeStation(int index){


    }

    public ArrayList<String> getStages(){
        return stages;
    }

    public int getTotalProduction() {
        return totalProduction;
    }
}
