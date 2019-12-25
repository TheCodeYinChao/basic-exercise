package exercise.visitor.example.visior;

public class VisitorImpl implements Visitor {
    @Override
    public void visit(Piceoriginal piceoriginal) {

        piceoriginal.rule();
        System.out.println("根据规则计算相关计算 原生");
        piceoriginal.finalValue = 100.00;
    }

    @Override
    public void visit(Piceorcoupon piceoriginal) {
        piceoriginal.rule();
        piceoriginal.finalValue = 90.00;
        System.out.println("根据规则计算相关计算 优化券");
    }

    @Override
    public void visit(Piceorinner piceoriginal) {
        piceoriginal.rule();
        System.out.println("根据规则计算相关计算 内部");
        piceoriginal.finalValue = 10.00;
    }
}
