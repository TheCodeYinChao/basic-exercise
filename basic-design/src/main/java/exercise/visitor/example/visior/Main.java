package exercise.visitor.example.visior;

public class Main {
    public static void main(String[] args) {

        Elment elment = new Piceoriginal();
        elment.accept(new VisitorImpl());

        double value = elment.getValue();
        System.out.println(value);


        System.out.println("=====================================");
        Elment elment1 = new Piceorcoupon();
        elment1.accept(new VisitorImpl());

        double value1 = elment1.getValue();
        System.out.println(value1);
    }
}
