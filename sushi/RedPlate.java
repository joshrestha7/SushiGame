package sushi;

import model.Chef;

public class RedPlate extends PlateImpl {

	public RedPlate(Chef chef, Sushi s) throws PlatePriceException {
		super(chef, s, 1.0, Plate.Color.RED);
	}
}
