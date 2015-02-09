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
public class Nodo 
{
    private double x;
    private double y;
    private double z;
    private double t;

    public Nodo()
    {
    
    }
    public Nodo(double x, double y, double z, double t) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
    }
    
    public Nodo(Nodo a) {
        this.x = a.getX();
        this.y = a.getY();
        this.z = a.getZ();
        this.t = a.getT();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }
    
    public double[][] devolverMatriz()
    {
        double[][] a = new double[1][4];
        a[0][0] = x;
        a[0][1] = y;
        a[0][2] = z;
        a[0][3] = t;
        
        return a;
    }
}
