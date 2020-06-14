
public class Zipcode {
	//define the different barcode patterns for each digit
	private static String bar_0 = "||:::";
	private static String bar_1 = ":::||";
	private static String bar_2 = "::|:|";
	private static String bar_3 = "::||:";
	private static String bar_4 = ":|::|";
	private static String bar_5 = ":|:|:";
	private static String bar_6 = ":||::";
	private static String bar_7 = "|:::|";
	private static String bar_8 = "|::|:";
	private static String bar_9 = "|:|::";
	
    private int zipCheck;
    private String zipString;
	private String barcodeString;
	
	public Zipcode (int zip) {
        //constructor 1 converts integer zip to String, in case of leading 0s
        int zip1 = zip/10000;
        int zip2 = ((zip/1000)-zip1*10);
        int zip3 = (zip/100 - (zip1*100 + zip2*10));
        int zip4 = (zip/10 -(zip1*1000 + zip2*100 + zip3*10));
        int zip5 = (zip - (zip1*10000 + zip2*1000 + zip3*100 + zip4*10));
        
        zipString = String.valueOf(zip1)+String.valueOf(zip2)+
        String.valueOf(zip3)+String.valueOf(zip4)+String.valueOf(zip5);
        
        zipCheck = 1000 - (zip1+zip2+zip3+zip4+zip5); //math for check digit
	}
	
	public Zipcode (String bar) {
        //constructor 2 sets up the barcode input
		barcodeString = bar;
	}
	
	public String getBarcode () {
        //retrieve the individual numbers from zip for conversion
		char digit_1 = zipString.charAt(0);
		char digit_2 = zipString.charAt(1);
		char digit_3 = zipString.charAt(2);
		char digit_4 = zipString.charAt(3);
		char digit_5 = zipString.charAt(4);
		char digit_check = (String.valueOf(zipCheck)).charAt(2);
        
        //assemble String of resulting barcode
		String resultingBarcode = "|"+
		convertToBarcode(digit_1)+
		convertToBarcode(digit_2)+
		convertToBarcode(digit_3)+
		convertToBarcode(digit_4)+
		convertToBarcode(digit_5)+
        convertToBarcode(digit_check)+"|";
        
        return resultingBarcode;
	}
	
    
	public String convertToBarcode (char digit) {
        //this changes a digit to the corresponding barcode pattern
		char currentDigit = digit;
        String convertedBar = "";
		switch (currentDigit) {
			case '0': convertedBar = bar_0; break;
			case '1': convertedBar = bar_1; break;
			case '2': convertedBar = bar_2; break;
			case '3': convertedBar = bar_3; break;
			case '4': convertedBar = bar_4; break;
			case '5': convertedBar = bar_5; break;
			case '6': convertedBar = bar_6; break;
			case '7': convertedBar = bar_7; break;
			case '8': convertedBar = bar_8; break;
			case '9': convertedBar = bar_9; break;
		}
        
        return convertedBar;
	}
	
	public String getZIPcode () {
        //scan consecutive parts of the barcode to convert into digits
		String barPart_1 = barcodeString.substring(1,6);
		String barPart_2 = barcodeString.substring(6,11);
		String barPart_3 = barcodeString.substring(11,16);
		String barPart_4 = barcodeString.substring(16,21);
		String barPart_5 = barcodeString.substring(21,26);
		String barPart_6 = barcodeString.substring(26,31);

		
        //convert bar code section to check digit
        int checkDigitValue = convertToZIP(barPart_6);
        
        //calculate what the check digit is supposed to be
        int reverseCheck = 1000 - (convertToZIP(barPart_1)+
        convertToZIP(barPart_2)+convertToZIP(barPart_3)+
        convertToZIP(barPart_4)+convertToZIP(barPart_5));
        
        int correctCheckDigit = Integer.parseInt(String.valueOf
        ((String.valueOf(reverseCheck)).charAt(2)));
        
        
        //put converted digits together into a single string
		String resultingZIP =
        String.valueOf(convertToZIP(barPart_1))+
		String.valueOf(convertToZIP(barPart_2))+
		String.valueOf(convertToZIP(barPart_3))+
		String.valueOf(convertToZIP(barPart_4))+
		String.valueOf(convertToZIP(barPart_5));
        
        //check if the value of the converted check digit is valid
        if(correctCheckDigit != checkDigitValue){
            resultingZIP = resultingZIP + " (Invalid check digit, try again)";
        }
        
        return resultingZIP;
	}
	
	public int convertToZIP (String barPortion) {
		//compares and converts barcode sections into their numbers
		if (barPortion.equals(bar_0)) {
			return 0;
		}
		else if (barPortion.equals(bar_1)) {
			return 1;
		}
		else if (barPortion.equals(bar_2)) {
			return 2;
		}
		else if (barPortion.equals(bar_3)) {
			return 3;
		}
		else if (barPortion.equals(bar_4)) {
			return 4;
		}
		else if (barPortion.equals(bar_5)) {
			return 5;
		}
		else if (barPortion.equals(bar_6)) {
			return 6;
		}
		else if (barPortion.equals(bar_7)) {
			return 7;
		}
		else if (barPortion.equals(bar_8)) {
			return 8;
		}
		else if (barPortion.equals(bar_9)) {
			return 9;
		}
        else {
            return -1;
        }
		

	}
	
	
}