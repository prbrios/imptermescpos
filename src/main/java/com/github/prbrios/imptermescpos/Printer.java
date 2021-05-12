package com.github.prbrios.imptermescpos;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import com.github.prbrios.imptermescpos.enums.PrinterModeState;
import com.github.prbrios.imptermescpos.enums.PrinterType;
import com.github.prbrios.imptermescpos.enums.QrCodeSize;
import com.github.prbrios.imptermescpos.escbemacommands.EscBema;
import com.github.prbrios.imptermescpos.helper.RawPrinterHelper;
import com.github.prbrios.imptermescpos.interfaces.command.IPrintCommand;
import com.github.prbrios.imptermescpos.interfaces.printer.IPrinter;

public class Printer implements IPrinter {

	private ByteArrayOutputStream buffer;
	private String printerName;
	private IPrintCommand command;
	
	@SuppressWarnings("unused")
	private PrinterType printerType;
	
	@SuppressWarnings("unused")
	private String encoding;
	
	public Printer(String printerName, PrinterType type, int colsNormal, int colsCondensed, int colsExpanded, String encoding) {
		
		this.buffer = new ByteArrayOutputStream();
		
		this.printerName = printerName == null || printerName.equals("") ? null : printerName.trim();
		this.printerType = type;
		this.encoding = encoding;
		
		switch(type) {
			case Bematech:
				
				this.buffer.write(29);
				this.buffer.write(249);
				this.buffer.write(32);
				this.buffer.write(0);
				
				this.buffer.write(29);
				this.buffer.write(249);
				this.buffer.write(55);
				this.buffer.write(8);
				
				this.command = new EscBema();
				break;
			case Epson:
				break;
			case Daruma:
				break;
		}

	}

	public Printer(String printerName, PrinterType type, int colsNormal, int colsCondensed, int colsExpanded) {
		this(printerName, type, colsNormal, colsCondensed, colsExpanded, null);
	}
	
	public Printer(String printerName, PrinterType type, String encoding) {
		this(printerName, type, 0, 0, 0, encoding);
	}
	
	public Printer(String printerName, PrinterType type) {
		this(printerName, type, 0, 0, 0, null);
	}
	
	@Override
	public void printDocument(int copies) {
		if (this.buffer == null) return;
		if (copies <= 0) copies = 1;
		
		for (int i=0; i< copies; i++) {
			if (!RawPrinterHelper.sendBytesToPrinter(this.printerName, this.buffer.toByteArray())) {
				throw new IllegalArgumentException("N\u00e3o foi poss\u00edvel acessar a impressora: " + this.printerName);
			}
		}
		
	}

	@Override
	public void write(String value) {
		this.writeString(value, false);
		
	}
	
	@Override
	public void write(byte[] value) {
		if (value == null) return;
		try {
			this.buffer.write(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeLine(String value) {
		this.writeString(value, true);
		
	}

	private void writeString(String value, boolean b) {
		if (value == null || value.trim().equals("")) return;
		
		if(b) value += "\n";
		
		try {
			this.buffer.write(value.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void newLine() {
		this.write("\n");
		
	}

	@Override
	public void newLines(int lines) {
		for (int i=0; i<lines; i++) {
			this.newLine();
		}
	}

	@Override
	public void clear() {
		this.buffer = new ByteArrayOutputStream();
	}

	@Override
	public void separator() {
		this.write(this.command.separator());
	}

	@Override
	public void autoTest() {
		this.write(this.command.autoTest());
	}

	@Override
	public void testPrinter() {
		
		this.alignLeft();
        this.writeLine("TESTE DE IMPRESS\u00e3O NORMAL - 48 COLUNAS");
        this.writeLine("....+....1....+....2....+....3....+....4....+...");
        
        this.separator();
        
        this.writeLine("Texto Normal");
        this.italicMode("Texto Italico");
        
        this.separator();
        
        this.alignRight();
        this.boldMode("Texto Negrito");
        this.underlineMode("Texto Sublinhado");
        
        this.separator();
        
        this.alignLeft();
        
        this.expandedMode(PrinterModeState.On);
        this.writeLine("Texto Expandido");
        this.expandedMode(PrinterModeState.Off);
        
        this.condensedMode(PrinterModeState.On);
        this.writeLine("Texto Condensado");
        this.writeLine("TEXTO CONDENSADO");
        this.condensedMode(PrinterModeState.Off);
        
        this.separator();
        
		this.alignCenter();
        this.write(this.command.barCode().code128("paulo rios")); newLine();
        this.write(this.command.barCode().code39("PAULO RIOS")); newLine();
        this.write(this.command.barCode().ean13("1234567891234"));
        this.qrCode("http://nfe.sefaz.go.gov.br/nfeweb/sites/nfce/danfeNFCe?p=52190830147903000160650010000024259271076610|2|1|01|96.95|755349634d5334716e6e54527477567273524342586a6f366e70553d|1|0E28B5EAD367D69ECA17B2EEBD9DFD1C62|1|0E28B5EAD367D69ECA17B2EEBD9DFD1C62|1|0E28B5E7Uz|755349634d5334716e6e54527477567273524342586a6f366e70553d|1|0E28B5EAD367D69ECA17B2EEBD9DFD1C62|1|0E28B5EAD367D69ECA17B2EEBD9DFD1C62|1|0E28B5E7Uzj", QrCodeSize.Size3);
        
        this.fullPaperCut();
		
	}

	@Override
	public void italicMode(String value) {
		this.write(this.command.fontMode().italic(value));
	}

	@Override
	public void italicMode(PrinterModeState state) {
		this.write(this.command.fontMode().italic(state));
	}

	@Override
	public void boldMode(String value) {
		this.write(this.command.fontMode().bold(value));
	}

	@Override
	public void boldMode(PrinterModeState state) {
		this.write(this.command.fontMode().bold(state));
	}

	@Override
	public void underlineMode(String value) {
		this.write(this.command.fontMode().underline(value));
	}

	@Override
	public void underlineMode(PrinterModeState state) {
		this.write(this.command.fontMode().underline(state));
	}

	@Override
	public void expandedMode(String value) {
		this.write(this.command.fontMode().expanded(value));
	}

	@Override
	public void expandedMode(PrinterModeState state) {
		this.write(this.command.fontMode().expanded(state));
	}

	@Override
	public void condensedMode(String value) {
		this.write(this.command.fontMode().condensed(value));
	}

	@Override
	public void condensedMode(PrinterModeState state) {
		this.write(this.command.fontMode().condensed(state));
	}

	@Override
	public void normalWidth() {
		this.write(this.command.fontWidth().normal());
	}

	@Override
	public void doubleWidth2() {
		this.write(this.command.fontWidth().doubleWidth2());
	}

	@Override
	public void doubleWidth3() {
		this.write(this.command.fontWidth().doubleWidth3());
	}

	@Override
	public void alignLeft() {
		this.write(this.command.alignment().left());
	}

	@Override
	public void alignRight() {
		this.write(this.command.alignment().right());
	}

	@Override
	public void alignCenter() {
		this.write(this.command.alignment().center());
	}

	@Override
	public void fullPaperCut() {
		this.write(this.command.paperCut().full());
		
	}

	@Override
	public void fullPaperCut(boolean predicate) {
		if (predicate) this.write(this.command.paperCut().full());
	}

	@Override
	public void partialPaperCut() {
		this.write(this.command.paperCut().partial());
	}

	@Override
	public void partialPaperCut(boolean predicate) {
		if (predicate) this.write(this.command.paperCut().partial());
	}

	@Override
	public void openDrawer() {
		this.write(this.command.drawer().open());
	}

	@Override
	public void qrCode(String qrData) {
		this.write(this.command.qrCode().print(qrData));
	}

	@Override
	public void qrCode(String qrData, QrCodeSize qrCodeSize) {
		this.write(this.command.qrCode().print(qrData, qrCodeSize));
	}

	@Override
	public void image(String path, boolean highDensity) {
		if (!new File(path).exists())
			throw new IllegalArgumentException("Imagem não encontrada: " + path);
		// TODO fazer a imagem
	}

	@Override
	public void image(byte[] bytes, boolean highDensity) {
		// TODO fazer a imagem
		
	}

	@Override
	public void image(Image image, boolean highDensity) {
		// TODO  fazer a imagem
		
	}

	@Override
	public void code128(String code) {
		this.write(this.command.barCode().code128(code));
	}

	@Override
	public void code39(String code) {
		this.write(this.command.barCode().code39(code));
	}

	@Override
	public void ean13(String code) {
		this.write(this.command.barCode().ean13(code));
	}

	@Override
	public void initializePrint() {
		RawPrinterHelper.sendBytesToPrinter(this.printerName, this.command.initializePrint().initialize());
		
	}

	public void resetPrinter() {
		this.write(new byte[] {29, (byte) 248, 70});
	}
	
}
