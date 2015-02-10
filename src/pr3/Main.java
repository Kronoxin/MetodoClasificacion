package pr3;

import javax.swing.UIManager;

import pr3.gui.Ventana;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    }
	    catch (Exception e) {}
		
		new Ventana();
				
	}
}
