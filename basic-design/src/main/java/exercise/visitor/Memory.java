package exercise.visitor;

public class Memory extends ComputerPart {
    @Override
    public void accept(Visitor visitor) {
        visitor.viditor(this);
    }
}
