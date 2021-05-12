package com.github.prbrios.imptermescpos.interfaces.command;

import com.github.prbrios.imptermescpos.enums.PrinterModeState;

public interface IFontMode {

	byte[] italic(String value);
    byte[] italic(PrinterModeState state);
    byte[] bold(String value);
    byte[] bold(PrinterModeState state);
    byte[] underline(String value);
    byte[] underline(PrinterModeState state);
    byte[] expanded(String value);
    byte[] expanded(PrinterModeState state);
    byte[] condensed(String value);
    byte[] condensed(PrinterModeState state);
    
}
