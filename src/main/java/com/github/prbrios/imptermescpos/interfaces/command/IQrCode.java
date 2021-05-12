package com.github.prbrios.imptermescpos.interfaces.command;

import com.github.prbrios.imptermescpos.enums.QrCodeSize;

public interface IQrCode {

	byte[] print(String qrData);
	byte[] print(String qrData, QrCodeSize qrCodeSize);
	
}
