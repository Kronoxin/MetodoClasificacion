package pr3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import pr3.Clase;
import pr3.Op;
import pr3.Str;
import pr3.bayes.Bayes;
import pr3.lloyd.Lloyd;


public class Ventana {
	
	private JFrame ventana;
	
	private double[][] centros = { {4.6, 3.0, 4.0, 0.0},
			  					   {6.8, 3.4, 4.6, 0.7} };
	
	private HashMap<String, Clase> clases;
	
	private JButton botonKMeans;
	private JButton botonBayes;
	private JButton botonLloyd;
	private JButton botonSOM;
	
	private DefaultTableModel model;
	
	private JButton botonComprobar;
	
	public Ventana(){
		
		ventana = new JFrame(Str.NOMBRE);
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setMinimumSize(new Dimension(700, 400));
		ventana.setMaximumSize(new Dimension(700, 400));
		ventana.setLocation(0, 0);
		ventana.setVisible(true);
		
		clases = new HashMap<String, Clase>();
		
		JPanel panelPrin = new JPanel(new BorderLayout());
		
		// PANEL SUPERIOR------------------------------------------------------
		
		JButton botonCargar = new JButton(Str.BTN_CARGAR);
		botonCargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[][] valS = FileManager.load();
				
				if (valS == null) return;
				
				for (int i = 0; i < valS.length; i++){
					
					Clase clase = null;
					String nombreC = valS[i][valS[i].length - 1]; 
					
					if (clases.containsKey(nombreC)) 
						clase = clases.get(nombreC);
					
					else {
						
						clase = new Clase(nombreC);
						clases.put(nombreC, clase);
						
					}
					
					double[] valores = new double[valS[i].length - 1];
					
					for (int j = 0; j < valS[i].length - 1; j++){
					
						valores[j] = Double.parseDouble(valS[i][j]);
						
					}
					
					clase.addValor(valores);
					
				} //for
				
				if(!clases.isEmpty())
					enableAll();
				
			}
		});
		
		//botonKMeans = new JButton(Str.BTN_KMEANS);
		//botonKMeans.setEnabled(false);
		
		botonBayes = new JButton(Str.BTN_BAYES);
		botonBayes.setEnabled(false);
                botonBayes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

                                Bayes a = new Bayes();
                                a.CalcularCentros(clases.get("Iris-versicolor"), clases.get("Iris-setosa"));
				
				model.fireTableDataChanged();
				
			}
		});
		
		botonLloyd = new JButton(Str.BTN_LLOYD);
		botonLloyd.setEnabled(false);
		botonLloyd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Lloyd.exec(0, clases, centros);
				model.fireTableDataChanged();
				
			}
		});
		
		botonSOM = new JButton(Str.BTN_SOM);
		botonSOM.setEnabled(false);
		
		JButton botonResetCentro = new JButton(Str.BTN_RESETC);
		botonResetCentro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				resetCentros();
				
			}
		});
		
		JPanel panelMetodos = new JPanel(new GridLayout(4, 1));
		//panelMetodos.add(botonKMeans);
		panelMetodos.add(botonBayes);
		panelMetodos.add(botonLloyd);
		//panelMetodos.add(botonSOM);
		
		JPanel panelArribaCentro = new JPanel(new GridLayout(1, 1));
		panelArribaCentro.setBorder(new TitledBorder(Str.TXT_METODOS));
		panelArribaCentro.add(panelMetodos);
		
		JPanel panelArriba = new JPanel(new GridLayout(1, 3));
		panelArriba.setBorder(new TitledBorder(""));
		
		JPanel panelBotonCargar = new JPanel(new GridLayout(5, 1));
                botonCargar.setBackground(Color.GREEN);
                botonResetCentro.setForeground(Color.red);
		panelBotonCargar.add(new JLabel());
		panelBotonCargar.add(botonCargar);
                panelBotonCargar.add(botonResetCentro);
		panelBotonCargar.add(new JLabel());
		
		
		
		panelArriba.add(panelBotonCargar);
		panelArriba.add(panelArribaCentro);
		
		
		panelPrin.add(panelArriba, BorderLayout.NORTH);
		
		// TABLA---------------------------------------------------------------
		
		model = new DefaultTableModel(){

			private static final long serialVersionUID = 1L;
			
			private String columnNames[] = {"C1", "C2"};
			
			public int getRowCount(){
				
				return centros[0].length;
				
			}
			
			public int getColumnCount() {
				
				return centros.length;
				
			}
			
			public String getValueAt(int row, int column){
				
				if (row >= 0)
					return String.format("%.2f", centros[column][row]);
				
				else 
					return "Empty";
			
			}
			
			public String getColumnName(int column){
				
				return columnNames [column];
				
			}
			
			public boolean isCellEditable(int row, int column) {
				
				return false;
					
			}
				
		};
		
		JTable tabla = new JTable(model);
		JScrollPane panelTabla = new JScrollPane(tabla);
		panelTabla.setPreferredSize(
				new Dimension(ventana.getWidth(), ventana.getHeight()/3));
		
		panelPrin.add(panelTabla, BorderLayout.CENTER);
		
		// COMPROBAR-----------------------------------------------------------
		
		JPanel panelComprobar = new JPanel(new GridLayout(1, 5));
		panelComprobar.setBorder(new TitledBorder(Str.TXT_COMPROBAR));
		
		final JTextField t1 = new JTextField();
		final JTextField t2 = new JTextField();
		final JTextField t3 = new JTextField();
		final JTextField t4 = new JTextField();
		
		final JLabel result = new JLabel("", JLabel.CENTER);
		
		botonComprobar = new JButton(Str.BTN_COMPROBAR);
		botonComprobar.setEnabled(false);
		botonComprobar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				double[] a = new double[4];
				
				try{
				
					a[0] = Double.parseDouble(t1.getText());
					a[1] = Double.parseDouble(t2.getText());
					a[2] = Double.parseDouble(t3.getText());
					a[3] = Double.parseDouble(t4.getText());
	
					if (Op.distEuclidea(a, centros[0]) < Op.distEuclidea(a, centros[1])){
						result.setText((String)clases.keySet().toArray()[0]);
                                                  Icon icono_ok;
       
        
                                        icono_ok = new ImageIcon(getClass().getResource("/img/iconOK.png"));


                                      JOptionPane pane = new JOptionPane();

                                      pane.showMessageDialog(null, "","Verificado",JOptionPane.INFORMATION_MESSAGE, icono_ok);
                                      pane.setBackground(Color.GREEN);
                                      pane.setForeground(Color.GREEN);
                                        
                                        }
                                        else{
						result.setText((String)clases.keySet().toArray()[1]);
                                                
                                                 Icon icono_no;
                                        icono_no = new ImageIcon(getClass().getResource("/img/iconNO.png"));
                                        JOptionPane pane = new JOptionPane();

                                      pane.showMessageDialog(null, "","Denegado",JOptionPane.ERROR_MESSAGE, icono_no);
                                      pane.setBackground(Color.red);
                                      pane.setForeground(Color.red);
                                        }
				}
				catch(NumberFormatException ex){
				
					JOptionPane.showMessageDialog(ventana, 
							"Introduzca todos los numeros.");
					
				}
			}
		});
		
		panelComprobar.add(t1);
		panelComprobar.add(t2);
		panelComprobar.add(t3);
		panelComprobar.add(t4);
		panelComprobar.add(botonComprobar);
		panelComprobar.add(result);		
		
		panelPrin.add(panelComprobar, BorderLayout.SOUTH);
		
		ventana.add(panelPrin);
		
		ventana.pack();
		
	}
	
	private void enableAll(){
		
		//botonKMeans.setEnabled(true);
		botonBayes.setEnabled(true);
		botonLloyd.setEnabled(true);
		//botonSOM.setEnabled(true);
		botonComprobar.setEnabled(true);
		
	}
	
	private void resetCentros(){
		
		centros = new double[][]{ {4.6, 3.0, 4.0, 0.0},
								  {6.8, 3.4, 4.6, 0.7} };
		
		model.fireTableDataChanged();
		
	}	
}
