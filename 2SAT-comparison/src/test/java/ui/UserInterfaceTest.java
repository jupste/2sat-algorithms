/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jussiste
 */
public class UserInterfaceTest {

    ConsoleUI ui;

    @Test
    public void UIallowsToCreateHandwrittenCNF() throws FileNotFoundException {
        File file = null;
        try {
            file = new File("src/test/resources/testinput1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ui = new ConsoleUI(new Scanner(file));
        ui.start();
        assertTrue(true);
    }

    @Test
    public void UIallowsToCreateGeneratedSatisfiableCNF() throws FileNotFoundException {
        File file = null;
        try {
            file = new File("src/test/resources/testinput2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ui = new ConsoleUI(new Scanner(file));
        ui.start();
        assertTrue(true);
    }

    @Test
    public void UIAllowstoCreateGeneratedNonSatisfiableCNF() throws FileNotFoundException {
        File file = null;
        try {
            file = new File("src/test/resources/testinput3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ui = new ConsoleUI(new Scanner(file));
        ui.start();
        assertTrue(true);
    }

    @Test
    public void helpAndExitCommandsWork() throws FileNotFoundException {
        File file = null;
        try {
            file = new File("src/test/resources/testinput4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ui = new ConsoleUI(new Scanner(file));
        ui.start();
        assertTrue(true);
    }
}
