package controller;

import model.AlreadyPlacedThisRotationException;
import model.BeltEvent;
import model.BeltFullException;
import model.Chef;
import model.InsufficientBalanceException;
import sushi.AvocadoPortion;
import sushi.CrabPortion;
import sushi.EelPortion;
import sushi.GoldPlate;
import sushi.IngredientPortion;
import sushi.Plate;
import sushi.PlatePriceException;
import sushi.RicePortion;
import sushi.Roll;
import sushi.SalmonPortion;
import sushi.SeaweedPortion;
import sushi.ShrimpPortion;
import sushi.TunaPortion;

public class RollMakerChefController implements ChefController {

	private Chef chef;
	private double makeFrequency;
	private int belt_size;

	public RollMakerChefController(Chef c, int belt_size) {
		chef = c;
		makeFrequency = Math.random() * 0.5 + 0.25;
		this.belt_size = belt_size;
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {

		if (e.getType() == BeltEvent.EventType.ROTATE) {
			if (Math.random() < makeFrequency) {
				Roll random_roll = makeRandomRoll();
				Plate plate = null;
				try {
					plate = new GoldPlate(chef, random_roll, Math.random()*3 + 5.0);
				}
				catch (PlatePriceException exc) {
					// Roll too expensive for price we chose.
					// Bail and do not try to place plate.
					return;
				}
				try {
					chef.makeAndPlacePlate(plate, (int) (Math.random()*belt_size));
				} catch (InsufficientBalanceException | BeltFullException | AlreadyPlacedThisRotationException exc) {
					// Too little money, belt too full, or already went this rotation.
					// Bail and do nothing.
					return;
				}
			}


		}
	}

	private Roll makeRandomRoll() {
		
		return new Roll("Random Roll",
				new IngredientPortion[] {
						new AvocadoPortion(Math.random()),
						new CrabPortion(Math.random()),
						new EelPortion(Math.random()),
						new RicePortion(Math.random()),
						new SalmonPortion(Math.random()),
						new SeaweedPortion(Math.random()),
						new ShrimpPortion(Math.random()),
						new TunaPortion(Math.random()),
		});
}}