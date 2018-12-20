/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import datastructures.CustomArrayList;
import io.StubIO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jussiste
 */
public class UserInterfaceTest {

    ConsoleUI ui;
    StubIO io;

    private void initializeTestOne() {
        CustomArrayList<String> lines = new CustomArrayList<>();
        lines.add("new");
        lines.add("1 2 -1 2");
        lines.add("1");
        lines.add("2");
        lines.add("3");
        lines.add("y");
        lines.add("5");
        lines.add("exit");
        io = new StubIO(lines);
    }

    private void initializeTestTwo() {
        CustomArrayList<String> lines = new CustomArrayList<>();
        lines.add("help");
        lines.add("new");
        lines.add("big");
        lines.add("10");
        lines.add("y");
        lines.add("1");
        lines.add("4");
        lines.add("5");
        lines.add("exit");
        io = new StubIO(lines);
    }

    private void initializeTestThree() {
        CustomArrayList<String> lines = new CustomArrayList<>();
        lines.add("help");
        lines.add("new");
        lines.add("big");
        lines.add("10");
        lines.add("n");
        lines.add("1");
        lines.add("4");
        lines.add("5");
        lines.add("exit");
        io = new StubIO(lines);
    }

    @Test
    public void UIallowsToCreateHandwrittenCNF() {
        initializeTestOne();
        ui = new ConsoleUI(io);
        ui.start();
        assertTrue(io.getPrints().contains("Thank you for using this app."));
    }

    @Test
    public void UIallowsToCreateGeneratedSatisfiableCNF() {
        initializeTestTwo();
        ui = new ConsoleUI(io);
        ui.start();
        assertTrue(io.getPrints().contains("satisfiable."));
    }

    @Test
    public void UIAllowstoCreateGeneratedNonSatisfiableCNF() {
        initializeTestThree();
        ui=new ConsoleUI(io);
        ui.start();
        assertTrue(io.getPrints().contains("not satisfiable."));
    }
}
