package com.eduit.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BusquedaClienteFracuenteMs implements BusquedaClienteFrecuente{

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    @Autowired
    RestTemplate restTemplate;
    
    @Override
    public int descuento(String idCliente) {
        String uri = "http://localhost:8080/clifrec/" + idCliente;
        //Object resp = restTemplate.getForObject(uri, Object.class);
        //return 0;
        List<HashMap<String, Object>> resp = (List<HashMap<String, Object>>)
                restTemplate.getForObject(uri, Object.class);
        if(resp.isEmpty()){
            System.out.println("No existe ese cliente");
            return 0;            
        }
        return Integer.parseInt(resp.get(0).get("tipoDescuento").toString());
    }
    

}
