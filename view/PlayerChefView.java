package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import sushi.AvocadoPortion;
import sushi.CrabPortion;
import sushi.EelPortion;
import sushi.IngredientPortion;
import sushi.Nigiri;
import sushi.RicePortion;
import sushi.Roll;
import sushi.SalmonPortion;
import sushi.Sashimi;
import sushi.SeaweedPortion;
import sushi.ShrimpPortion;
import sushi.Sushi;
import sushi.TunaPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private int belt_size;
	private Sushi sushi;
	
	private JComboBox plate_color, plate_position;
	private JSlider gold_price_slider;
	
	//Ingredient sliders
	private JSlider[] ing_slider_array;
	private JSlider avocado_sl, rice_sl, seaweed_sl, eel_sl, 
					crab_sl, shrimp_sl, salmon_sl, tuna_sl;
	private JTextField roll_text_field;
	private JButton roll_button;
	
	//Nigiri/Sashimi buttons
	private JComboBox nigiri_ing, sashimi_ing;
	private JButton nigiri_button, sashimi_button;
	
	
	@SuppressWarnings("unchecked")
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(new JLabel("Make A Plate:"));
		
		//Plate color
		add(new JLabel("Color:"));
		plate_color = new JComboBox(new String[]{"Red", "Green", "Blue", "Gold"});
		plate_color.addActionListener(this);
		add(plate_color);
		
		//Plate position
		add(new JLabel("Position:"));
		plate_position = new JComboBox();
		for (int i = 0; i < 20; i++) {
			plate_position.addItem(i);
		} 
		add(plate_position);
		
		//Gold price slider
		Hashtable gold_price_label = new Hashtable();
		for (int j = 5; j <= 10; j++) {
			gold_price_label.put((j * 10), new JLabel(Integer.toString(j)));
		}
		
		add(new JLabel("Gold Plate Price:"));
		gold_price_slider = new JSlider(JSlider.HORIZONTAL, 50, 100, 50);
		gold_price_slider.setPaintTicks(true);
		gold_price_slider.setPaintLabels(true);
		gold_price_slider.setLabelTable(gold_price_label);
		gold_price_slider.setMajorTickSpacing(10);
		gold_price_slider.setMinorTickSpacing(5);
		add(gold_price_slider);	

		
	//Roll

		add(new JLabel("Make A Roll:"));

		//Ingredient Portion Amount Slider
		Hashtable ingredient_label = new Hashtable();
		for (int k = 0; k <= 3; k++) {
			ingredient_label.put((k * 50), new JLabel(Double.toString(((double) k) * 0.5)));
		}

	//Sliders	
		avocado_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		avocado_sl.setPaintTicks(true);
		avocado_sl.setPaintLabels(true);
		avocado_sl.setMajorTickSpacing(50);
		avocado_sl.setMinorTickSpacing(10);
		avocado_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Avocado:"));
		add(avocado_sl);
		
		rice_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		rice_sl.setPaintTicks(true);
		rice_sl.setPaintLabels(true);
		rice_sl.setMajorTickSpacing(50);
		rice_sl.setMinorTickSpacing(10);
		rice_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Rice:"));
		add(rice_sl);
		
		seaweed_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		seaweed_sl.setPaintTicks(true);
		seaweed_sl.setPaintLabels(true);
		seaweed_sl.setMajorTickSpacing(50);
		seaweed_sl.setMinorTickSpacing(10);
		seaweed_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Seaweed:"));
		add(seaweed_sl);

		eel_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		eel_sl.setPaintTicks(true);
		eel_sl.setPaintLabels(true);
		eel_sl.setMajorTickSpacing(50);
		eel_sl.setMinorTickSpacing(10);
		eel_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Eel:"));
		add(eel_sl);
		
		crab_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		crab_sl.setPaintTicks(true);
		crab_sl.setPaintLabels(true);
		crab_sl.setMajorTickSpacing(50);
		crab_sl.setMinorTickSpacing(10);
		crab_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Crab:"));
		add(crab_sl);

		shrimp_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		shrimp_sl.setPaintTicks(true);
		shrimp_sl.setPaintLabels(true);
		shrimp_sl.setMajorTickSpacing(50);
		shrimp_sl.setMinorTickSpacing(10);
		shrimp_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Shrimp:"));
		add(shrimp_sl);

		salmon_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		salmon_sl.setPaintTicks(true);
		salmon_sl.setPaintLabels(true);
		salmon_sl.setMajorTickSpacing(50);
		salmon_sl.setMinorTickSpacing(10);		
		salmon_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Salmon:"));
		add(salmon_sl);

		tuna_sl = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		tuna_sl.setPaintTicks(true);
		tuna_sl.setPaintLabels(true);
		tuna_sl.setMajorTickSpacing(50);
		tuna_sl.setMinorTickSpacing(10);
		tuna_sl.setLabelTable(ingredient_label);
		add(new JLabel("Ounces Of Tuna:"));
		add(tuna_sl);
		

	//Non-slider
		//Text Field
		roll_text_field = new JTextField();
		add(new JLabel("Roll Name:"));
		add(roll_text_field);

		//Button
		roll_button = new JButton("Make Roll");
		roll_button.setActionCommand("roll");
		roll_button.addActionListener(this);
		add(roll_button);	

		
	//Nigiri 
		
		add(new JLabel("Type of Nigiri:"));
		
		//ComboBox	
		nigiri_ing = new JComboBox(new String[]{"Eel", "Shrimp", "Crab", "Salmon", "Tuna"});
		nigiri_ing.addActionListener(this);
		add(nigiri_ing);

		//Button
		nigiri_button = new JButton("Make Nigiri");
		nigiri_button.setActionCommand("nigiri");
		nigiri_button.addActionListener(this);
		add(nigiri_button);

		
	//Sashimi

		add(new JLabel("Type of Sashimi:"));

		//ComboBox	
		sashimi_ing = new JComboBox(new String[]{"Eel", "Shrimp", "Crab", "Salmon", "Tuna"});
		sashimi_ing.addActionListener(this);
		add(sashimi_ing);

		//Button
		sashimi_button = new JButton("Make Sashimi");
		sashimi_button.setActionCommand("sashimi");
		sashimi_button.addActionListener(this);
		add(sashimi_button);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		case "roll":
			
			//Roll name
			String roll_text = roll_text_field.getText();
			if (roll_text == null) {
				roll_text = "Player Roll";
			}
			
			ArrayList<IngredientPortion> ing_slider_portions = new ArrayList<IngredientPortion>();
			
			//Add ingredient portions to list if it is not 0
			if (avocado_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new AvocadoPortion(avocado_sl.getValue() / 100.0));
			}
			if (rice_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new RicePortion(rice_sl.getValue() / 100.0));
			}
			if (seaweed_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new SeaweedPortion(seaweed_sl.getValue() / 100.0));
			}
			if (eel_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new EelPortion(eel_sl.getValue() / 100.0));
			}
			if (crab_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new CrabPortion(crab_sl.getValue() / 100.0));
			}
			if (shrimp_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new ShrimpPortion(shrimp_sl.getValue() / 100.0));
			}
			if (salmon_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new SalmonPortion(salmon_sl.getValue() / 100.0));
			}
			if (tuna_sl.getValue() / 100.0 > 0) {
				ing_slider_portions.add(new TunaPortion(tuna_sl.getValue() / 100.0));
			}
			
			int a = ing_slider_portions.size();
			IngredientPortion[] final_ing_slider_portions = new IngredientPortion[a];
			for (int i = 0; i < a; i++) {
				final_ing_slider_portions[i] = ing_slider_portions.get(i);
			}
			
			//Sushi cannot not have ingredients
			if (final_ing_slider_portions.length == 0) {
				break;
			}

			sushi = new Roll(roll_text, final_ing_slider_portions);
			makePlateRequest();
	
			break;
			
		
		case "nigiri":
			switch (nigiri_ing.getSelectedIndex()) {
			case 0:
				sushi = new Nigiri(Nigiri.NigiriType.EEL);
				makePlateRequest();
				break;
			case 1:
				sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
				makePlateRequest();
				break;
			case 2:
				sushi = new Nigiri(Nigiri.NigiriType.CRAB);
				makePlateRequest();
				break;
			case 3:
				sushi = new Nigiri(Nigiri.NigiriType.SALMON);
				makePlateRequest();
				break;
			case 4:
				sushi = new Nigiri(Nigiri.NigiriType.TUNA);	
				makePlateRequest();
				break;
			}
			break;
		
		
		case "sashimi":
			switch(sashimi_ing.getSelectedIndex()) {
			case 0:
				sushi = new Sashimi(Sashimi.SashimiType.EEL);
				makePlateRequest();
				break;
			case 1:
				sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
				makePlateRequest();
				break;
			case 2:
				sushi = new Sashimi(Sashimi.SashimiType.CRAB);
				makePlateRequest();
				break;
			case 3:
				sushi = new Sashimi(Sashimi.SashimiType.SALMON);
				makePlateRequest();
				break;
			case 4:
				sushi = new Sashimi(Sashimi.SashimiType.TUNA);
				makePlateRequest();
				break;
			}
			break;
		}
	}
	

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
	
	private void makePlateRequest() {
		switch (plate_color.getSelectedIndex()) {
		case 0:
			makeRedPlateRequest(sushi, plate_position.getSelectedIndex());
			break;
		case 1:
			makeGreenPlateRequest(sushi, plate_position.getSelectedIndex());
			break;
		case 2:
			makeBluePlateRequest(sushi, plate_position.getSelectedIndex());
			break;
		case 3:
			makeGoldPlateRequest(sushi, plate_position.getSelectedIndex(), gold_price_slider.getValue() / 10.0);
			break;
		}
	}
	
}
