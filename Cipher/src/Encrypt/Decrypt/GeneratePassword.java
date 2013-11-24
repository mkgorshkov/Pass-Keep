package Encrypt.Decrypt;

import java.util.Random;

public class GeneratePassword {
	private String output;
	private boolean useCapitals;
	private boolean useSymbols;
	private int length;
	private Random a;
	
	public GeneratePassword(int size, boolean caps, boolean symb){
		output = "";
		length = size;
		useCapitals = caps;
		useSymbols = symb;
		a = new Random();
		
		makePass();
	}
	
	private void makePass(){
		if(!useCapitals && !useSymbols){
			for(int i = 0; i < length; i++){
				output += "" + lowerCase();
			}
		}else if(!useCapitals && useSymbols){
			for(int i = 0; i < length; i++){
				output += "" + symbols();
			}
		}else if(!useSymbols && useCapitals){
			for(int i = 0; i < length; i++){
				output += "" + upperCase();
			}
		}else{
			for(int i = 0; i < length; i++){
				output += "" + upperCaseSymbols();
			}
		}
	}
	
	private char lowerCase(){
		return (char)(a.nextInt(26)+97);
	}
	
	private char upperCase(){
		boolean switchC = a.nextBoolean();
		if(switchC){
			return lowerCase();
		}
		return (char)(a.nextInt(26)+65);
	}
	
	private char symbols(){
		boolean switchC = a.nextBoolean();
		if(switchC){
			return lowerCase();
		}
		return (char)(a.nextInt(11)+33);
	}
	
	private char upperCaseSymbols(){
		int switchC = a.nextInt(3);
		if(switchC == 0){
			return lowerCase();
		}else if(switchC == 1){
			return (char)(a.nextInt(26)+65);
		}else{
			return (char)(a.nextInt(11)+33);
		}
	}
	
	public String getPass(){
		return output;
	}
	
//	public static void main(String[] args){
//		for(int i = 0; i<10; i++){
//		GeneratePassword a = new GeneratePassword(15, false, false);
//		System.out.println(a.getPass());
//		}
//		System.out.println("--");
//		
//		for(int i = 0; i<10; i++){
//			GeneratePassword a = new GeneratePassword(15, true, false);
//			System.out.println(a.getPass());
//			}
//		System.out.println("--");
//		
//		for(int i = 0; i<10; i++){
//			GeneratePassword a = new GeneratePassword(15, false, true);
//			System.out.println(a.getPass());
//			}
//		
//		System.out.println("--");
//		for(int i = 0; i<10; i++){
//			GeneratePassword a = new GeneratePassword(15, true, true);
//			System.out.println(a.getPass());
//			}
//		
//	}
}
