package exercise.visitor.example.visior;

public interface Visitor {
    public void visit(Piceoriginal piceoriginal);
    public void visit(Piceorcoupon piceoriginal);
    public void visit(Piceorinner piceoriginal);
}
