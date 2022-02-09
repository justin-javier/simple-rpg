import java.lang.String;
import java.io.*;
import java.io.File; 

public class rpgMain extends gameData
{
	public static boolean startGame = false;
	
	public static void main(String[] args) throws IOException 
	{

		// Main menu section 
		System.out.println("What is your name?");
		name = b.readLine();
		
		menu.main(args);  // Runs main menu
		
		if (startGame == false) // Check to see if user quits
		{
			System.exit(0);
		}
		
		// Else, begin game
		clearScreen();
		System.out.println("Starting..");
		level.main(args);
	}

}
