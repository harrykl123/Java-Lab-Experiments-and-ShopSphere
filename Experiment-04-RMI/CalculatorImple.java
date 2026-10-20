
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CalculatorImple extends UnicastRemoteObject implements Calculator {

    public CalculatorImple() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }
}