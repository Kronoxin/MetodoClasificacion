package pr3;

public class Op {
	
	public static double distEuclidea(double[] a, double[] b){
		
		double d = 0;
		
		// Sumatorio desde i = 1 hasta n de (pi - qi)^2
		for (int i = 0; i < a.length; i++){
			
			d += Math.pow(a[i] - b[i], 2); 
			
		}
		
		// Raiz de eso
		d = Math.sqrt(d);
		
		return d;
		
	}

}
