package com.github.prbrios.imptermescpos.enums;

public enum PrinterType {

	Epson(0),
	Bematech(1),
	Daruma(2);

	private int codigo;

	PrinterType(int codigo){
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

}
