package sushi;

import model.Chef;

public interface Plate {
	 public enum Color {RED, GREEN, BLUE, GOLD}

     Sushi getContents();
     double getPrice();
     Plate.Color getColor();
     double getProfit();
     Chef getChef();  
}

