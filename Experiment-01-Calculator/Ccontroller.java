import java.awt.event.*;
import javax.swing.JOptionPane;

public class Ccontroller implements ActionListener {

    Cviews view;
    Cmodals model;

    public Ccontroller(Cmodals model, Cviews view) {

        this.model = model;
        this.view = view;

        view.add.addActionListener(this);
        view.sub.addActionListener(this);
        view.mul.addActionListener(this);
        view.div.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            double a = Double.parseDouble(view.txta.getText());
            double b = Double.parseDouble(view.txtb.getText());

            double result = 0;

            if (e.getSource() == view.add) {
                result = model.add(a, b);
            }

            else if (e.getSource() == view.sub) {
                result = model.sub(a, b);
            }

            else if (e.getSource() == view.mul) {
                result = model.mul(a, b);
            }

            else if (e.getSource() == view.div) {

                if (b == 0) {
                    JOptionPane.showMessageDialog(view, "Division by zero is not allowed.");
                    return;
                }

                result = model.div(a, b);
            }

            view.txtresult.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(view, "Please enter valid numbers.");
        }
    }
}