package com.hse.command;

public class TimingDecorator implements Command {
    private final Command command;

    public TimingDecorator(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        long start = System.nanoTime();
        command.execute();
        long end = System.nanoTime();
        System.out.println("Время выполнения: " + (end - start) / 1_000_000 + " мс");
    }
}
