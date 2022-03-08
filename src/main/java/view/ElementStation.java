package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ElementStation extends JPanel {

    private JLabel labelname;
    private JTextField textFieldQuantity;
    private JLabel labelMinutes;
    private Color backgroundColor;

    public ElementStation(String name){
        backgroundColor = Color.decode("#F6E9E8");
        this.setBackground(backgroundColor);
        Font stationFont = new Font("Arial Narrow", Font.PLAIN,18);
        this.setLayout(new GridLayout(1,3));
        labelname = new JLabel(name, SwingConstants.RIGHT);
        labelname.setFont(stationFont);
        this.add(labelname);
        textFieldQuantity = new JTextField();
        textFieldQuantity.setFont(stationFont);
        textFieldQuantity.addKeyListener(new KeyAdapter()
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
        this.add(textFieldQuantity);
        labelMinutes = new JLabel("Minutos");
        labelMinutes.setFont(stationFont);
        this.add(labelMinutes);
    }

    public int getQuantity() {
        if (textFieldQuantity.getText().equals("")){
            return 0;
        }else {
            return Integer.parseInt(textFieldQuantity.getText());
        }
    }

    public void createExtraProcess(String name){
        labelMinutes.setText("minutos - "+name);
    }
}
