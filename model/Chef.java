package model;

import sushi.Plate;
import sushi.Sushi;
import sushi.Plate.Color;

public interface Chef {

	String getName();
	void setName(String name);
	
	void makeAndPlacePlate(Plate plate, int position) 
			throws InsufficientBalanceException, BeltFullException, AlreadyPlacedThisRotationException;
		
	HistoricalPlate[] getPlateHistory(int max_history_length);
	HistoricalPlate[] getPlateHistory();
	
	double getBalance();
	
	boolean alreadyPlacedThisRotation();
	
	double getConsumed(); 
	
	double getSpoiled();

}

