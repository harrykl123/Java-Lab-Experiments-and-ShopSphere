public class Main {
    public static void main(String[] args) {
        Cmodals model = new Cmodals();
        Cviews view = new Cviews();
        new Ccontroller(model, view);
    }
}