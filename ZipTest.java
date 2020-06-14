import java.util.Scanner;

public class ZipTest{

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);


	//convert from ZIP to bar code
        System.out.println("Please enter a ZIP code: ");
        int zip = input.nextInt();
        input.nextLine();

    //make a Zipcode object using int    
        Zipcode code1 = new Zipcode(zip);
		System.out.println("The corresponding bar code: ");
        System.out.println(code1.getBarcode());

	//convert from bar to ZIP code
	    System.out.println("Now please provide a bar code: ");
	    String bar = input.nextLine();

	//make a Zipcode object using String      
	    Zipcode code2 = new Zipcode(bar);
	    System.out.println("The corresponding ZIP code: ");
	    System.out.println(code2.getZIPcode());
    }
}