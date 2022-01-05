/*
 * 
 * 
 * 
 */
package com.eduit.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Javier Macoc
 */
@Service
public class CalculoDescuento {

    private String idCliente;
    private double importe;
    private BusquedaClienteFrecuente busquedaClienteFrecuente;

    public static CalculoDescuento build() {
        return new CalculoDescuento();
    }    
    
    public CalculoDescuento setearIdCliente(String idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public CalculoDescuento setearImportee(double importe) {
        this.importe = importe;
        return this;
    }

    public CalculoDescuento setearBusquedaClienteFrecuente(BusquedaClienteFrecuente busquedaClienteFrecuente) {
        this.busquedaClienteFrecuente = busquedaClienteFrecuente;
        return this;
    }

    public double calcular() {
        Integer tipoDescuento = busquedaClienteFrecuente.descuento(idCliente);
        if (tipoDescuento == 0) {
            return importe;
        }
        if (tipoDescuento == 1) {
            return importe * 0.9;
        }
        if (tipoDescuento == 2) {
            return importe * 0.8;
        }
        throw new RuntimeException("No deberia llegar a este punto");
    }

}
