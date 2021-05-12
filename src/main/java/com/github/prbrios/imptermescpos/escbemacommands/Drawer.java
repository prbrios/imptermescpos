package com.github.prbrios.imptermescpos.escbemacommands;

import com.github.prbrios.imptermescpos.interfaces.command.IDrawer;

public class Drawer implements IDrawer {

	@Override
	public byte[] open() {
		return new byte[] {27, 118, (byte) 140};
	}

}
