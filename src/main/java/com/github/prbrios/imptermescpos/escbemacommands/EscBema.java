package com.github.prbrios.imptermescpos.escbemacommands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.github.prbrios.imptermescpos.enums.PrinterModeState;
import com.github.prbrios.imptermescpos.interfaces.command.IAlignment;
import com.github.prbrios.imptermescpos.interfaces.command.IBarCode;
import com.github.prbrios.imptermescpos.interfaces.command.IDrawer;
import com.github.prbrios.imptermescpos.interfaces.command.IFontMode;
import com.github.prbrios.imptermescpos.interfaces.command.IFontWidth;
import com.github.prbrios.imptermescpos.interfaces.command.IImage;
import com.github.prbrios.imptermescpos.interfaces.command.IInitializePrint;
import com.github.prbrios.imptermescpos.interfaces.command.IPaperCut;
import com.github.prbrios.imptermescpos.interfaces.command.IPrintCommand;
import com.github.prbrios.imptermescpos.interfaces.command.IQrCode;

public class EscBema implements IPrintCommand {

	private IFontMode fontMode;
	private IFontWidth fontWidth;
	private IAlignment alignment;
	private IPaperCut paperCut;
	private IDrawer drawer;
	private IQrCode qrCode;
	private IImage image;
	private IBarCode barCode;
	private IInitializePrint initializePrint;
	
	public EscBema() {
		this.fontMode = new FontMode();
		this.fontWidth = new FontWidth();
		this.alignment = new Alignment();
		this.paperCut = new PaperCut();
		this.drawer = new Drawer();
		this.qrCode = new QrCode();
		//this.image = new Image();
		this.barCode = new BarCode();
		this.initializePrint = new InitializePrint();
	}
	
	@Override
	public int colsNormal() {
		return 50;
	}

	@Override
	public int colsCondensed() {
		return 67;
	}

	@Override
	public int colsExpanded() {
		return 25;
	}

	@Override
	public IFontMode fontMode() {
		return this.fontMode;
	}

	@Override
	public IFontWidth fontWidth() {
		return this.fontWidth;
	}

	@Override
	public IAlignment alignment() {
		return this.alignment;
	}

	@Override
	public IPaperCut paperCut() {
		return this.paperCut;
	}

	@Override
	public IDrawer drawer() {
		return this.drawer;
	}

	@Override
	public IQrCode qrCode() {
		return this.qrCode;
	}

	@Override
	public IImage image() {
		return this.image;
	}

	@Override
	public IBarCode barCode() {
		return this.barCode;
	}

	@Override
	public IInitializePrint initializePrint() {
		return this.initializePrint;
	}

	@Override
	public byte[] separator() {
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(this.fontMode.condensed(PrinterModeState.On));
			for (int i=0; i < colsCondensed(); i++) {
				bos.write(45);
			}
			bos.write(this.fontMode.condensed(PrinterModeState.Off));
			bos.write((byte) 10);
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};
		
	}

	@Override
	public byte[] autoTest() {
		return new byte[] {0x1D, (byte) 0xf9, 0x29, 0x30};
	}

	
}
