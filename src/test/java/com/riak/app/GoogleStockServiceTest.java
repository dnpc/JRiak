package com.riak.app;

import com.basho.riak.client.RiakException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.riak.app.entity.GoogleStock;
import com.riak.app.service.GoogleStockService;
import com.riak.app.service.RiakService;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.*;

public class GoogleStockServiceTest {

    private static RiakService riakService;
    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new GuiceModule());
        riakService = injector.getInstance(GoogleStockService.class);
    }

    @Test
    public void testValueAsString() throws RiakException {
        String value = riakService.getValueAsString("2007-04-03");
        System.out.println("Value " + value);
    }

    @Test
    public void testValueAsPOJO() throws RiakException {
        GoogleStock googleStock = (GoogleStock)riakService.getValueAsPOJO("2007-04-03");
        Assert.assertNotNull(googleStock);
        System.out.println(googleStock.toString());
    }

    @Test
    public void testUpdate() throws RiakException{
        String key = "2007-04-03";
        GoogleStock googleStock = (GoogleStock)riakService.getValueAsPOJO(key);
        assertNotNull(googleStock);
        Long volume = googleStock.getVolume().longValue();
        ++volume;
        googleStock.setVolume(volume);
        riakService.update(key, googleStock);
        googleStock = (GoogleStock)riakService.getValueAsPOJO(key);
        assertEquals(volume.longValue(), googleStock.getVolume().longValue());
        System.out.println("Google Stock After update : "+googleStock);
    }

    @Test
    public void testInsert() throws RiakException{
       String key = "2013-01-01";
       GoogleStock googleStock = getNewInstance(key);
       riakService.update(key, googleStock);
       googleStock = (GoogleStock)riakService.getValueAsPOJO(key);
       assertNotNull(googleStock);
    }

    @Test
    public void testDelete() throws RiakException{
        String key = "2013-02-02";
        GoogleStock googleStock = getNewInstance(key);
        riakService.update(key, googleStock);
        googleStock = (GoogleStock)riakService.getValueAsPOJO(key);
        assertNotNull(googleStock);
        riakService.delete(key);
        googleStock = (GoogleStock)riakService.getValueAsPOJO(key);
        assertNull(googleStock);
    }

    private GoogleStock getNewInstance (String key){
        GoogleStock googleStock = new GoogleStock();
        googleStock.setDate(key);
        googleStock.setOpen(new BigDecimal(464.05));
        googleStock.setHigh(new BigDecimal(474.25));
        googleStock.setLow(new BigDecimal(464.00));
        googleStock.setClose(new BigDecimal(472.60));
        googleStock.setVolume(6000000L);
        googleStock.setAdjClose(new BigDecimal(472.60));
        return googleStock;
    }

}
