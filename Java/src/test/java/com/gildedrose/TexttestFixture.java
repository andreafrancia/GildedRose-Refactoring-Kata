package com.gildedrose;

public class TexttestFixture {

    public static void main(String[] args) {
        System.out.println("OMGHAI!");
        new Tester(new StdOut()).testAll(parseDays(args));
    }

    private static int parseDays(String[] args) {
        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }
        return days;
    }

    private static class StdOut implements LinePrinter {
        public void println(String line) {
            System.out.println(line);
        }
    }


}
