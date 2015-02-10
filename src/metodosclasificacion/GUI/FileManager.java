package metodosclasificacion.GUI;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import metodosclasificacion.bayes.Clase;
import metodosclasificacion.bayes.Nodo;

public class FileManager {
    
    private static String path = "";
	
	public static ArrayList<Clase>[] loadAtributos(){
		
		String[] rej = null;
                ArrayList<Nodo> c1 = new ArrayList<>();
                ArrayList<Nodo> c2 = new ArrayList<>();

                Clase clase1;
                Clase clase2;
		
		JFileChooser chooser = new JFileChooser(path);
		chooser.setDialogTitle("Abrir Archivo de Atributos");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Archivos .txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			String name = chooser.getSelectedFile().getPath();
                        
                        path=name;
					
			FileInputStream file;
			
			try {

				file = new FileInputStream(name);
				BufferedReader br = new BufferedReader(new InputStreamReader(file));

				String line = null;
                                c1 = new ArrayList<>();
                                c2 = new ArrayList<>();
                                
                                clase1 = new Clase("Iris-setosa",c1);
                                clase2 = new Clase("Iris-versicolor",c2);
                                
                                while(br.ready())
                                {
                                    
                                    line = br.readLine();
                                    rej = line.split(",");
                                    Nodo nodoAIntroducir = new Nodo(Double.parseDouble(rej[0]),Double.parseDouble(rej[1]),Double.parseDouble(rej[2]),Double.parseDouble(rej[3]));
                                    if (rej[4].equalsIgnoreCase("Iris-setosa"))
                                        c1.add(nodoAIntroducir);
                                    else
                                        c2.add(nodoAIntroducir);
                                    
                                        
                                }
				
				System.out.println(clase1.getClase().size());
                                System.out.println(clase2.getClase().size());

				br.close();
				
			} 
			catch (FileNotFoundException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			} 
			catch (IOException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			}
		}
		
		return clase1;
		
	}
	
	public static String[][] loadEjemplos(){
		
		ArrayList<String[]> rej = new ArrayList<String[]>();
		String[][] tabla = null;
		
		JFileChooser chooser = new JFileChooser(path);
		chooser.setDialogTitle("Abrir Archivo de Ejemplos");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Archivos .txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			String name = chooser.getSelectedFile().getPath();
                        
                        path=name;
					
			FileInputStream file;
			
			try {

				file = new FileInputStream(name);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						file));

				String line = br.readLine();
				
				while(line != null){
					
					rej.add(line.split(","));
					
					line = br.readLine();
				
				}

				br.close();
				
			} 
			catch (FileNotFoundException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			} 
			catch (IOException e) {

				JOptionPane.showMessageDialog(null, "Aqui falta un mensaje");

			}
			
			
			rej.remove(rej.size() - 1);
			
			tabla = new String[rej.size()][rej.get(0).length];
			
			for (int i = 0; i < rej.size(); i++) {
				for (int j = 0; j < rej.get(i).length; j++){

					tabla[i][j] = rej.get(i)[j];
					
				}
			}	
		}
		
		return tabla;
		
	}
}
