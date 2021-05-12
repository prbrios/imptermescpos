package com.github.prbrios.imptermescpos.helper;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class RawPrinterHelper {
	
	public static boolean sendBytesToPrinter(String printerName, byte[] bytes) {
		
		try { 
			
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE; 
	        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
	        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras); 
	        PrintService service = findPrintService(printerName, printService); 
	        DocPrintJob job = service.createPrintJob(); 
            Doc doc = new SimpleDoc(bytes, flavor, null); 
            job.print(doc, null);
            
            return true;
            
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        
        return false;
		
	}
	
	private static PrintService findPrintService(String printerName, PrintService[] services) { 
		
		if(printerName == null) 
			return PrintServiceLookup.lookupDefaultPrintService();
		
        for (PrintService service : services) { 
            if (service.getName().equalsIgnoreCase(printerName)) { 
                return service; 
            } 
        }

        throw new IllegalArgumentException(); 
    } 

}
