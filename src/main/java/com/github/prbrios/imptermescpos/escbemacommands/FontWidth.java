package com.github.prbrios.imptermescpos.escbemacommands;

import com.github.prbrios.imptermescpos.interfaces.command.IFontWidth;

public class FontWidth implements IFontWidth {

	@Override
	public byte[] normal() {
		return new byte[] { 27, 87, 0, 27, 100, 0 };
	}

	@Override
	public byte[] doubleWidth2() {
		return new byte[] { 27, 87, 0, 27, 100, 0 }; //27, 87, 1, 27, 100, 0
	}

	@Override
	public byte[] doubleWidth3() {
		return new byte[] { 27, 87, 0, 27, 100, 0 }; //29, 33, 32
	}

}
