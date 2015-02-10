package pr3;

import java.util.ArrayList;

public class Clase {
	
	private String nombre;
	private ArrayList<double[]> valores;
	
	public Clase(String nombre){
		
		this.nombre = nombre;
		valores = new ArrayList<double[]>();
		
	}
	
	public void addValor(double[] valor){
		
		valores.add(valor);
		
	}
	
	public String getNombre(){ return nombre; }
	
	public ArrayList<double[]> getValores(){ return valores; }
	
	public boolean equals(Object o){
		
		if (!o.getClass().equals(Clase.class)) return false;
		
		else return nombre.equals(((Clase)o).nombre);
				
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("Clase [" + nombre + "]\n{");
		
		for (double[] strings : valores) {
			sb.append("\n\t");
			for (double string : strings) {
			
				sb.append(string + ", ");
				
			}		
		}
		
		sb.append("\n}");
		
		return sb.toString();
		
	}

}
