/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduit.model;

public class ClienteFrecuente {
        
    public String idCliente;
    public String nombre;
    public String apellido;    
    public Integer tipoDescuento;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(String idCliente, String nombre, String apellido, Integer tipoDescuento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDescuento = tipoDescuento;
    }
    
    
    
}
