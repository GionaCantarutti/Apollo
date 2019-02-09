package prove;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProvaApplet extends Applet implements ActionListener {

    TextField t1 = new TextField();
    TextField t2 = new TextField();
    TextField t3 = new TextField();
    TextField t4 = new TextField();
    TextField t5 = new TextField();

        public ProvaApplet() {

            GridLayout layout = new GridLayout( 3, 2);

            setLayout(layout);

            add(new Label("N1"));
            add(t1);
            add(new Label("N2"));
            add(t2);
            add(new Label("sum"));
            add(t3);

            t2.addActionListener(this);


        }

    public void actionPerformed(ActionEvent e) {

            String testo1 = t1.getText();
            String testo2 = t2.getText();

            Double n1 = Double.parseDouble(testo1);
            Double n2 = Double.parseDouble(testo2);

            Double sum = n1 + n2;

            t3.setText(sum.toString());

    }
}
