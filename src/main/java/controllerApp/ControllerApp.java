package controllerApp;

import model.Production;
import view.MainWindow;
import view.Results;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerApp implements ActionListener {

    private Production production;
    private MainWindow mainWindow;
    private Results results;

    public ControllerApp() {
        System.out.println("kios");
        production = new Production();

        mainWindow = new MainWindow(this);
        mainWindow.setVisible(true);
        results = new Results();
    }

    //Listener de las pulsaciones de los botones
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (Commands.valueOf(actionEvent.getActionCommand())) {
            case C_START_SIMULATION:
                initSimulation();
                break;
            default:
                break;
        }
    }

    private void initSimulation(){
        production.generateStations(mainWindow.getValueRecept(),mainWindow.getValueCooking(), mainWindow.getValueMold(), mainWindow.getValueCutting(), mainWindow.getValueLabeled());
        production.startProduction(mainWindow.getTonsQuantity());
        System.out.println("Finalizo la simulación");
        results.setHistory(production.getStages());
        results.setTotalProduction(production.getTotalProduction());
        results.setVisible(true);
    }
}
