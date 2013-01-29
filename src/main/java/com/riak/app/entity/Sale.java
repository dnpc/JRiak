package com.riak.app.entity;

import java.math.BigDecimal;

public class Sale implements BaseEntity{

    private String saleId;
    private BigDecimal saleAmount;
    private Long units;

    public Sale (){}

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        if (saleId != null ? !saleId.equals(sale.saleId) : sale.saleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return saleId != null ? saleId.hashCode() : 0;
    }

    public static Sale getNewInstance(String saleId){
        Sale sale = new Sale();
        sale.setSaleId(saleId);
        sale.setSaleAmount(new BigDecimal(1000.00));
        sale.setUnits(100L);
        return sale;
    }
}
