package com.github.prbrios.imptermescpos.interfaces.printer;

import java.awt.Image;

import com.github.prbrios.imptermescpos.enums.PrinterModeState;
import com.github.prbrios.imptermescpos.enums.QrCodeSize;

public interface IPrinter {

    void printDocument(int copies);
    void write(String value);
    void write(byte[] value);
    void writeLine(String value);
    void newLine();
    void newLines(int lines);
    void clear();

    void separator();
    void autoTest();
    void testPrinter();

    void italicMode(String value);
    void italicMode(PrinterModeState state);
    void boldMode(String value);
    void boldMode(PrinterModeState state);
    void underlineMode(String value);
    void underlineMode(PrinterModeState state);
    void expandedMode(String value);
    void expandedMode(PrinterModeState state);
    void condensedMode(String value);
    void condensedMode(PrinterModeState state);

    void normalWidth();
    void doubleWidth2();
    void doubleWidth3();

    void alignLeft();
    void alignRight();
    void alignCenter();

    void fullPaperCut();
    void fullPaperCut(boolean predicate);
    void partialPaperCut();
    void partialPaperCut(boolean predicate);

    void openDrawer();

    void qrCode(String qrData);
    void qrCode(String qrData, QrCodeSize qrCodeSize);


    void image(String path, boolean highDensity);
    //void image(Stream stream, boolean highDensity);
    void image(byte[] bytes, boolean highDensity);
    void image(Image image, boolean highDensity);

    void code128(String code);
    void code39(String code);
    void ean13(String code);

    void initializePrint();
    
}
