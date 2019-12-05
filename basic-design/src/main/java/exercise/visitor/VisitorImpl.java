package exercise.visitor;

public class VisitorImpl implements Visitor {
    @Override
    public void viditor(Cpu cpu) {
        System.out.println("cpu");
        System.out.println(cpu.getPirce());
    }

    @Override
    public void viditor(Board cpu) {
        System.out.println("Board");
    }

    @Override
    public void viditor(Memory cpu) {
        System.out.println("Memory");
    }
}
