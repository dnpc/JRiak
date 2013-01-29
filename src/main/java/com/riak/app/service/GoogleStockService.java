package com.riak.app.service;


import com.google.inject.Inject;
import com.riak.app.dao.GoogleStockDAO;
import com.riak.app.entity.GoogleStock;

public class GoogleStockService extends BaseRiakService<GoogleStock>{

    @Inject
    public GoogleStockService(GoogleStockDAO googleStockDAO) {
        super(googleStockDAO);
    }
}
