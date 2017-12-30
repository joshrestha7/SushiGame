package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.BeltEvent;
import model.BeltObserver;
import model.Chef;
import model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private String balance_sb;
	private String spoiled_sb;
	private String consumed_sb;
	private JComboBox sb;
	
	@SuppressWarnings("unchecked")
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new GridLayout(4,1));
		add(display, BorderLayout.CENTER);
		display.setText(makeBalanceScoreboardHTML());
		
		
		//Scoreboard JComboBox
		add(new JLabel("Scoreboard Type:"));
		sb = new JComboBox(new String[]{"Balance", "Spoiled", "Consumed"});
		sb.addActionListener(this);
		sb.setActionCommand("scoreboard");
		add(sb);
		
	}

	private String makeBalanceScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		balance_sb = sb_html;
		return sb_html;
	}
	
	
	private String makeSpoiledScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by spoiled.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new SpoiledComparator());

		for (Chef c : chefs) {
			sb_html += c.getName() + " (" + Math.round(c.getSpoiled()*100.0)/100.0 + " oz) <br>";
		}
		spoiled_sb = sb_html;
		return sb_html;
	}
	
	private String makeConsumedScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by consumed.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new ConsumedComparator());

		for (Chef c : chefs) {
			sb_html += c.getName() + " (" + Math.round(c.getConsumed()*100.0)/100.0 + " oz) <br>";
		}
		consumed_sb = sb_html;
		return sb_html;
	}

	public void refresh() {
		switch (sb.getSelectedIndex()) {
		case 0:
			display.setText(makeBalanceScoreboardHTML());
			break;
		case 1:
			display.setText(makeSpoiledScoreboardHTML());
			break;
		case 2:
			display.setText(makeConsumedScoreboardHTML());
			break;
		}
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("scoreboard")) {
			refresh();
		}
	}
}
