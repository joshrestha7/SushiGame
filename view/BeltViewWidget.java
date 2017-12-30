package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Belt;
import sushi.Plate;
import sushi.Sushi;

public class BeltViewWidget extends JPanel {

	private Belt belt;
	private JLabel[] belt_labels;

	public BeltViewWidget(Belt b) {
		if (b != null) {
			this.belt = b;
			this.belt_labels = new JLabel[belt.getSize()];
			setLayout(new GridLayout(belt.getSize(), 1));
			for (int i = 0; i < belt.getSize(); i++) {
				JLabel plabel = new JLabel("");
				plabel.setMinimumSize(new Dimension(700, 45));
				plabel.setPreferredSize(new Dimension(700, 45));
				plabel.setOpaque(true);
				plabel.setBackground(Color.GRAY);
				add(plabel);
				this.belt_labels[i] = plabel;
			}
		}
		refresh();
	}

	//Returns JLabel with all info on it
	public JLabel[] refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			JLabel plabel = belt_labels[i];

			if (p == null) {
				plabel.setText("");
				plabel.setBackground(Color.GRAY);
			} else {	
				plabel.setText("<html>" + "Chef: " + belt.getPlateAtPosition(i).getChef().getName() + ", Roll Name: " + 
						getPlateInfo(i) + ", Age: " + belt.getAgeOfPlateAtPosition(i) + "</html>");
				switch (p.getColor()) {
				case RED:
					plabel.setBackground(Color.RED); break;
				case GREEN:
					plabel.setBackground(Color.GREEN); break;
				case BLUE:
					plabel.setBackground(Color.BLUE); break;
				case GOLD:
					plabel.setBackground(Color.YELLOW); break;
				}
			}
		}
		return belt_labels;
	}

	//Returns string of name and ingredients (roll)
	public String getPlateInfo(int position) {
		String sushiName; 
		Sushi contents = belt.getPlateAtPosition(position).getContents();
		String[] ingredients = new String[contents.getIngredients().length];

		//Creates an array of ingredients and their rounded amounts
		if (belt.getPlateAtPosition(position) != null) { 
			for (int i = 0; i < contents.getIngredients().length; i++) {
				ingredients[i] = contents.getIngredients()[i].getName() + 
						" (" + ((int) (contents.getIngredients()[i].getAmount() 
								* 100.0 + 0.5))/100.0 + ")";
			}
		}
		
		//String array of ingredients to String
		String ingredientList = Arrays.toString(ingredients); 

		//Roll or nigiri/sashimi
		if (contents.getName().contains("nigiri") || contents.getName().contains("sashimi")) {
			sushiName = contents.getName();
		} else { 
			sushiName = contents.getName() + ", Ingredients:  " + ingredientList;
		}

		return sushiName;	
	}
}
