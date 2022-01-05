/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduit.contoller;

import com.eduit.components.ContenedorJPA;
import com.eduit.model.ClienteFrecuente;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafaeli
 */
@RestController
@RequestMapping("/clifrec")
@CrossOrigin(origins = "*")
public class ClienteFrecuenteController {

    @Autowired
    private ContenedorJPA contenedorJPA;

    private int[] crearArray(int n) {
        int[] arrEnteros = new int[n];

        for (int x = 0; x < n; x++) {
            arrEnteros[x] = x;
        }

        return arrEnteros;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ClienteFrecuente> getTodos() {

        List<ClienteFrecuente> retValue = new ArrayList<>();

        try {
            EntityManager em = contenedorJPA.getEntityManager();
            String strSQL = "from ClienteFrecuente c";
            TypedQuery<ClienteFrecuente> cfQuery = em.createQuery(strSQL, ClienteFrecuente.class);
            retValue = cfQuery.getResultList();
            for(ClienteFrecuente cf : retValue){
                System.out.println("Se obtuvo el siguiente cliente frecuente: ");
                System.out.println(cf.toString());
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return retValue;
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<ClienteFrecuente> getClienteFrecuente(@PathVariable String id) {       
        return  getTodos()
                .stream()
                .filter(z -> z.idCliente.equals(id))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/inventados", method = RequestMethod.GET)
    public List<ClienteFrecuente> getInventados() {

        ArrayList<ClienteFrecuente> retValue = new ArrayList<>();

        for (int z : crearArray(10)) {
            retValue.add(new ClienteFrecuente(
                    UUID.randomUUID().toString(),
                    Faker.instance().address().firstName(),
                    Faker.instance().address().lastName(),
                    (z % 2) + 1
            ));
        }

        return retValue;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarCliente(@RequestBody ClienteFrecuente cf) {
        System.out.println("Se agregó el cliente: ");
        System.out.println("Id: " + cf.idCliente);
        System.out.println("Nombre: " + cf.nombre);
        System.out.println("Apellido: " + cf.apellido);
        System.out.println("Descuento: " + (cf.tipoDescuento == 1 ? "10%" : "20%"));

        EntityTransaction tx = null;
        try {
            EntityManager em = contenedorJPA.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(cf);
            tx.commit();

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            tx.rollback();
        }

    }
}
