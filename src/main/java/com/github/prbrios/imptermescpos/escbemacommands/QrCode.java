package com.github.prbrios.imptermescpos.escbemacommands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.github.prbrios.imptermescpos.enums.QrCodeSize;
import com.github.prbrios.imptermescpos.interfaces.command.IQrCode;

public class QrCode implements IQrCode {

	@Override
	public byte[] print(String qrData) {
		return print(qrData, QrCodeSize.Size0);
	}

	@Override
	public byte[] print(String qrData, QrCodeSize qrCodeSize) {
		
		int t1 = qrData.length();
		int t2 = 0;
		
		
		if(qrData.length() > 255) {
			t1 = (qrData.length() % 255);
			t2 = (qrData.length() / 255);
		}
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
		
			bos.write(new byte[] {27, 97, 1});
			bos.write(new byte[] {29, 107, 81});
			bos.write(new byte[] {2, (byte) qrCodeSize.getSize(), 9, 1}); //2, 0, 3, 1  - 0, 2, 
			bos.write(new byte[] {(byte)t1, (byte)t2});
			for (int i=0; i < qrData.length(); i++) {
				bos.write((qrData.charAt(i) + "").getBytes()[0]);
			}
			
			bos.write(10);
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};
	}
	
}
