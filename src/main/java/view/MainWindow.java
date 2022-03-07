package view;

import controllerApp.Commands;
import controllerApp.ControllerApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel panelPrincipal;
    private JLabel labelTitle;
    private JLabel labelDetails;
    private JPanel panelMedium;
    private JPanel panelStations;
    private ElementStation elementRecep;
    private ElementStation elementCooking;
    private ElementStation elementMold;
    private ElementStation elementCutting;
    private ElementStation elementLabeled;
    private JButton buttonInitSimulation;
    private Color backgroundColor;

    public MainWindow(ControllerApp controllerApp){
        backgroundColor = Color.decode("#F6E9E8");
        this.setLayout(new BorderLayout());
        this.setTitle("Fabrica de bocadillos <Inserte nombre>");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200,500));

        this.getContentPane().setBackground(Color.CYAN);
        this.initComponents(controllerApp);
        this.pack();
        setLocationRelativeTo(null);
    }

    private void initComponents(ControllerApp controllerApp){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(20,20,20,20));
        panelPrincipal.setBackground(backgroundColor);
        Font titleFont = new Font("Arial Narrow", Font.BOLD,35);
        labelTitle = new JLabel("Simulador de cadena de producción de bocadillos");
        labelTitle.setFont(titleFont);
        panelPrincipal.add(labelTitle, BorderLayout.NORTH);

        panelMedium = new JPanel();
        panelMedium.setLayout(new BorderLayout());
        panelMedium.setBorder(new EmptyBorder(20,20,20,20));
        panelMedium.setBackground(backgroundColor);

        Font detailsFont = new Font("Arial Narrow", Font.PLAIN,28);
        labelDetails = new JLabel("Por favor ingrese la cantidad de tiempo en minutos que tardara cada estación en terminar su proceso");
        labelDetails.setFont(detailsFont);
        panelMedium.add(labelDetails, BorderLayout.NORTH);

        panelStations = new JPanel();
        panelStations.setLayout(new BoxLayout(panelStations, BoxLayout.Y_AXIS));
        panelStations.setBorder(new EmptyBorder(20,20,20,20));
        panelStations.setBackground(backgroundColor);

        elementRecep = new ElementStation("Recepción, lavado y preparación");
        panelStations.add(elementRecep);

        elementCooking = new ElementStation("Despulpado y Cocción");
        elementCooking.createExtraProcess("Tiempo adicional en cocina: 45 minutos");
        panelStations.add(elementCooking);

        elementMold = new ElementStation("Punteo y Moldeo");
        elementMold.createExtraProcess("Tiempo adicional en Enfriamiento: 2880 minutos");
        panelStations.add(elementMold);

        elementCutting = new ElementStation("Corte y empacado");
        elementCutting.createExtraProcess("Tiempo adicional en Secamiento: 720 minutos");
        panelStations.add(elementCutting);

        elementLabeled = new ElementStation("Embalado y etiquetado");
        panelStations.add(elementLabeled);
        panelMedium.add(panelStations, BorderLayout.CENTER);
        panelPrincipal.add(panelMedium,BorderLayout.CENTER);

        JPanel panelDown = new JPanel();
        panelDown.setLayout(new BorderLayout());
        panelDown.setBackground(backgroundColor);
        panelDown.setBorder(new EmptyBorder(0,480,0,480));
        buttonInitSimulation = new JButton("Iniciar simulación");
        buttonInitSimulation.setBackground(Color.GREEN);
        buttonInitSimulation.setActionCommand(Commands.C_START_SIMULATION.name());
        buttonInitSimulation.addActionListener(controllerApp);
        panelDown.add(buttonInitSimulation, BorderLayout.CENTER);

        panelPrincipal.add(panelDown, BorderLayout.SOUTH);
        this.add(panelPrincipal, BorderLayout.CENTER);
    }

    public Integer getValueRecept(){
        return elementRecep.getQuantity();
    }

    public Integer getValueCooking(){
        return elementCooking.getQuantity();
    }

    public Integer getValueMold(){
        return elementMold.getQuantity();
    }

    public Integer getValueCutting(){
        return elementCutting.getQuantity();
    }

    public Integer getValueLabeled(){
        return elementLabeled.getQuantity();
    }

}
