/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosclasificacion.bayes;


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
        
        
        
        for(Nodo a : clase1.getClase())
        {
            x += a.getX();
            y += a.getY();
            z += a.getZ();
            t += a.getT();
        }
        
        m1 = new Nodo(x/clase1.getClase().size(),y/clase1.getClase().size(),z/clase1.getClase().size(),t/clase1.getClase().size());
        
        x = 0; y = 0; z = 0; t = 0;
        
        for(Nodo a : clase2.getClase())
        {
            x += a.getX();
            y += a.getY();
            z += a.getZ();
            t += a.getT();
        }
        
        m2 = new Nodo(x/clase2.getClase().size(),y/clase2.getClase().size(),z/clase2.getClase().size(),t/clase2.getClase().size());
        
        Matriz matrizCentro1 = new Matriz(4);
        
        for (Nodo a : clase1.getClase())
        {
            matrizCentro1 = Matriz.suma(matrizCentro1, Matriz.producto(Matriz.traspuesta(Matriz.resta(new Matriz(a.devolverMatriz()), new Matriz(m1.devolverMatriz()))), Matriz.resta(new Matriz(a.devolverMatriz()), new Matriz(m1.devolverMatriz()))));
        }
        
        
        
        c1[0][0] = matrizCentro1.getX()[0][0] * (1/ (clase1.getClase().size() -1));
        c1[0][1] = matrizCentro1.getX()[0][1] * (1/ (clase1.getClase().size() -1));
        c1[0][2] = matrizCentro1.getX()[0][2] * (1/ (clase1.getClase().size() -1));
        c1[0][3] = matrizCentro1.getX()[0][3] * (1/ (clase1.getClase().size() -1));
        
        
        Matriz matrizCentro2 = new Matriz(4);
        
        for (Nodo a : clase2.getClase())
        {
            matrizCentro2 = Matriz.suma(matrizCentro2, Matriz.producto(Matriz.traspuesta(Matriz.resta(new Matriz(a.devolverMatriz()), new Matriz(m1.devolverMatriz()))), Matriz.resta(new Matriz(a.devolverMatriz()), new Matriz(m1.devolverMatriz()))));
        }
        
        
        
        c2[0][0] = matrizCentro2.getX()[0][0] * (1/ (clase2.getClase().size() -1));
        c2[0][1] = matrizCentro2.getX()[0][1] * (1/ (clase2.getClase().size() -1));
        c2[0][2] = matrizCentro2.getX()[0][2] * (1/ (clase2.getClase().size() -1));
        c2[0][3] = matrizCentro2.getX()[0][3] * (1/ (clase2.getClase().size() -1));
        
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
