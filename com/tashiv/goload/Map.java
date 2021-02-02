package com.tashiv.goload;

// imports
import java.util.Random;

// class
public class Map
{
	//// variables
	private String[][] fMap;
	private Random fRandomizer;
	private int[] fMoveCnt;
	private int[] fFoodLevels;
	private int fNrOfCritters;
	
	//// methods
	public Map(int NrOfCritters)
	{
		// initialize
		fRandomizer = new Random();
		fMap = new String[10][10];
		fMoveCnt = new int[NrOfCritters];
		fFoodLevels = new int[NrOfCritters];
		fNrOfCritters = NrOfCritters;
		// clear map
		for (int Y = 0; Y < 10; Y++)
		{
			for (int X = 0; X < 10; X++)
			{
				fMap[Y][X] = " ";
			}
		}
	}
	
	public String readChar(int xPoint, int yPoint)
	{
		return fMap[yPoint][xPoint];
	}
	
	public void writeChar(int xPoint, int yPoint, String theCharacter)
	{
		fMap[yPoint][xPoint] = theCharacter;
	}
	
	public void generateFood()
	{
		// generate food coordinates
		int iX = fRandomizer.nextInt(10);
		int iY = fRandomizer.nextInt(10);
		// check if valid
		while ((fMap[iY][iX]).equals(" ") == false)
		{
			iX = fRandomizer.nextInt(10);
			iY = fRandomizer.nextInt(10);
		}
		// place food
		fMap[iY][iX] = "*";
	}
	
	public void incMoveCnt(int iName)
	{
		fMoveCnt[iName] ++;
	}
	
	public int readMoveCnt(int iName)
	{
		return fMoveCnt[iName];
	}
	
	public void updateFoodLevels(int iName,int iFoodLevel)
	{
		fFoodLevels[iName] = iFoodLevel;
	}
	
	public int readFoodLevels(int iName)
	{
		return fFoodLevels[iName];
	}
	
	public boolean isThereLife()
	{
		for (int i = 0; i < fNrOfCritters; i++)
		{
			if (fFoodLevels[i] > 0)
				return true;
		}
		return false;
	}
}
