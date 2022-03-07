package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ElementStation extends JPanel {

    private JLabel labelname;
    private JTextField textFieldQuantity;
    private JLabel labelMinutes;

    public ElementStation(String name){
        this.setBackground(Color.WHITE);
        Font stationFont = new Font("Arial Narrow", Font.PLAIN,18);
        this.setLayout(new GridLayout(1,3));
        labelname = new JLabel(name);
        labelname.setFont(stationFont);
        this.add(labelname);
        textFieldQuantity = new JTextField();
        textFieldQuantity.setFont(stationFont);
        textFieldQuantity.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') ||
                        (caracter > '9')) &&
                        (caracter != '\b' /*corresponde a BACK_SPACE*/))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        this.add(textFieldQuantity);
        labelMinutes = new JLabel("Minutos");
        labelMinutes.setFont(stationFont);
        this.add(labelMinutes);
    }

    public int getQuantity() {
        return Integer.parseInt(textFieldQuantity.getText());
    }

    public void createExtraProcess(String name){
        labelMinutes.setText("minutos - "+name);
    }
}
