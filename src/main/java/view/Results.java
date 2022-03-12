package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Results extends JFrame {

    private JPanel panelPrincipal;
    private JLabel labelSup;
    private JPanel panelTons;
    private JLabel labelEnunc;
    private JLabel labelResult;
    private JPanel panelInf;
    private JLabel labelHistory;
    private JTextArea textHistorial;
    private JPanel panelHistory;
    private Color backgroundColor;
    public Results(){
        backgroundColor = Color.decode("#F6E9E8");
        this.setLayout(new BorderLayout());
        this.setTitle("Resultados de simulación");
        this.setPreferredSize(new Dimension(910,450));

        this.getContentPane().setBackground(backgroundColor);
        //this.initComponents(controllerApp);
        initComponents();
        this.pack();
        setLocationRelativeTo(null);
    }

    private void initComponents(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(20,20,20,20));
        panelPrincipal.setBackground(backgroundColor);
        Font titleFont = new Font("Arial Narrow", Font.BOLD,28);
        labelSup = new JLabel("Resultados de la simulación tras 25 diás", SwingConstants.CENTER);
        labelSup.setFont(titleFont);
        panelPrincipal.add(labelSup,BorderLayout.NORTH);
        panelTons = new JPanel();
        panelTons.setBorder(new EmptyBorder(20,20,20,20));
        panelTons.setLayout(new GridLayout(1,2));
        panelTons.setBackground(backgroundColor);
        Font detailFont = new Font("Arial Narrow", Font.PLAIN,23);
        labelEnunc = new JLabel("Cajas producidas");
        labelEnunc.setFont(detailFont);
        //labelEnunc.setAlignmentX(SwingConstants.WEST);
        panelTons.add(labelEnunc);
        labelResult = new JLabel("-");
        labelResult.setFont(detailFont);
        panelTons.add(labelResult);
        panelPrincipal.add(panelTons, BorderLayout.CENTER);
        panelInf = new JPanel();
        panelInf.setBorder(new EmptyBorder(20,20,20,20));
        panelInf.setLayout(new BorderLayout());
        panelInf.setBackground(backgroundColor);
        Font detailHistoryFont = new Font("Arial Narrow", Font.PLAIN,20);
        labelHistory= new JLabel("Historial de la simulación");
        labelHistory.setFont(detailHistoryFont);
        panelInf.add(labelHistory, BorderLayout.NORTH);
        panelHistory = new JPanel();
        panelHistory.setLayout(new BorderLayout());
        Font historyFont = new Font("Arial Narrow", Font.PLAIN,18);
        textHistorial = new JTextArea();
        textHistorial.setEditable(false);
        textHistorial.setFont(historyFont);
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
        for (String s : arrayHistory) {
            textHistorial.setText(textHistorial.getText()+s);
        }
    }

    public void setTotalProduction(int totalProduction){
        labelResult.setText(totalProduction+" cajas");
    }
}
