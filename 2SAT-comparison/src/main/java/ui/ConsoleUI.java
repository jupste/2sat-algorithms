/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import algorithms.BruteForce;
import algorithms.KosarajuAlgorithm;
import algorithms.TarjanAlgorithm;
import datastructures.CustomArrayList;
import datastructures.CustomHashmap;
import io.IO;
import util.GraphUtils;
import util.UnicodeUtil;

/**
 *
 * @author jussiste
 */
public class ConsoleUI {
    private BruteForce brute;
    private TarjanAlgorithm tarjan;
    private KosarajuAlgorithm kosaraju;
    private GraphUtils util;
    private int[] statement;
    private int numVariables;
    private boolean run;
    private IO io;

    /**
     *
     * @param io
     */
    public ConsoleUI(IO io) {
        this.io = io;
        this.brute = new BruteForce();
        this.util = new GraphUtils();
        this.io = io;
    }

    /**
     * Main menu from the app that takes user input and moves to the next menu.
     */
    public void start() {
        io.println("Welcome to 2SAT-solver app!\n");
        String command = "";
        run = true;
        while (run) {
            while (true) {
                io.println("\nType \"new\" to insert an CNF, \"help\" for help or \"exit\" to exit the application");
                command = io.nextLine();
                if (command.equals("new") || command.equals("help") || command.equals("exit")) {
                    break;
                }
                io.println("Invalid command. Please try again.");
            }
            switch (command) {
                case "new":
                    insertNew();
                    break;
                case "help":
                    displayHelp();
                    break;
                case "exit":
                    io.println("Thank you for using this app.");
                    run = false;
            }

        }
    }

    /**
     * Displays the help menu
     */
    public void displayHelp() {
        io.println("To insert a new CNF use the following format: \n1 2; -1 2; 3 1; -3 2");
        io.println("Where each number represents a variable. If the number has a minus sign in front it means it  is the negation of that variable.");
        io.println("Each disjunction pair is separated by a semicolon so for example above looks in mathematical form:");
        io.println("(x" + UnicodeUtil.numbers[1] + " " + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[2] + ") " + UnicodeUtil.conjunction + " (" + UnicodeUtil.negation
                + "x" + UnicodeUtil.numbers[1] + " " + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[2] + ") " + UnicodeUtil.conjunction + " (x" + UnicodeUtil.numbers[3] + " "
                + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[1] + ") " + UnicodeUtil.conjunction + " (" + UnicodeUtil.negation + "x" + UnicodeUtil.numbers[3] + " " + UnicodeUtil.disjunction + " x" + UnicodeUtil.numbers[2] + ")");
        io.println("Note that this is a 2SAT-solver so each disjunction can hold just two variables.");
    }

    /**
     * Displays the menu that allows user to add a new CNF
     */
    public void insertNew() {
        io.println("Insert the CNF or type \"big\" to generate a large CNF:");
        String CNF = io.nextLine();
        if (CNF.equals("big")) {
            io.println("How many variables?");
            int variables;
            try {
                variables = Integer.parseInt(io.nextLine());
            } catch (NumberFormatException e) {
                variables = 1;
            }
            numVariables = variables;
            io.println("Is the CNF satisfiable? y/n");
            String SAT = io.nextLine();
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
            io.println("Which algorithm do you want to use to solve this?");
            io.println("1: Tarjan algorithm\n2: Kosaraju algorithm\n3: Brute force method\n4: Print the truth distribution that solves the CNF\n5: return");
            String selection = io.nextLine();
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
                    io.println("Warning! Brute force method has an exponential time complexity. Using this with CNF with more than 20 variables will take a really long time. ");
                    io.println("Continue? y/n");
                    String affirmate = io.nextLine();
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
                        printTruthAssesment();
                    } else {
                        io.println("Not satisfiable");
                    }
                    break;
                case ("5"):
                    io.println("Returning to main menu...");
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
        io.print("The given statement is ");
        if (brute.checkEveryCombination(numVariables, statement)) {
            io.println("satisfiable.");
        } else {
            io.println("not satisfiable.");
        }
        io.println("The calculation took " + (System.currentTimeMillis() - start) + " milliseconds.\n");
    }

    /**
     * Uses Tarjan algorithm to determine weather the CNF is satisfiable. Also
     * prints the time it took to solve the problem.
     */
    public void TarjanPrint() {
        long start = System.currentTimeMillis();
        io.println("The given statement is ");
        if (tarjan.checkSatisfiability()) {
            io.println("satisfiable.");
        } else {
            io.println("not satisfiable.");
        }
        io.println("The calculation took " + (System.currentTimeMillis() - start) + " milliseconds.\n");
    }

    /**
     * Uses Kosaraju algorithm to determine weather the CNF is satisfiable. Also
     * prints the time it took to solve the problem.
     */
    public void KosarajuPrint() {
        long start = System.currentTimeMillis();
        io.println("The given statement is ");
        if (kosaraju.checkSatisfiability()) {
            io.println("satisfiable.");
        } else {
            io.println("not satisfiable.");
        }
        io.println("The calculation took " + (System.currentTimeMillis() - start) + " milliseconds.\n");
    }
        /**
     * Prints a truth distribution that satisfies the given CNF. Note that this is only one of all the possible solutions.
     */
    public void printTruthAssesment(){
        for(CustomHashmap.HashMapEntry<Integer, Boolean> e:  tarjan.getTruthAssignment().entrySet()){
            if(e.getKey()>tarjan.getNumVariables()){
                continue;
            }else{
                String number="x";
                for(char c: e.getKey().toString().toCharArray()){
                    number+=UnicodeUtil.numbers[c-'0'];
                }
                io.println(number+" = "+ e.getValue());
            }
        }
    }
}
