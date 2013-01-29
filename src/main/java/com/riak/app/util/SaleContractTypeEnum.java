package com.riak.app.util;

public enum SaleContractTypeEnum {

    A("A", "ONE"),
    B("B", "TWO");

    private String saleContractType;
    private String saleContractTypeDesc;

    private SaleContractTypeEnum (String saleContractType,
                                  String saleContractTypeDesc){
        this.saleContractType = saleContractType;
        this.saleContractTypeDesc = saleContractTypeDesc;
    }


    public String getSaleContractType() {
        return saleContractType;
    }

    public String getSaleContractTypeDesc() {
        return saleContractTypeDesc;
    }
}
