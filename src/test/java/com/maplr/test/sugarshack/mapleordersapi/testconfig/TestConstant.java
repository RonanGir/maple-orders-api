package com.maplr.test.sugarshack.mapleordersapi.testconfig;

import com.maplr.test.sugarshack.mapleordersapi.product.MapleSyrupTypeEnum;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductEntity;

public class TestConstant {

    public static ProductEntity getSampleProduct() {
        ProductEntity p = new ProductEntity();
        p.setName("sample product");
        p.setDescription("sample description");
        p.setStock(1);
        p.setPrice(2d);
        p.setMaxQty(1);
        p.setType(MapleSyrupTypeEnum.CLEAR);
        return p;
    }
}
