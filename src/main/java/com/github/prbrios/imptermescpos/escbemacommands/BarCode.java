package com.github.prbrios.imptermescpos.escbemacommands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.github.prbrios.imptermescpos.interfaces.command.IBarCode;

public class BarCode implements IBarCode {

	@Override
	public byte[] code128 (String code) {

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(new byte[] {29, 119, 2});
			bos.write(new byte[] {29, 104, 50});
			bos.write(new byte[] {29, 102, 1});
			bos.write(new byte[] {29, 72, 0});
			bos.write(new byte[] {29, 107, 73});
			bos.write(new byte[] {(byte)code.length()});
			bos.write(code.getBytes());
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

	@Override
	public byte[] code39 (String code) {
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(new byte[] {29, 119, 2});
			bos.write(new byte[] {29, 104, 50});
			bos.write(new byte[] {29, 102, 1});
			bos.write(new byte[] {29, 72, 0});
			bos.write(new byte[] {29, 107, 4});
			bos.write(code.getBytes());
			bos.write(0);
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

	@Override
	public byte[] ean13(String code) {
		
		if (code.trim().length() != 13)
			return new byte[] {0};
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(new byte[] {29, 119, 40});
			bos.write(new byte[] {29, 104, 50});
			bos.write(new byte[] {29, 72, 0});
			bos.write(new byte[] {29, 107, 67, 12});
			bos.write(code.substring(0, 12).getBytes());
			bos.write(0);
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

}
