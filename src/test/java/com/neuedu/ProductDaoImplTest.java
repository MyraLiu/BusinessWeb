package com.neuedu;

import com.neuedu.dao.IProductDao;
import com.neuedu.dao.impl.ProductDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductDaoImplTest {

    IProductDao pdo =null;

    @Before
    public void beforetest(){
      pdo=   new ProductDaoImpl();
    }

    @Test
    public void testFindById(){

        System.out.println(pdo.findProductById(10000));
    }


    @After
    public  void afterTest(){
        pdo=null;
    }

}
