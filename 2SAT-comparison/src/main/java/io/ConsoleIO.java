package io;

import java.util.Scanner;

public class ConsoleIO implements IO {

    private final Scanner scanner;

    public ConsoleIO() {
        this(new Scanner(System.in));
    }

    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void println(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public void print(String toPrint) {
        System.out.print(toPrint);
    }


}
