import java.rmi.Naming;

public class Server {

    public static void main(String[] args) {

        try {

            CalculatorImple obj = new CalculatorImple();

            Naming.rebind("rmi://localhost/manan", obj);

            System.out.println("RMI Server Started...");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}