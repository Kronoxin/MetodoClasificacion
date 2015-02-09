/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosclasificacion.bayes;

import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class Clase 
{
    private ArrayList<Nodo> clase;
    private String nombre;
    
    public Clase(String nombre, ArrayList<Nodo> clase)
    {
        this.nombre = nombre;
        this.clase = clase;
    }

    public ArrayList<Nodo> getClase() {
        return clase;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
