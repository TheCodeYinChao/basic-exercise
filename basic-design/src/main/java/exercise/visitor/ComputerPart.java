package exercise.visitor;

public abstract class ComputerPart {
    public abstract void accept(Visitor visitor);
}
