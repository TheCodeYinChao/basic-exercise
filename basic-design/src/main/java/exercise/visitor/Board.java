package exercise.visitor;

public class Board extends ComputerPart {
    @Override
    public void accept(Visitor visitor) {
        visitor.viditor(this);
    }
}
