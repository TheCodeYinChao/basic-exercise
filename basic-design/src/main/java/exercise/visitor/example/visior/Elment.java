package exercise.visitor.example.visior;

public interface Elment {
    void accept(Visitor visitor);

    void  rule();

    double getValue();
}
