package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Results extends JFrame {

    private Dimension screenSize;
    private JPanel panelPrincipal;
    private JLabel labelSup;
    private JPanel panelTons;
    private JLabel labelEnunc;
    private JLabel labelResult;
    private JPanel panelInf;
    private JLabel labelHistory;
    private JTextArea textHistorial;
    private JPanel panelHistory;
    public Results(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(new BorderLayout());
        this.setTitle("Resultados de simulación");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension((screenSize.width/2),(screenSize.height/2)));

        this.getContentPane().setBackground(Color.CYAN);
        //this.initComponents(controllerApp);
        initComponents();
        this.pack();
        setLocationRelativeTo(null);
    }

    private void initComponents(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(20,20,20,20));
        panelPrincipal.setBackground(Color.BLUE);
        labelSup = new JLabel("Resultados de la simulación tras 25 diás", SwingConstants.CENTER);
        panelPrincipal.add(labelSup,BorderLayout.NORTH);
        panelTons = new JPanel();
        panelTons.setBorder(new EmptyBorder(20,20,20,20));
        panelTons.setLayout(new GridLayout(1,2));
        panelTons.setBackground(Color.YELLOW);
        labelEnunc = new JLabel("Toneladas producidas");
        //labelEnunc.setAlignmentX(SwingConstants.WEST);
        panelTons.add(labelEnunc);
        labelResult = new JLabel("-");
        panelTons.add(labelResult);
        panelPrincipal.add(panelTons, BorderLayout.CENTER);
        panelInf = new JPanel();
        panelInf.setBorder(new EmptyBorder(20,20,20,20));
        panelInf.setLayout(new BorderLayout());
        panelInf.setBackground(Color.red);
        labelHistory= new JLabel("Historial de la simulación");
        panelInf.add(labelHistory, BorderLayout.NORTH);

        panelHistory = new JPanel();
        //panelHistory.
        panelHistory.setLayout(new BorderLayout());
        textHistorial = new JTextArea();
        textHistorial.setEditable(false);
        JScrollPane scrollpane = new JScrollPane(textHistorial);
        scrollpane.setPreferredSize(new Dimension(400,200));
        scrollpane.setBorder(null);
        scrollpane.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelHistory.add(scrollpane, BorderLayout.CENTER);
        panelInf.add(scrollpane,BorderLayout.CENTER);
        panelPrincipal.add(panelInf, BorderLayout.SOUTH);
        this.add(panelPrincipal);
    }

    public void setHistory(ArrayList<String> arrayHistory){
        //String temp="";
        for (String s : arrayHistory) {
            /*temp=textHistorial.getText();
            temp+=s;*/
            System.out.println(s);
            textHistorial.setText(textHistorial.getText()+s);
        }
    }

    public void setTotalProduction(int totalProduction){
        labelResult.setText(totalProduction+" toneladas");
    }
}
