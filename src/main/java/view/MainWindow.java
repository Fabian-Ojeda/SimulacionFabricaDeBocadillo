package view;

import controllerApp.Commands;
import controllerApp.ControllerApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JPanel panelQuantities;
    private JLabel labelQuantityIN;
    private JTextField textQuantityIn;
    private JButton buttonInitSimulation;
    private Color backgroundColor;

    public MainWindow(ControllerApp controllerApp){
        backgroundColor = Color.decode("#F6E9E8");
        this.setLayout(new BorderLayout());
        this.setTitle("Fabrica de bocadillos <Inserte nombre>");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200,400));

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
        panelMedium.setLayout(new GridBagLayout());
        panelMedium.setBorder(new EmptyBorder(20,20,0,20));
        panelMedium.setBackground(backgroundColor);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 0; // El área de texto empieza en la fila cero
        constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
        constraints.gridheight = 1; // El área de texto ocupa 2 filas.

        Font detailsFont = new Font("Arial Narrow", Font.PLAIN,28);
        labelDetails = new JLabel("Por favor ingrese la cantidad de tiempo en minutos que tardara cada estación en terminar su proceso");
        labelDetails.setFont(detailsFont);
        panelMedium.add(labelDetails, constraints);
        panelQuantities = new JPanel();
        panelQuantities.setBackground(backgroundColor);
        panelQuantities.setBorder(new EmptyBorder(20,0,20,0));
        panelQuantities.setLayout(new GridLayout(1,2));
        labelQuantityIN = new JLabel("Toneladas de materia prima que sera ingresada por ciclo de producción:");
        Font quantityFont = new Font("Arial Narrow", Font.PLAIN,18);
        labelQuantityIN.setFont(quantityFont);
        panelQuantities.add(labelQuantityIN);
        textQuantityIn = new JTextField();
        textQuantityIn.setFont(quantityFont);
        textQuantityIn.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                if(((caracter < '0') ||
                        (caracter > '9')) &&
                        (caracter != '\b'))
                {
                    e.consume();
                }
            }
        });
        panelQuantities.add(textQuantityIn);
        //panelQuantities.setPreferredSize(new Dimension(50,50));
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 1; // El área de texto empieza en la fila cero
        constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
        constraints.gridheight = 1; // El área de texto ocupa 2 filas.
        panelMedium.add(panelQuantities, constraints);
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
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 2; // El área de texto empieza en la fila cero
        constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
        constraints.gridheight = 5; // El área de texto ocupa 4 filas.
        panelMedium.add(panelStations, constraints);
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

    public int getTonsQuantity(){
        if (textQuantityIn.getText().equals("")){
            return 0;
        }else {
            return Integer.parseInt(textQuantityIn.getText());
        }
    }

}
