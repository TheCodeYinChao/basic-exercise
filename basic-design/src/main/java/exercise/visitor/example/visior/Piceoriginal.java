package exercise.visitor.example.visior;

public class Piceoriginal extends AbstractFinalPrice implements Elment {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void rule() {
        System.out.println("原生规则");
    }

    @Override
    public double getValue() {
        return finalValue;
    }
}
