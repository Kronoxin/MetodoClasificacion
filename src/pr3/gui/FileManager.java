package pr3.gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {

	private static String lastPath = "";
	
	public static String[][] load(){
		
		ArrayList<String[]> rej = new ArrayList<String[]>();
		String[][] tabla = null;
		
		JFileChooser chooser = new JFileChooser(lastPath);
		chooser.setDialogTitle("Abrir");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Archivos .txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			String name = chooser.getSelectedFile().getPath();
			lastPath = name;
					
			FileInputStream file;
			
			try {

				file = new FileInputStream(name);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						file));

				String line = br.readLine();
				
				while(line != null){
					
					if (!line.equals(""))
						rej.add(line.split(","));
						
					line = br.readLine();
						
				}

				br.close();
				
				tabla = new String[rej.size()][rej.get(0).length];
				
				for (int i = 0; i < rej.size(); i++) {
					for (int j = 0; j < rej.get(i).length; j++){

						tabla[i][j] = rej.get(i)[j];
						
					}	
				}
			} 
			catch (FileNotFoundException e){ 
				JOptionPane.showMessageDialog(null, "ERROR"); //TODO Poner mensaje de error
			} 
			catch (IOException e){ 
				JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage()); 
			}				
		}
		
		return tabla;
		
	}
}
