package com.github.prbrios;

import org.junit.Test;

import com.github.prbrios.imptermescpos.Printer;
import com.github.prbrios.imptermescpos.enums.PrinterType;

public class AppTest 
{

    @Test
    public void testeBematech()
    {
        Printer printer = new Printer("MP-4200 TH", PrinterType.Bematech);
        printer.testPrinter();
        printer.printDocument(1);
    }

}
