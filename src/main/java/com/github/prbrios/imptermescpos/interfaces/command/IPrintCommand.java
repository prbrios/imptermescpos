package com.github.prbrios.imptermescpos.interfaces.command;

public interface IPrintCommand {

	int colsNormal();
    int colsCondensed();
    int colsExpanded();
    IFontMode fontMode();
    IFontWidth fontWidth();
    IAlignment alignment();
    IPaperCut paperCut();
    IDrawer drawer();
    IQrCode qrCode();
    IImage image();
    IBarCode barCode();
    IInitializePrint initializePrint();
    byte[] separator();
    byte[] autoTest();

}
