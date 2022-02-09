import java.lang.String;
import java.io.*;
import java.io.File; 

public class menu extends rpgMain
{
	public static void main(String[] args) throws IOException 
	{
		int selection = 0;  // Menu selection input value 
		boolean menuLoop = true;  
		String enter;  // Holds user input during instructions

		while(menuLoop)
		{
			printTitle(name);
			printOptions();
			System.out.println("Please enter an option.");
			
			try
			{
				selection = Integer.parseInt(b.readLine());
			}
			catch (NumberFormatException e)
			{
				selection = 0;
			}
			
			if (selection < 1 || selection > 3)  // Invalid number
			{
				rpgMain.clearScreen();
				System.out.println("Enter a valid number.");	
			}
			
			if (selection == 1)  // Start game option
			{
				menuLoop = false;
				rpgMain.startGame = true;
			}
			
			else if (selection == 2)  // Check instructions
			{
				rpgMain.clearScreen();
				System.out.println("The rules are simple. Type into the console whatever options are available.");
				System.out.println("\nType anything and press enter to continue.");
				enter = b.readLine();
				rpgMain.clearScreen();
			}
			
			else if (selection == 3)  // Quit option
			{
				menuLoop = false;  // Stop loop
				rpgMain.startGame = false;
				System.out.println("Exiting Game..");
			}
		}
	}

	public static void printTitle(String name)
	{
		System.out.println("Welcome " + name + ", to...\n");
		System.out.println("* *    * *     * * ");
		System.out.println("*  *   *  *   *");
		System.out.println("* *    * *    *  **");
		System.out.println("*  *   *       * *\n\n");
	}

	public static void printOptions()
	{
		System.out.println("1 - Start ");
		System.out.println("2 - Instructions ");
		System.out.println("3 - Quit ");
	}
}

