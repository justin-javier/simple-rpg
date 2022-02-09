import java.lang.String;
import java.io.*;
import java.io.File; 

public class gameData
{
	public static final int MAX_WARRIOR_HEALTH = 200;
	public static final int MAX_ARCHER_HEALTH = 150;
	public static final int MAX_MAGE_HEALTH = 125;
	public static final int POTION_VALUE = 50;

	public static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
	public static String name;  // Global name variable
	public static Hero player;
	public static Enemy grimhelm;

	public static class Character
	{
		int health;
		int special;  // Tracks progress towards special attack
		String attacks[]; 
	}

	public static class Hero extends Character
	{
		String heroClass;
		int potions;  // Unique to hero

		public Hero(String heroClass, int health, String attacks[])
		{
			this.heroClass = heroClass;
			this.health = health;
			this.special = 0;
			this.attacks = attacks;
			this.potions = 3;
		}
	}

	public static class Enemy extends Character 
	{
		public Enemy()
		{
			this.health = 300;
			this.special = 0;
		}
	}

	public static void clearScreen()
	{  
		// Function to clear the screen of all text

		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}
	public static Hero chooseClass() throws FileNotFoundException
	{
		// Function that allows player to choose desired class
		// Returns a string representing the class

		FileReader fr;  // Will hold text file with hero information
		BufferedReader br;

		Hero hero;
		String heroClass;
		int health = 0;
		String line = "";
		String attacks[] = new String[3];

		while (true)
		{
			System.out.println("Choose a class:");
			System.out.println("Warrior		Archer 		Mage");

			try
			{
				line = b.readLine();
			}
			catch (IOException e)
			{

			}

			if (line.equalsIgnoreCase("Warrior"))
			{
				fr = new FileReader("warrior.txt");
				System.out.println("You are now " + name + " the warrior!");
				heroClass = "Warrior";
				break;
			}
			else if (line.equalsIgnoreCase("Archer"))
			{
				fr = new FileReader("archer.txt");
				System.out.println("You are now " + name + " the archer!");
				heroClass = "Archer";
				break;
			}
			else if (line.equalsIgnoreCase("Mage"))
			{
				fr = new FileReader("mage.txt");
				System.out.println("You are now " + name + " the mage!");
				heroClass = "Mage";
				break;
			}
			else
			{
				clearScreen();
				System.out.println("Try again..");
			}
		}

		br = new BufferedReader(fr);

		try
		{
			health = Integer.valueOf(br.readLine());
			attacks = br.readLine().split(",");		
		}
		catch (IOException e)
		{
			
		}

		hero = new Hero(heroClass, health, attacks);
		return hero;
	}

	// Function that processes an attack
	public static int attack(String attack)
	{
		int dmg;

		clearScreen();

		if (attack.equalsIgnoreCase("swing"))
		{
			System.out.println(name + " swings his sword at Grimhelm!");
			dmg = (int)Math.floor(Math.random()*(30-50+1)+30);
			System.out.println(name + " does " + dmg + " damage!");
		}
		else if (attack.equalsIgnoreCase("stab"))
		{
			System.out.println(name + " impales Grimhelm!");
			dmg = (int)Math.floor(Math.random()*(20-70+1)+20);
			System.out.println(name + " does " + dmg + " damage!");
		}
		else if (attack.equalsIgnoreCase("smite") && player.special == 100)
		{
			System.out.println(name + " smites Grimhelm for massive damage!");
			dmg = 100;
			System.out.println(name + " does " + dmg + " damage!");

		}
		else if (attack.equalsIgnoreCase("shoot arrow"))
		{
			System.out.println(name + " shoots an arrow at Grimhelm!");
			dmg = (int)Math.floor(Math.random()*(40-60+1)+40);
			System.out.println(name + " does " + dmg + " damage!");
		}
		else if (attack.equalsIgnoreCase("throw knife"))
		{
			System.out.println(name + " throws a knife at Grimhelm!");
			dmg = (int)Math.floor(Math.random()*(30-80+1)+30);
			System.out.println(name + " does " + dmg + " damage!");
		}
		else if (attack.equalsIgnoreCase("barrage") && player.special == 100)
		{
			System.out.println(name + " releases a barrage for massive damage!");
			dmg = 125;
			System.out.println(name + " does " + dmg + " damage!");
		}
		else if (attack.equalsIgnoreCase("fireblast"))
		{
			System.out.println(name + " blasts Grimhelm with fire!");
			dmg = (int)Math.floor(Math.random()*(40-70+1)+40);
			System.out.println(name + " does " + dmg + " damage!");
		}
		else if (attack.equalsIgnoreCase("icestorm"))
		{
			System.out.println(name + " fires ice at Grimhelm!");
			dmg = (int)Math.floor(Math.random()*(20-90+1)+20);
			System.out.println(name + " does " + dmg + " damage!");
		}
		else if (attack.equalsIgnoreCase("explode") && player.special == 100)
		{
			System.out.println(name + " shoots an explosion for massive damage!");
			dmg = 150;
			System.out.println(name + " does " + dmg + " damage!");
		}
		else
		{
			System.out.println("Invalid attack..");
			dmg = -1;
		}
		return dmg;
	}

	// Function to use potion
	public static void usePotion()
	{
		if (player.potions == 0)
		{
			System.out.println("You are out of potions!");
		}
		else if (player.heroClass.equalsIgnoreCase("Warrior"))
		{
			if (player.health == MAX_WARRIOR_HEALTH)
			{
				System.out.println("Health is already full.");
			}
			else if (player.health >= 150)
			{
				player.potions -= 1;
				player.health = MAX_WARRIOR_HEALTH;
				System.out.println("Used potion!");
			}
			else
			{
				player.potions -= 1;
				player.health += POTION_VALUE;
				System.out.println("Used potion!");

			}
		}
		else if (player.heroClass.equalsIgnoreCase("Archer"))
		{
			if (player.health == MAX_ARCHER_HEALTH)
			{
				System.out.println("Health is already full.");
			}
			else if (player.health >= MAX_ARCHER_HEALTH)
			{
				player.potions -= 1;
				player.health = MAX_ARCHER_HEALTH;
				System.out.println("Used potion!");
			}
			else
			{
				player.potions -= 1;
				player.health += POTION_VALUE;
				System.out.println("Used potion!");
			}
		}
		else 
		{
			if (player.health == MAX_MAGE_HEALTH)
			{
				System.out.println("Health is already full.");
			}
			else if (player.health >= MAX_MAGE_HEALTH)
			{
				player.potions -= 1;
				player.health = MAX_MAGE_HEALTH;
				System.out.println("Used potion!");
			}
			else
			{
				player.potions -= 1;
				player.health += POTION_VALUE;
				System.out.println("Used potion!");
			}
		}
	}
}