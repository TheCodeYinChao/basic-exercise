package exercise.visitor;

public interface Visitor {
    public void viditor(Cpu cpu);

    public void viditor(Board cpu);

    public void viditor(Memory cpu);
}
