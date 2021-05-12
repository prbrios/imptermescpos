package com.github.prbrios.imptermescpos.escbemacommands;

import com.github.prbrios.imptermescpos.enums.Justifications;
import com.github.prbrios.imptermescpos.interfaces.command.IAlignment;

public class Alignment implements IAlignment {

	@Override
	public byte[] left() {
		return align(Justifications.Left);
	}

	@Override
	public byte[] right() {
		return align(Justifications.Right);
	}

	@Override
	public byte[] center() {
		return align(Justifications.Center);
	}
	
	private static byte[] align(Justifications justification) {
		
		byte align;
		
		switch (justification) {
		
			case Right:
				align = 50;
				break;
			case Center:
				align = 49;
				break;
			default:
				align = 48;
				break;
		}

		return new byte[] { 27, 97, align };
	}

}
