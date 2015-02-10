package pr3.lloyd;

import java.util.ArrayList;
import java.util.HashMap;

import pr3.Clase;
import pr3.Op;

public class Lloyd {
	
	private static final double TOLERANCIA = Math.pow(10, -10);
	private static final double MAX_ITER = 10;
	private static final double RAZON = 0.1;
	
	public static void exec(int iter, HashMap<String, Clase> clases,
			double[][] centros){
		
		for (String clase : clases.keySet()) {
			
			System.out.println(clases.get(clase));
			System.out.println();
			
		}
		
		for (String clase : clases.keySet()) {
			
			ArrayList<double[]> valores = 
					clases.get(clase).getValores();
			
			// Centro a actualizar.
			int centAct = 0;
			
			// Guardamos los centros acuales para compararlos luego
			double[][] centrosAnt = centros;
			
			for (double[] xi : valores) {

				// Distancia euclidea del punto xi a los centros
				double d1 = Op.distEuclidea(xi, centros[0]);
				double d2 = Op.distEuclidea(xi, centros[1]);
				
				// Vemos que centro actualizamos
				if (d1 <= d2) centAct = 0;
				else centAct = 1;
				
				// Actualizamos el centro
				for (int i = 0; i < xi.length; i++){
				
					centros[centAct][i] =
							centros[centAct][i] + 
							RAZON * (xi[i] - centros[centAct][i]);
					
				}
				
				// Calculamos la distancia euclidea entre los centros antes 
				// y despues para saber si hay que seguir
				double dc1 = Op.distEuclidea(centrosAnt[0], centros[0]);
				double dc2 = Op.distEuclidea(centrosAnt[1], centros[1]);
				
				if (iter+1 <= MAX_ITER && 
						(dc1 > TOLERANCIA || dc2 > TOLERANCIA))
					exec(iter + 1, clases, centros);
				
			}
		}
	}

}
