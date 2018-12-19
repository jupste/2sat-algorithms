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
import util.UnicodeUtil;

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

    /**
     *
     * @param scanner
     */
    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
        this.brute = new BruteForce();
        this.util = new GraphUtils();
    }

    /**
     * Main menu from the app that takes user input and moves to the next menu.
     */
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

    /**
     * Displays the help menu
     */
    public void displayHelp() {
        System.out.println("To insert a new CNF use the following format: \n1 2; -1 2; 3 1; -3 2");
        System.out.println("Where each number represents a variable. If the number has a minus sign in front it means it  is the negation of that variable.");
        System.out.println("Each disjunction pair is separated by a semicolon so for example above looks in mathematical form:");
        System.out.println("(x" + UnicodeUtil.numbers[1] + " " + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[2] + ") " + UnicodeUtil.conjunction + " (" + UnicodeUtil.negation
                + "x" + UnicodeUtil.numbers[1] + " " + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[2] + ") " + UnicodeUtil.conjunction + " (x" + UnicodeUtil.numbers[3] + " "
                + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[1] + ") " + UnicodeUtil.conjunction + " (" + UnicodeUtil.negation + "x" + UnicodeUtil.numbers[3] + " " + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[2] + ")");
        System.out.println("Note that this is a 2SAT-solver so each disjunction can hold just two variables.");
    }

    /**
     * Displays the menu that allows user to add a new CNF
     */
    public void insertNew() {
        System.out.println("Insert the CNF or type \"big\" to generate a large CNF:");
        String CNF = scanner.nextLine();
        if (CNF.equals("big")) {
            System.out.println("How many variables?");
            int variables;
            try {
                variables = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                variables=1;
            }
            numVariables = variables;
            System.out.println("Is the CNF satisfiable? y/n");
            String SAT = scanner.nextLine();
            switch (SAT) {
                case ("y"):
                    statement = util.initializeLargeSatisfiable(variables);
                    break;
                case ("n"):
                    statement = util.initializeLargeNonSatisfiable(variables);
                    break;
            }
        } else {
            statement = util.createFromString(CNF);
            numVariables = util.countVariables(statement);
        }
        chooseAlgorithm();
    }

    /**
     * Displays the available algorithms that can be used to run the algorithm
     * and allows user to select one.
     */
    public void chooseAlgorithm() {
        boolean runloop = true;
        CustomArrayList<Integer>[] graph = null;
        graph = util.initializeCNF(statement);
        while (runloop) {
            System.out.println("Which algorithm do you want to use to solve this?");
            System.out.println("1: Tarjan algorithm\n2: Kosaraju algorithm\n3: Brute force method\n4: Print the truth distribution that solves the CNF\n5: return");
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
                    break;
                case ("4"):
                    tarjan = new TarjanAlgorithm(graph, numVariables);
                    if (tarjan.checkSatisfiability()) {
                        tarjan.printTruthAssesment();
                    } else {
                        System.out.println("Not satisfiable");
                    }
                    break;
                case ("5"):
                    System.out.println("Returning to main menu...");
                    runloop = false;
                    break;
            }
        }
    }

    /**
     * Uses the bruteForce method to determine weather the CNF is satisfiable.
     * Also prints the time it took to solve the problem.
     */
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

    /**
     * Uses Tarjan algorithm to determine weather the CNF is satisfiable. Also
     * prints the time it took to solve the problem.
     */
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

    /**
     * Uses Kosaraju algorithm to determine weather the CNF is satisfiable. Also
     * prints the time it took to solve the problem.
     */
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
