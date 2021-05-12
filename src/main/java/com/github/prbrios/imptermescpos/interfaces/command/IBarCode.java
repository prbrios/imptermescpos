package com.github.prbrios.imptermescpos.interfaces.command;

public interface IBarCode {

	byte[] code128(String code);
	byte[] code39(String code);
	byte[] ean13(String code);

}
