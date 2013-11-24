package Password.Storage;

import java.io.File;
import java.util.Scanner;

public class accessList {
	private String date;
	private String fName;
	private File file;
	private Scanner scan;
	
	public accessList(File f){
	
		file = f;
		
		getDate();
	}
	
	private void getDate(){
		scan = new Scanner(System.in);
		if(scan.hasNext()){
			date = scan.nextLine();
		}else{
			System.err.println("There is nothing in the file.");
		}
		
		scan.close();
	}
}
