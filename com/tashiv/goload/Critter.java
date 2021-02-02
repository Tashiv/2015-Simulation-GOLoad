package com.tashiv.goload;

// imports
import java.util.Random;

// class
public class Critter extends Thread
{
	//// variables
	private Map fMap;
	private int fFoodLevel;
	private int fX;
	private int fY;
	private Random fRandomizer;
	private String fName;
	
	//// methods
	public Critter(Map theMap, int iName)
	{
		// initialize
		fMap = theMap;
		fName = iName + "";
		fFoodLevel = 500;		
		fRandomizer = new Random();
		// generate starting position
		fX = fRandomizer.nextInt(10);
		fY = fRandomizer.nextInt(10);
		// check if valid
		while ((fMap.readChar(fX, fY)).equals(" ") == false)
		{
			fX = fRandomizer.nextInt(10);
			fY = fRandomizer.nextInt(10);
		}
		// add critter to map
		fMap.writeChar(fX,fY,fName);
		fMap.updateFoodLevels(Integer.parseInt(fName),fFoodLevel);
	}
	
	public void run()
	{
		// simulate
		while (fFoodLevel > 0)
		{
			live();
		}
		// dies :(
		fMap.writeChar(fX,fY," ");
	}
	
	private void live()
	{
		// cost of living
		fFoodLevel = fFoodLevel - 1;
		// generate a random move direction - 0=N,1=W,2=E,3=S
		int iMove = fRandomizer.nextInt(4);							
		// attempt to move 
		switch (iMove)
		{
			case 0: // case: North
			{
				if ((fY - 1) >= 0)	// case: is valid map position
				{
					if (fMap.readChar(fX, fY-1).equals("*"))	// case: food in cell, can move and eat
					{
						fMap.writeChar(fX,fY," ");
						fY = fY - 1;
						fMap.writeChar(fX,fY,fName);
						fFoodLevel = fFoodLevel + 10;
					}
					else if (fMap.readChar(fX, fY-1).equals(" "))	// case: cell is empty, can move
					{
						fMap.writeChar(fX,fY," ");
						fY = fY - 1;
						fMap.writeChar(fX,fY,fName);
					}
				}
				break;
			}
			case 1: // case: West
			{
				if ((fX - 1) >= 0)	// case: is valid map position
				{
					if (fMap.readChar(fX-1, fY).equals("*"))	// case: food in cell, can move and eat
					{
						fMap.writeChar(fX,fY," ");
						fX = fX - 1;
						fMap.writeChar(fX,fY,fName);
						fFoodLevel = fFoodLevel + 10;
					}
					else if (fMap.readChar(fX-1, fY).equals(" "))	// case: cell is empty, can move
					{
						fMap.writeChar(fX,fY," ");
						fX = fX - 1;
						fMap.writeChar(fX,fY,fName);
					}
				}
				break;
			}
			case 2: // case: East
			{
				if ((fY + 1) < 10)	// case: is valid map position
				{
					if (fMap.readChar(fX, fY+1).equals("*"))	// case: food in cell, can move and eat
					{
						fMap.writeChar(fX,fY," ");
						fY = fY + 1;
						fMap.writeChar(fX,fY,fName);
						fFoodLevel = fFoodLevel + 10;
					}
					else if (fMap.readChar(fX, fY+1).equals(" "))	// case: cell is empty, can move
					{
						fMap.writeChar(fX,fY," ");
						fY = fY + 1;
						fMap.writeChar(fX,fY,fName);
					}
				}
				break;
			}
			case 3: // case: South
			{
				if ((fX + 1) < 10)	// case: is valid map position
				{
					if (fMap.readChar(fX+1, fY).equals("*"))	// case: food in cell, can move and eat
					{
						fMap.writeChar(fX,fY," ");
						fX = fX + 1;
						fMap.writeChar(fX,fY,fName);
						fFoodLevel = fFoodLevel + 10;
					}
					else if (fMap.readChar(fX+1, fY).equals(" "))	// case: cell is empty, can move
					{
						fMap.writeChar(fX,fY," ");
						fX = fX + 1;
						fMap.writeChar(fX,fY,fName);
					}
				}
				break;
			}
		}
		// update statistics
		fMap.incMoveCnt(Integer.parseInt(fName));
		fMap.updateFoodLevels(Integer.parseInt(fName),fFoodLevel);
		// moving take time
		try
		{
		    sleep(1);
		}
		catch (InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
}
