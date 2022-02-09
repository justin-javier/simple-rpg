import java.lang.String;
import java.io.*;
import java.io.File; 

public class level extends rpgMain
{

	public static boolean Grimhelm() throws IOException
	{
		String action;

		System.out.println("You walk down the path and see a Grimhelm sign next to a camp.");
		System.out.println("Apparently Grimhelm isn't a place. It's a 12 foot tall troll...");
		while (true)
		{
			System.out.println("The troll is busy cooking. What will you do?\n");
			System.out.println("Poke		Compliment		Shout");

			action = b.readLine();

			clearScreen();
			if (action.equalsIgnoreCase("Poke"))
			{
				System.out.println("Grimhelm is furious you disturbed him!");
				break;
			}
			else if (action.equalsIgnoreCase("Compliment"))
			{
				System.out.println("Grimhelm is surprised at the compliment, but blushes.");
				System.out.println("'Would you like to try some?' he asks. Looks like he's cooking some type of human stew");
				System.out.println("Yes 		No");
				
				action = b.readLine();
				clearScreen();
				if (action.equalsIgnoreCase("Yes"))
				{
					System.out.println("Grimhelm smiles and gives you a spoonful. You pretend to love it.");
					System.out.println("Impressed by your strange charisma, Grimhelm offers help.");
					System.out.println("'If you're looking for the nearest town, I picked up my dinner a mile north of here.'");
					System.out.println("For the sacrifice you made, you avoid confrontation and made it out of town.");
					System.out.println("The taste of human flesh has driven you slightly insane. Regardless, you've survived the forest.");
					return true;
				}
				else
				{
					System.out.println("Grimhelm is unimpressed with your response. He gives you no time to think and charges!");
					break;
				}
			}
			else if (action.equalsIgnoreCase("Shout"))
			{
				System.out.println("Grimhelm turns around and sees you. He is furious you disturbed him.");
				System.out.println("'Foolish hero. I was just thinking I could use some more protein!'");
				break;
			}
			else
			{
				System.out.println("That seemed to do nothing. Try again.");
			}
		}

		// Initiate fight
		fight();

		if (player.health < 0) return false;
		else return true;
	}

	// Simple Function to print relevant values during fight
	public static void printFightInfo()
	{
		System.out.println("Health: " + player.health);
		System.out.println("Special Charge: " + player.special);
		System.out.println("Potions: " + player.potions);

		System.out.println("\nGrimhelm Health: " + grimhelm.health);
		System.out.println("Grimhelm Special Charge: " + grimhelm.special + "\n");
	}
	public static void fight() throws IOException
	{
		grimhelm = new Enemy();
		String action;  // Holds action taken
		int dmg;  // Holds damage dealt
		int enemymove; // Holds Grimhelm's attack decision
		int enemydmg; // Holds damage received

		System.out.println("A fight initiates!");
		while (true)
		{
			if (player.health < 0)
			{
				System.out.println("You fall to the ground and slowly die..");
				System.out.println("Grimhelm's might was too much to bear.");

				break;
			}
			printFightInfo();

			System.out.println("Choose an action:");
			System.out.println("Attack 		Use Potion");
			action = b.readLine();
			clearScreen();

			if (action.equalsIgnoreCase("Attack"))
			{
				while(true)
				{
					printFightInfo();
					System.out.println("Select an attack:");

					// Prints available attacks
					for (int i = 0; i < 3; i++)
					{
						if (i == 2 && player.special != 100) break;
						System.out.print(player.attacks[i] + " 		");
					}

					System.out.println("\n");
					action = b.readLine();
					clearScreen();

					dmg = attack(action);  // Calculate damage done
					if (dmg > 0)
					{
						grimhelm.health -= dmg;
						player.special += dmg;
						break;
					}
				}
			}
			else if (action.equalsIgnoreCase("Use Potion"))
			{
				usePotion();
			}
			else
			{
				System.out.println("Invalid action.. Try again");
			}

			// Check if Grimhelm is dead
			if (grimhelm.health < 0)
			{
				System.out.println("Grimhelm falls to his knees and collapses. The battle is won!");
				break;
			}

			// Grimhelms turn to attack
			if (grimhelm.special >= 100)
			{
				System.out.println("Grimhelm uses his special RKO move!");
				enemydmg = 60;
				System.out.println("Grimhelm does " + enemydmg + " damage to you!");
			}
			else
			{
				enemymove = (int)Math.floor(Math.random()*(2-1+1)+1);
				if (enemymove == 1)
				{
					System.out.println("Grimhelm smashes his fist on your body!");
					enemydmg = (int)Math.floor(Math.random()*(35-20+1)+20);
					System.out.println("Grimhelm does " + enemydmg + " damage to you!");
				}
				else
				{
					System.out.println("Grimhelm kicks into your chest!");
					enemydmg = (int)Math.floor(Math.random()*(45-15+1)+15);
					System.out.println("Grimhelm does " + enemydmg + " damage to you!");
				}
			}

			player.health -= enemydmg;
			grimhelm.special += enemydmg / 2;
		}

	}

	public static void main (String[] args) throws IOException
	{
		String action;
		boolean lvl_loop = true;
		boolean win;  // To see if player won or lost

		player = chooseClass();
		System.out.println("Beginning game...");

		while (lvl_loop)
		{
			System.out.println("A signpost stands eerily in the woods. For hours you've been struggling to find your way out.");
			System.out.println("The sign says:");
			System.out.println("Ahead: Grimhelm");
			System.out.println("Where will you go?\n");
			System.out.println("Forward		Left 		Right");
			action = b.readLine();

			clearScreen();
			if (action.equalsIgnoreCase("Forward"))
			{
				lvl_loop = false;
			}
			else if (action.equalsIgnoreCase("Left") || action.equalsIgnoreCase("Right"))
			{
				System.out.println("All there is to your " + action + " is trees and bushes.");
			}
			else
			{
				System.out.println("Try again..");
			}
		}

		win = Grimhelm();  // Run story

		if (win)
		{
			System.out.println("VICTORY!");
		}
		else
		{
			System.out.println("DEFEAT");
		}
	}



}