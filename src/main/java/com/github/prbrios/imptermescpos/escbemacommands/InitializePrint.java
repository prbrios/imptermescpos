package com.github.prbrios.imptermescpos.escbemacommands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.github.prbrios.imptermescpos.interfaces.command.IInitializePrint;

public class InitializePrint implements IInitializePrint {

	@Override
	public byte[] initialize() {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(new byte[] {27, 64});
			bos.write(setEscBema());
			bos.write(setLineSpace3((byte)20));

			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

	private static byte[] setEscBema() {
		return new byte[] {29, (byte)249, 32, 0};
	}
	
	private static byte[] setLineSpace3(byte range) {
		return new byte[] {29, 51, range};
	}

}
