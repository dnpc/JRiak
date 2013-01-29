package com.riak.app.entity;

import com.riak.app.util.SaleContractTypeEnum;

import java.util.List;

public class SaleContract implements BaseEntity{

    private Long saleContractId;
    private SaleContractTypeEnum saleContractType;
    private List<Sale> saleList;



    public SaleContract (){}

    public SaleContractTypeEnum getSaleContractType() {
        return saleContractType;
    }

    public void setSaleContractType(SaleContractTypeEnum saleContractType) {
        this.saleContractType = saleContractType;
    }

    public Long getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(Long saleContractId) {
        this.saleContractId = saleContractId;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleContract that = (SaleContract) o;

        if (getSaleContractId() != null ? !getSaleContractId().equals(that.getSaleContractId()) : that.getSaleContractId() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getSaleContractId() != null ? getSaleContractId().hashCode() : 0;
    }

    public static SaleContract getNewInstance (Long saleContractId){
        SaleContract saleContract = new SaleContract();
        saleContract.setSaleContractId(saleContractId);
        saleContract.setSaleContractType(SaleContractTypeEnum.A);
        return saleContract;
    }
}
