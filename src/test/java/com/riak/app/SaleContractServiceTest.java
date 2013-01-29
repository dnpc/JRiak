package com.riak.app;


import com.basho.riak.client.RiakException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.riak.app.dao.SaleContractDAO;
import com.riak.app.entity.GoogleStock;
import com.riak.app.entity.Sale;
import com.riak.app.entity.SaleContract;
import com.riak.app.service.RiakService;
import com.riak.app.service.SaleContractService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class SaleContractServiceTest {

    private static RiakService riakService;
    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new GuiceModule());
        riakService = injector.getInstance(SaleContractService.class);
    }

    @Test
    public void testCreateBucket(){
        Exception ex = null;
        try{
            riakService.createBucket(SaleContractDAO.BUCKET_NAME);
        }catch(RiakException re){
            ex = re;
        }
        assertNull(ex);
    }

    @Test
    public void testInsert () throws RiakException{
        SaleContract saleContract = SaleContract.getNewInstance(1000L);
        String key = saleContract.getSaleContractId().toString();
        riakService.update(key, saleContract);
        saleContract = (SaleContract)riakService.getValueAsPOJO(key);
        assertNotNull(saleContract);
        assertEquals(key, saleContract.getSaleContractId().toString());
    }

    @Test
    public void testInsertSale() throws RiakException{
        String key = "1000_001";
        String key1 = "1000_002";
        Sale sale = Sale.getNewInstance(key);
        SaleContract saleContract = (SaleContract)riakService.getValueAsPOJO("1000");
        List<Sale> saleList = new ArrayList<Sale>();
        saleList.add(sale);
        sale = Sale.getNewInstance(key1);
        saleList.add(sale);
        saleContract.setSaleList(saleList);
        riakService.update(saleContract.getSaleContractId().toString(), saleContract);
        assertEquals(2, saleContract.getSaleList().size());
    }

    @Test
    public void testUpdateOfSale() throws RiakException{
        String key = "1000";
        SaleContract saleContract = (SaleContract)riakService.getValueAsPOJO(key);
        Sale sale = saleContract.getSaleList().get(0);
        sale.setUnits(200L);
        riakService.update(key, saleContract);
        saleContract = (SaleContract)riakService.getValueAsPOJO(key);
        assertEquals(new Long(200), saleContract.getSaleList().get(0).getUnits());
    }

}
