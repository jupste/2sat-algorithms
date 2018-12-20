package io;

import datastructures.CustomArrayList;

/**
 * A simple implementation of IO for testing purposes
 */
public class StubIO implements IO {
    private CustomArrayList<String> lines;
    private int i;
    private CustomArrayList<String> prints;

    public StubIO(CustomArrayList<String> lines) {
        this.lines = lines;
        i=0;
        prints = new CustomArrayList<>();
    }

    @Override
    public void println(String toPrint) {
        prints.add(toPrint);
    }

    public CustomArrayList<String> getPrints() {
        return prints;
    }

    @Override
    public String nextLine() {
        if (i < lines.size()) {
            return lines.get(i++);
        }
        return "";
    }

    @Override
    public void print(String toPrint) {
        prints.add(toPrint);
    }

}
