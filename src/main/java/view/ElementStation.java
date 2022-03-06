package view;

import javax.swing.*;
import java.awt.*;

public class ElementStation extends JPanel {

    private JLabel labelname;
    private JTextField textFieldQuantity;
    private JLabel labelMinutes;

    public ElementStation(String name){
        this.setLayout(new GridLayout(1,3));
        labelname = new JLabel(name);
        this.add(labelname);
        textFieldQuantity = new JTextField();
        this.add(textFieldQuantity);
        labelMinutes = new JLabel("Minutos");
        this.add(labelMinutes);
    }

    public int getQuantity() {
        return Integer.parseInt(textFieldQuantity.getText());
    }

    public void createExtraProcess(String name){
        labelMinutes.setText("minutos - "+name);
    }
}
