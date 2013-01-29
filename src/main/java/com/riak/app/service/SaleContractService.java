package com.riak.app.service;


import com.google.inject.Inject;
import com.riak.app.dao.SaleContractDAO;
import com.riak.app.entity.SaleContract;

public class SaleContractService extends BaseRiakService<SaleContract>{

    @Inject
    public SaleContractService(SaleContractDAO saleContractDAO) {
        super(saleContractDAO);
    }
}
