/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr3.bayes;

import java.util.ArrayList;
import pr3.Clase;


/**
 *
 * @author Ruben
 */
public class Bayes 
{
    double[][] c1 = new double[4][4];
    double[][] c2 = new double[4][4];
    Nodo m1;
    Nodo m2;
    
    public void CalcularCentros(Clase clase1, Clase clase2)
    {
        double x = 0;
        double y = 0;
        double z = 0;
        double t = 0;
        
        
        
        for(double[] a : clase1.getValores())
        {
            x += a[0];
            y += a[1];
            z += a[2];
            t += a[3];
        }
        
        m1 = new Nodo(x/clase1.getValores().size(),y/clase1.getValores().size(),z/clase1.getValores().size(),t/clase1.getValores().size());
        
        x = 0; y = 0; z = 0; t = 0;
        
        for(double[] a : clase2.getValores())
        {
            x += a[0];
            y += a[1];
            z += a[2];
            t += a[3];
        }
        
        m2 = new Nodo(x/clase2.getValores().size(),y/clase2.getValores().size(),z/clase2.getValores().size(),t/clase2.getValores().size());
        
        Matriz matrizCentro1 = new Matriz(new double[1][4]);
        
        for (double[] a : clase1.getValores())
        {
            double[][] b = new double[1][a.length];
            for (int i = 0; i < a.length; i++)
                b[0][i] = a[i];
            
            Matriz matrizResta = Matriz.resta(new Matriz(b), new Matriz(m1.devolverMatriz()));
            Matriz matrizTranspuesta = Matriz.traspuesta(matrizResta);
            Matriz matrizProducto = Matriz.producto(matrizResta, matrizTranspuesta);
            
            matrizCentro1 = Matriz.suma(matrizCentro1, matrizProducto);
        }
        
        
        
        c1[0][0] = matrizCentro1.getX()[0][0] * (1/ (clase1.getValores().size() -1));
        c1[0][1] = matrizCentro1.getX()[0][1] * (1/ (clase1.getValores().size() -1));
        c1[0][2] = matrizCentro1.getX()[0][2] * (1/ (clase1.getValores().size() -1));
        c1[0][3] = matrizCentro1.getX()[0][3] * (1/ (clase1.getValores().size() -1));
        
        
        Matriz matrizCentro2 = new Matriz(4);
        
        for (double[] a : clase2.getValores())
        {
            double[][] b = new double[1][a.length];
            for (int i = 0; i < a.length; i++)
                b[0][i] = a[i];
            
            matrizCentro2 = Matriz.suma(matrizCentro2, Matriz.producto(Matriz.traspuesta(Matriz.resta(new Matriz(b), new Matriz(m1.devolverMatriz()))), Matriz.resta(new Matriz(b), new Matriz(m1.devolverMatriz()))));
        }
        
        
        
        c2[0][0] = matrizCentro2.getX()[0][0] * (1/ (clase2.getValores().size() -1));
        c2[0][1] = matrizCentro2.getX()[0][1] * (1/ (clase2.getValores().size() -1));
        c2[0][2] = matrizCentro2.getX()[0][2] * (1/ (clase2.getValores().size() -1));
        c2[0][3] = matrizCentro2.getX()[0][3] * (1/ (clase2.getValores().size() -1));
        
    }
    
    public String cercaDeclase(Nodo a)
    {
        String claseMasCercana = null;
        
        Matriz distanciaAC1 = new Matriz(1);
        Matriz distanciaAC2 = new Matriz(1);
                        // RESTA
        distanciaAC1 = Matriz.producto(  Matriz.producto(   Matriz.resta(   new Matriz(a.devolverMatriz())  ,   new Matriz(m1.devolverMatriz())   ) , Matriz.traspuesta(Matriz.resta(new Matriz(a.devolverMatriz()), new Matriz(m1.devolverMatriz()) ))),  Matriz.inversa(new Matriz(c1)));
        
        distanciaAC2 = Matriz.producto(  Matriz.producto(   Matriz.resta(   new Matriz(a.devolverMatriz())  ,   new Matriz(m2.devolverMatriz())   ) , Matriz.traspuesta(Matriz.resta(new Matriz(a.devolverMatriz()), new Matriz(m2.devolverMatriz()) ))),  Matriz.inversa(new Matriz(c2)));
        
        if (distanciaAC1.getX()[0][0] < distanciaAC2.getX()[0][0])
            claseMasCercana = "c1";
        
        else
            claseMasCercana = "c2";
        
        return claseMasCercana;
    }
}
