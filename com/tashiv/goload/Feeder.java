package com.tashiv.goload;

// class
public class Feeder extends Thread
{
	//// variables
	Map fMap;
	
	//// methods
	public Feeder(Map theMap)
	{
		fMap = theMap;
	}
	
	public void run()
	{
		// feed the critters
		while (fMap.isThereLife() == true)
		{
			fMap.generateFood();
			// actions take time
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
}
