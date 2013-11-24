package Password.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class makeList {
	private String fName;
	private File file;
	private Scanner scan;
	FileWriter write;
	Date date;
	String testData = "";
	public makeList(File f){
		file = f;
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		date = new Date();
	}
	
	public void writeFile(String inData){
		testData = inData;
		String crtDate = date.toString();
		
		String[] siftA = crtDate.split(" "); //Use i = 3
		String[] siftB = siftA[3].split(":"); //Use all 3
		
		testData = moveBy(testData, Integer.parseInt(siftA[2]));
		testData = moveBy(testData, Integer.parseInt(siftB[0]));
		testData = moveBy(testData, Integer.parseInt(siftB[1]));
		testData = moveBy(testData, Integer.parseInt(siftB[2]));

		try {
			write = new FileWriter(file);
			write.write(crtDate);
			write.write("\n");
			write.write(testData);
			write.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String moveBy(String a, int mod){
		char[] moveby = a.toCharArray();
		String moveString = "";
		for(int i = 0; i<moveby.length; i++){
			moveby[i] = (char) ((moveby[i]+mod)%127);

			moveString += moveby[i];
		}
		return moveString;
	}
	
	private String moveByDecryption(String a, int mod){
		char[] moveby = a.toCharArray();
		String moveString = "";
		for(int i = 0; i<moveby.length; i++){
			moveby[i] = (char) ((moveby[i]+(127-mod))%127);
			
			moveString += moveby[i];
		}
		return moveString;
	}
	
	public String readFile(){
		String inDate = "";
		String input = "";
		
		try{
			Scanner a = new Scanner(file);
			if(a.hasNextLine()){
				inDate = a.nextLine();
			}
			while(a.hasNext()){
				input += a.next();
			}
		}catch(IOException e){
			System.exit(0);
		}

		String[] siftA = inDate.split(" "); //Use i = 3
		String[] siftB = siftA[3].split(":"); //Use all 3
		
		testData = input;
		testData = moveByDecryption(testData, Integer.parseInt(siftA[2]));
		testData = moveByDecryption(testData, Integer.parseInt(siftB[0]));
		testData = moveByDecryption(testData, Integer.parseInt(siftB[1]));
		testData = moveByDecryption(testData, Integer.parseInt(siftB[2]));

		return testData;
	}
	
//	public static void main(String[] args){
//		makeList a = new makeList("output_files\\testFile.txt");
//		a.readFile();
//	//	a.writeFile();
//		
//	}
}
