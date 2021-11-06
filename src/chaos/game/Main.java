package chaos.game;

import java.awt.EventQueue;

import chaos.game.gui.MainWindow;

public class Main {
	
	/*
	 * Removed ChaosGameGenerator class. Modified main class to launch the app as Event.
	 */

	public static void main(String[] args) {
		
		// Launch the application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});
		

	}

}








