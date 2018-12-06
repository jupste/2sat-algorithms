/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import comparison.BruteForce;
import comparison.KosarajuAlgorithm;
import comparison.TarjanAlgorithm;
import java.util.Scanner;
import util.CustomArrayList;
import util.GraphUtils;

/**
 *
 * @author jussiste
 */
public class ConsoleUI {

    private Scanner scanner;
    private BruteForce brute;
    private TarjanAlgorithm tarjan;
    private KosarajuAlgorithm kosaraju;
    private GraphUtils util;
    private int[] statement;
    private int numVariables;
    private boolean run;

    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
        this.brute = new BruteForce();
        this.util = new GraphUtils();
    }

    public void start() {
        System.out.println("Welcome to 2SAT-solver app!\n");
        String command = "";
        run = true;
        while (run) {
            while (true) {
                System.out.println("\nType \"new\" to insert an CNF, \"help\" for help or \"exit\" to exit the application");
                command = scanner.nextLine();
                if (command.equals("new") || command.equals("help") || command.equals("exit")) {
                    break;
                }
                System.out.println("Invalid command. Please try again.");
            }
            switch (command) {
                case "new":
                    insertNew();
                    break;
                case "help":
                    displayHelp();
                    break;
                case "exit":
                    System.out.println("Thank you for using this app.");
                    run = false;
            }

        }
    }

    public void displayHelp() {
        System.out.println("To insert a new CNF use the following format: \n1 2; -1 2; 3 1; -3 2");
        System.out.println("Where each number represents a variable. If the number has a minus sign in front it means it  is the negation of that variable.");
        System.out.println("Each disjunction pair is separated by a semicolon so for example above looks in mathematical form:");
        System.out.println("(1\u22282)\u2227(\u00AC1\u22282)\u2227(3\u22281)\u2227(\u00AC3\u22272)");
        System.out.println("Note that this is a 2SAT-solver so each disjunction can hold just two variables.");
    }

    public void insertNew() {
        System.out.println("Insert the CNF or type \"big\" to generate a large CNF:");
        CustomArrayList<Integer>[] graph = null;
        String CNF = scanner.nextLine();
        if (CNF.equals("big")) {
            System.out.println("How many variables?");
            int variables = Integer.parseInt(scanner.nextLine());
            numVariables = variables;
            System.out.println("Is the CNF satisfiable? y/n");
            String SAT = scanner.nextLine();
            switch (SAT) {
                case ("y"):
                    statement = util.initializeLargeSatisfiable(variables);
                case ("n"):
                    statement = util.initializeLargeNonSatisfiable(variables);
            }
        } else {
            statement = util.createFromString(CNF);
            numVariables = util.countVariables(statement);
        }

        graph = util.initializeCNF(statement);
        while (run) {
            System.out.println("Which algorithm do you want to use to solve this?");
            System.out.println("1: Tarjan algorithm\n2: Kosaraju algorithm\n3: Brute force method\n4: exit");
            String selection = scanner.nextLine();
            switch (selection) {
                case ("1"):
                    tarjan = new TarjanAlgorithm(graph, numVariables);
                    TarjanPrint();
                    break;
                case ("2"):
                    kosaraju = new KosarajuAlgorithm(graph, numVariables);
                    KosarajuPrint();
                    break;
                case ("4"):
                    System.out.println("Goodbye!");
                    run = false;
                    break;
                case ("3"):
                    System.out.println("Warning! Brute force method has an exponential time complexity. Using this with CNF with more than 20 variables will take a really long time. ");
                    System.out.println("Continue? y/n");
                    String affirmate = scanner.nextLine();
                    switch (affirmate) {
                        case ("y"):
                            bruteForcePrint();
                            break;
                        case ("n"):
                            break;
                    }
            }
        }
    }

    public void bruteForcePrint() {
        long start = System.currentTimeMillis();
        System.out.print("The given statement is ");
        if (brute.checkEveryCombination(numVariables, statement)) {
            System.out.println("satisfiable.");
        } else {
            System.out.println("not satisfiable.");
        }
        System.out.println("The calculation took " + (System.currentTimeMillis() - start) + " milliseconds.\n");
    }

    public void TarjanPrint() {
        long start = System.currentTimeMillis();
        System.out.println("The given statement is ");
        if (tarjan.checkSatisfiability()) {
            System.out.println("satisfiable.");
        } else {
            System.out.println("not satisfiable.");
        }
        System.out.println("The calculation took " + (System.currentTimeMillis() - start) + " milliseconds.\n");
    }

    public void KosarajuPrint() {
        long start = System.currentTimeMillis();
        System.out.println("The given statement is ");
        if (kosaraju.checkSatisfiability()) {
            System.out.println("satisfiable.");
        } else {
            System.out.println("not satisfiable.");
        }
        System.out.println("The calculation took " + (System.currentTimeMillis() - start) + " milliseconds.\n");
    }
}
