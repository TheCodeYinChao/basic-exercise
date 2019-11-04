package exercise.visitor;

public class Cpu extends ComputerPart {
    @Override
    public void accept(Visitor visitor) {
        visitor.viditor(this);
    }
    public int getPirce(){
        return 100;
    }
}
