import javax.swing.*;

public class Cviews extends JFrame {

    JLabel lblA, lblB, lblResult;

    JTextField txta, txtb, txtresult;

    JButton add, sub, mul, div;

    public Cviews() {

        setTitle("Calculator");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblA = new JLabel("Enter A");
        lblA.setBounds(30, 30, 80, 25);
        add(lblA);

        txta = new JTextField();
        txta.setBounds(120, 30, 120, 25);
        add(txta);

        lblB = new JLabel("Enter B");
        lblB.setBounds(30, 70, 80, 25);
        add(lblB);

        txtb = new JTextField();
        txtb.setBounds(120, 70, 120, 25);
        add(txtb);

        add = new JButton("+");
        add.setBounds(30, 110, 50, 30);
        add(add);

        sub = new JButton("-");
        sub.setBounds(90, 110, 50, 30);
        add(sub);

        mul = new JButton("*");
        mul.setBounds(150, 110, 50, 30);
        add(mul);

        div = new JButton("/");
        div.setBounds(210, 110, 50, 30);
        add(div);

        lblResult = new JLabel("Result");
        lblResult.setBounds(30, 160, 80, 25);
        add(lblResult);

        txtresult = new JTextField();
        txtresult.setBounds(120, 160, 120, 25);
        txtresult.setEditable(false);
        add(txtresult);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}