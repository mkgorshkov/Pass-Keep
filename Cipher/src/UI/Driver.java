package UI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.InputMismatchException;
import java.util.Scanner;

import Encrypt.Decrypt.GeneratePassword;

public class Driver {
	
	static int mainChoice;
	static String passWord;
	
	public static void mainMenu(){
		System.out.println("==========================================");
		System.out.println("||\t\t\t\t\t||"); 
		System.out.println("||\t  Pasword Generator\t\t||");
		System.out.println("||\t  and Passsword Storage\t\t||");
		System.out.println("||\t\t\t\t\t||");
		System.out.println("==========================================");
		System.out.println("Input option number to continue.");
		System.out.println();
		System.out.println("1) Generate Password");
		System.out.println("2) Access Password List");
		System.out.print("Selection: ");
	}
	
	public static void generatePassword(){
		Scanner input = new Scanner(System.in);
		int length = 0;
		String capitals = "No";
		String symbols = "No";
		boolean caps;
		boolean symbs;
		
		System.out.println();
		System.out.println("==========================================");
		System.out.println("\t  Generating Password");
		System.out.println("==========================================");
		System.out.println();
		try{
			System.out.println("Please enter the length of the desired password (ex. 3): ");
			length = input.nextInt();
			System.out.println("Would you like to use capitals? (Y/N) ");
			capitals = input.next();
			System.out.println("Would you like to use symbols? (Y/N) ");
			symbols = input.next();
		}catch(InputMismatchException e){
			System.err.println("Excepted to be an integer for length and Y or N for capitals and symbols.");
			System.exit(0);
		}
		
		if(capitals.contains("Y")){
			caps = true;
		}else{
			caps = false;
		}
		
		if(symbols.contains("Y")){
			symbs = true;
		}else{
			symbs = false;
		}
		GeneratePassword pass = new GeneratePassword(length, caps, symbs);
		passWord = pass.getPass();
		
		System.out.println("Your new password is:\t\t"+ passWord);
		System.out.println("Would you like to save your password to a file? (Y/N) ");
		String savepass = input.next();
		
		if(savepass.contains("Y")){
			savePassword();
		}else{
			System.out.println("Thank you. Program will now terminate.");
			System.exit(0);
		}
	}
	
	public static void savePassword(){
		Scanner input = new Scanner(System.in);
		String filename = "";
		System.out.println();
		System.out.println("==========================================");
		System.out.println("\t  Saving Password");
		System.out.println("==========================================");
		System.out.println();
		System.out.println("Please enter a filename (ex. myPassword): ");
		filename = input.next();
		
		File writeFile = new File("output_files\\"+filename+".txt");
		try {
			writeFile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			FileWriter writer = new FileWriter(writeFile);
			writer.write(passWord);
			writer.close();
		} catch (IOException e) {
			System.err.print("Something went wrong while writing to file.");
			System.exit(0);
		}
		
		System.out.println("New password written to {program directory}\\output_files\\"+filename+".txt");
		System.out.println("Thank you.");
		System.exit(0);
	}
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		mainMenu();
		
		try{
			int temp = input.nextInt();
			if(temp == 1 || temp == 2){
				mainChoice = temp;
			}else{
				System.err.println("Incorrect input. Expected 1 or 2.");
				System.exit(0);
			}
		}catch(InputMismatchException e){
			System.err.println("Incorrect input. Expected 1 or 2.");
			System.exit(0);
		}
		
		if(mainChoice == 1){
			generatePassword();
		}else{
			System.out.println("This feature needs to be implementd.");
			//accessList();
		}
	}
}
