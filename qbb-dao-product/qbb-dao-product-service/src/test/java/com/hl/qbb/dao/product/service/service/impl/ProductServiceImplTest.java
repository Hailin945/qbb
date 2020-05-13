package com.hl.qbb.dao.product.service.service.impl;

import com.hl.qbb.dao.product.api.dataobject.ProductDO;
import com.hl.qbb.dao.product.service.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * {@RunWith(SpringRunner.class)}注解的作用：让测试在Spring容器环境下执行。如测试类中无此注解，将导致service,dao等自动注入失败。
 *
 * @author Hailin
 * @date 2020/2/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Resource
    private ProductService productService;

    @Test
    public void update() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 30, 30,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        for (int i = 1; i < 30; i++) {
            final int index = i;
            threadPoolExecutor.execute(() -> {
                System.out.println("第" + index + "个线程:" + Thread.currentThread().getName());
                ProductDO productDO = productService.get(1L);
                productDO.setNum(productDO.getNum() - 1);
                System.out.println("=============" + Thread.currentThread().getName() + ":" + productDO.getNum());
                boolean b = productService.update(productDO);
                Assert.assertTrue(b);
            });
        }
        // int i = 0;
        // while (i < 100) {
        //     threadPoolExecutor.execute(() -> {
        //
        //     });
        //     i++;
        // }
    }
}
