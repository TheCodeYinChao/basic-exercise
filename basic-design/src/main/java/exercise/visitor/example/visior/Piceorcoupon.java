package exercise.visitor.example.visior;

public class Piceorcoupon extends AbstractFinalPrice implements Elment{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void rule() {
        System.out.println("优惠券规则");
    }

    @Override
    public double getValue() {
        return finalValue;
    }
}
