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
    private ArrayList<Nodo> grupoNodosC1;
    private ArrayList<Nodo> grupoNodosC2;
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
    
    public ArrayList<Nodo> copiarArrayList(ArrayList<Nodo> array)
    {
        ArrayList<Nodo> arrayDevolver = new ArrayList<>();
        
        for (Nodo a : array)
            arrayDevolver.add(new Nodo(a));
        
        return arrayDevolver;
    }
    
    public void calcularCentros()
    {
        ArrayList<Nodo> grupo1 = new ArrayList<>();
        ArrayList<Nodo> grupo1Anterior = new ArrayList<>();
        ArrayList<Nodo> grupo2 = new ArrayList<>();
        ArrayList<Nodo> grupo2Anterior = new ArrayList<>();
        
        boolean seguirIterando = true;
        
        if(this.listaNodos.size() >= 2)
        {
            c1 = this.listaNodos.get(0);
            c2 = this.listaNodos.get(1);
        }
        
        double matrizDistanciasActual[][] = new double[listaNodos.size()][listaNodos.size()];
        
        while(seguirIterando)
        {
            for (int i = 0; i < listaNodos.size();i++)
            {
                matrizDistanciasActual[0][i] = distanciaEuclidea(c1,this.listaNodos.get(i));
                matrizDistanciasActual[1][i] = distanciaEuclidea(c2,this.listaNodos.get(i));

                if (matrizDistanciasActual[0][i] < matrizDistanciasActual[1][i])
                    grupo1.add(listaNodos.get(i));
                else
                    grupo2.add(listaNodos.get(i));  
            }
            
            if (grupo1.containsAll(grupo1Anterior) && grupo2.containsAll(grupo2Anterior))
                seguirIterando = false;
            
            else
            {
                grupo1Anterior = copiarArrayList(grupo1);
                grupo2Anterior = copiarArrayList(grupo2);
                
                // Calcular centroides
                int sumX = 0;
                int sumY = 0;
                for(int i=0; i<grupo1.size();i++)
                {
                    sumX += grupo1.get(i).getX();
                    sumY += grupo1.get(i).getY();
                }
                c1.setX(sumX/grupo1.size());
                c1.setY(sumY/grupo1.size());

                sumX = 0;
                sumY = 0;

                for(int i=0; i < grupo2.size();i++)
                {
                    sumX += grupo2.get(i).getX();
                    sumY += grupo2.get(i).getY();
                }

                c2.setX(sumX/grupo2.size());
                c2.setY(sumY/grupo2.size());
                
                this.grupoNodosC1 = copiarArrayList(grupo1);
                this.grupoNodosC2 = copiarArrayList(grupo2);
            }
       }
        
    }

    public ArrayList<Nodo> getListaNodos() 
    {
        return listaNodos;
    }

    public ArrayList<Nodo> getGrupoNodosC1() 
    {
        return grupoNodosC1;
    }

    public ArrayList<Nodo> getGrupoNodosC2()
    {
        return grupoNodosC2;
    }
    
    
    
    public static void main(String args[])
    {
        ArrayList<Nodo> lista = new ArrayList<>();
        
        lista.add(new Nodo("A",1,1));
        lista.add(new Nodo("B",2,1));
        lista.add(new Nodo("C",4,3));
        lista.add(new Nodo("D",5,4));
        
        Lloyd l = new Lloyd(lista);
        
        l.calcularCentros();
        
        for (Nodo e : l.getGrupoNodosC1())
            System.out.println(e.getNombre() + " pertenece al centroide 1");
        
        for (Nodo e : l.getGrupoNodosC2())
            System.out.println(e.getNombre() + " pertenece al centroide 2");
    }
}
