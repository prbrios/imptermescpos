package com.github.prbrios.imptermescpos.escbemacommands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.github.prbrios.imptermescpos.enums.PrinterModeState;
import com.github.prbrios.imptermescpos.interfaces.command.IFontMode;

public class FontMode implements IFontMode {

	@Override
	public byte[] italic(String value) {
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(this.italic(PrinterModeState.On));
			bos.write(value.getBytes());
			bos.write(this.italic(PrinterModeState.Off));
			bos.write(new byte[] {10});

			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

	@Override
	public byte[] italic(PrinterModeState state) {
		
		return state.equals(PrinterModeState.On) 
			? new byte[] {27, 52} 
			: new byte[] {27, 53};
	}

	@Override
	public byte[] bold(String value) {
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(this.bold(PrinterModeState.On));
			bos.write(value.getBytes());
			bos.write(this.bold(PrinterModeState.Off));
			bos.write(new byte[] {10});

			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

	@Override
	public byte[] bold(PrinterModeState state) {
		return state.equals(PrinterModeState.On) 
				? new byte[] {27, 69} 
				: new byte[] {27, 70};
	}

	@Override
	public byte[] underline(String value) {
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(this.underline(PrinterModeState.On));
			bos.write(value.getBytes());
			bos.write(this.underline(PrinterModeState.Off));
			bos.write(new byte[] {10});

			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};
		
	}

	@Override
	public byte[] underline(PrinterModeState state) {
		return state.equals(PrinterModeState.On) 
				? new byte[] {27, 45, 1} 
				: new byte[] {27, 45, 0};
	}

	@Override
	public byte[] expanded(String value) {
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(this.expanded(PrinterModeState.On));
			bos.write(value.getBytes());
			bos.write(this.expanded(PrinterModeState.Off));
			bos.write(new byte[] {10});

			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

	@Override
	public byte[] expanded(PrinterModeState state) {
		return new byte[] { 27, 87, (byte) (state.equals(PrinterModeState.On) ? 49 : 48) };
	}

	@Override
	public byte[] condensed(String value) {
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
			bos.write(this.condensed(PrinterModeState.On));
			bos.write(value.getBytes());
			bos.write(this.condensed(PrinterModeState.Off));
			bos.write(new byte[] {10});

			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[] {0};

	}

	@Override
	public byte[] condensed(PrinterModeState state) {
		return state.equals(PrinterModeState.On) 
				? new byte[] {27, 15} 
				: new byte[] {27, 80};
	}

}
