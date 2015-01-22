/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosclasificacion.lloyd;

import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class Lloyd 
{
    private ArrayList<Nodo> listaNodos;
    private Nodo c1;
    private Nodo c2;
    
    public Lloyd(ArrayList<Nodo> listaNodos)
    {
        this.listaNodos = listaNodos;
    }
    
    
    public double distanciaEuclidea(Nodo a, Nodo b)
    {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }
    
    public void calcularCentros()
    {
        if(this.listaNodos.size() >= 2)
        {
            c1 = this.listaNodos.get(0);
            c2 = this.listaNodos.get(1);
        }
        
        double matrizDistancias[][] = new double[listaNodos.size()][listaNodos.size()];
        
        for (int i = 0; i < listaNodos.size();i++)
        {
            matrizDistancias[0][i] = distanciaEuclidea(c1,this.listaNodos.get(i));
            matrizDistancias[1][i] = distanciaEuclidea(c2,this.listaNodos.get(i));
        }
    }
}
