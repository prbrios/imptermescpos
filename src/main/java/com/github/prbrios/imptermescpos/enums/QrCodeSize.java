package com.github.prbrios.imptermescpos.enums;

public enum QrCodeSize {

	Size0(0),
	Size1(4),
	Size2(6),
	Size3(8),
	Size4(10),
	Size5(12),
	Size6(14);
	
	private int size;
	
	QrCodeSize(int size){
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}

}
