/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosclasificacion.bayes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruben
 */
public class Matriz {
   
    public int n;      //dimensión
    private double[][] x;
    public Matriz(int n) {
        this.n=n;
        x=new double[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                x[i][j]=0.0;
            }
        }
    }
    public Matriz(double[][] x) {
        this.x=x;
        n=x.length;
   }
//otras funciones miembro
    
    public static Matriz traspuesta(Matriz a){
        int n=a.n;
        Matriz resultado=new Matriz(a.n);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                resultado.x[i][j]=a.x[j][i];
            }
        }
        return resultado;
    }
    
     public static Matriz producto(Matriz a, Matriz b){
        Matriz resultado=new Matriz(a.n);
        for(int i=0; i<a.n; i++){
            for(int j=0; j<a.n; j++){
                for(int k=0; k<a.n; k++){
                    resultado.x[i][j]+=a.x[i][k]*b.x[k][j];
                }
            }
        }
        return resultado;
    }
     
     public static Matriz suma(Matriz a, Matriz b){
        Matriz resultado=new Matriz(a.n);
        for(int i=0; i<a.n; i++){
            for(int j=0; j<a.n; j++){
                resultado.x[i][j]=a.x[i][j]+b.x[i][j];
            }
        }
        return resultado;
    }
     
     public static Matriz resta(Matriz a, Matriz b){
        Matriz resultado=new Matriz(a.n);
        for(int i=0; i<a.n; i++){
            for(int j=0; j<a.n; j++){
                resultado.x[i][j]=a.x[i][j]-b.x[i][j];
            }
        }
        return resultado;
    }

    public int getN() {
        return n;
    }

    public double[][] getX() {
        return x;
    }
    
    static Matriz inversa(Matriz d) 
    {
        int n=d.n;  //dimensión de la matriz
        Matriz a ;
        Matriz c = null;
        try {
            a = (Matriz)d.clone();
        
        Matriz b=new Matriz(n);   //matriz de los términos independientes
        c=new Matriz(n);   //matriz de las incógnitas
//matriz unidad
        for(int i=0; i<n; i++){
            b.x[i][i]=1.0;
        }
//transformación de la matriz y de los términos independientes
        for(int k=0; k<n-1; k++){
            for(int i=k+1; i<n; i++){
//términos independientes
                for(int s=0; s<n; s++){
                    b.x[i][s]-=a.x[i][k]*b.x[k][s]/a.x[k][k];
                }
//elementos de la matriz
                for(int j=k+1; j<n; j++){
                    a.x[i][j]-=a.x[i][k]*a.x[k][j]/a.x[k][k];
                }
            }
        }
//cálculo de las incógnitas, elementos de la matriz inversa
        for(int s=0; s<n; s++){
            c.x[n-1][s]=b.x[n-1][s]/a.x[n-1][n-1];
            for(int i=n-2; i>=0; i--){
                c.x[i][s]=b.x[i][s]/a.x[i][i];
                for(int k=n-1; k>i; k--){
                    c.x[i][s]-=a.x[i][k]*c.x[k][s]/a.x[i][i];
                }
            }
        }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Matriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
     
}
    

