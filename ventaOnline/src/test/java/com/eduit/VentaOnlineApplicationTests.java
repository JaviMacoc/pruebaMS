package com.eduit;

import com.eduit.service.BusquedaClienteFrecuente;
import com.eduit.service.CalculoDescuento;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class VentaOnlineApplicationTests {

    @Test
    void contextLoads() {
        Double importeFinal = CalculoDescuento
                .build()
                .setearIdCliente("11")
                .setearImportee(50000d)
                .setearBusquedaClienteFrecuente(z -> 1)
                .calcular();

        Assert.isTrue(importeFinal == 45000d, "El descuento no esta bien calculado");
    }

    
    void testServicio() {

        BusquedaClienteFrecuente busquedaClienteMock = Mockito
                .mock(BusquedaClienteFrecuente.class);

        CalculoDescuento
                .build()
                .setearIdCliente("12")
                .setearImportee(10000d)
                .setearBusquedaClienteFrecuente(busquedaClienteMock)
                .calcular();

        Mockito.verify(busquedaClienteMock,
                Mockito.times(1))
                .descuento("144");
    }
}
