package com.github.prbrios.imptermescpos.escbemacommands;

import com.github.prbrios.imptermescpos.interfaces.command.IPaperCut;

public class PaperCut implements IPaperCut {

	@Override
	public byte[] full() {
		return new byte[] {27, 105};
	}

	@Override
	public byte[] partial() {
		return new byte[] {27, 109};
	}

}
