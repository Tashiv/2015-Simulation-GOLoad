package com.tashiv.goload;

// imports
import java.util.Scanner;

// main method
public class Driver
{
	//// variables
	private static int fFrameSize = 0;											// height of terminal for rendering purposes
	private static Map fMap;
	public static final int fCritterCnt = 5;									// number of critters
	
	//// methods
	public static void main(String[] args)
	{
		// initialization
		fMap = new Map(fCritterCnt);
		fMap.generateFood();
		// calibrate program
		fFrameSize = callibrate();
		// ensure terminal is large enough
		if (fFrameSize > 20)
		{
			// make critters
			for (int i = 0; i < fCritterCnt; i++)
			{
				Critter objCritter = new Critter(fMap,i);
				objCritter.start();
			}
			// deploy feeder
			Feeder objFeeder = new Feeder(fMap);
			objFeeder.start();
			// render
			while  (fMap.isThereLife() == true)
			{	
				render();
				try
				{
				    Thread.sleep(200);
				}
				catch (InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
			}
		}
		else // case: terminal not big enough
		{
			System.out.println("Please make terminal space larger and then restart program.");
		}
	}
	
	public static void render()
	{
		// render map
		for (int Y = 0; Y < 10; Y++)
		{
			// formatting
			System.out.println("");
			// render map
			System.out.println(" ____________________"); 		// top border of row
			// formatting
			System.out.print(" ");
			for (int X = 0; X < 10; X++)
			{
				System.out.print("|" + fMap.readChar(X,Y));		// cell of row
			}
			System.out.print("|" + "     ");  				// end of cells
			// render statistics
			switch (Y)
			{
				case (2):
				{
					System.out.print("Statistics:");
					break;
				}
				case (3):
				{
					System.out.print("-----------------");
					break;
				}
				case (4):
				{
					System.out.print("Critter #0 - Days Lived: " + fMap.readMoveCnt(0) + " - Hunger: " + fMap.readFoodLevels(0));
					break;
				}
				case (5):
				{
					System.out.print("Critter #1 - Days Lived: " + fMap.readMoveCnt(1) + " - Hunger: " + fMap.readFoodLevels(1));
					break;
				}
				case (6):
				{
					System.out.print("Critter #2 - Days Lived: " + fMap.readMoveCnt(2) + " - Hunger: " + fMap.readFoodLevels(2));
					break;
				}
				case (7):
				{
					System.out.print("Critter #3 - Days Lived: " + fMap.readMoveCnt(3) + " - Hunger: " + fMap.readFoodLevels(3));
					break;
				}
				
				case (8):
				{
					System.out.print("Critter #4 - Days Lived: " + fMap.readMoveCnt(4) + " - Hunger: " + fMap.readFoodLevels(4));
					break;
				}
			}
		}
		System.out.println("");
		System.out.println(" ____________________"); 			// end of rows
		// extra space for correct placement
		for (int Z = 0; Z < fFrameSize - 21; Z++)				// -21 for map height
		{
			System.out.println("");
		}
	}
	
	public static int callibrate()
	{
		// initialization
		Scanner input = new Scanner(System.in);
		int iCnt = 0;
		// instructions
		System.out.println("Callibration Required: Please keep pressing enter until the '--- TOP ---' text can no longer be seen. Once this occurs type 's' and hit enter to finish this process.");
		// calibration
		System.out.println("---- TOP ----");
		System.out.print(">");
		String sInput = input.nextLine();
		while (sInput.equals(""))
		{
			System.out.print(">");
			iCnt ++;
			sInput = input.nextLine();
		}
		// apply result
		return iCnt - 1;
	}
}