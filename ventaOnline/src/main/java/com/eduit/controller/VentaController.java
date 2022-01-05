/*
 * 
 * 
 * 
 */

package com.eduit.controller;

import com.eduit.model.ClienteFrecuente;
import com.eduit.model.Remito;
import com.eduit.service.BusquedaClienteFracuenteMs;
import com.eduit.service.CalculoDescuento;
import java.util.UUID;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Javier Macoc
 */
@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {    
   
    @Autowired
    BusquedaClienteFracuenteMs busquedaClienteFracuenteMs;
    
    @RequestMapping(path = "remito/{id}", method = RequestMethod.GET) 
    public Remito vender(@PathVariable String id){        
//        
        Double desc = CalculoDescuento
                .build()
                .setearIdCliente(id)
                .setearImportee(1000d)
                .setearBusquedaClienteFrecuente(z -> 
                    busquedaClienteFracuenteMs.descuento(id))
                .calcular();
        return new Remito(            
            UUID.randomUUID().toString(),
            1000d,                           
            desc            
        );

    }
     @RequestMapping(path = "verificar/{id}", method = RequestMethod.GET)
    public int verificar(@PathVariable String id){
        return busquedaClienteFracuenteMs.descuento(id);
    }
}
