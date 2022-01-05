/*
 * 
 * 
 * 
 */

package com.eduit.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Javier Macoc
 */
public class Remito {
    
    @Getter @Setter
    private String id;
    @Getter @Setter
    private double montoNominal;    
    @Getter @Setter
    private String descuento;
    @Getter @Setter
    private double montoTotal;

    public Remito() {
    }

    public Remito(String id, double montoNominal, double montoTotal) {
        this.id = id;
        this.montoNominal = montoNominal; 
        descuento = Math.round(1-(montoTotal/montoNominal))*10000.0/100.0 + "%";
        this.montoTotal = montoTotal;        
    }
    
}
