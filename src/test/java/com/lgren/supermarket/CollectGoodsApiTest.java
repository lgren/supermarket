package com.lgren.supermarket;

import com.lgren.api.moudle.CollectGoodsApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectGoodsApiTest {
    @Autowired
    private CollectGoodsApi collectGoodsApi;
    @Test
    public void getCollectGoodsByCollectId() {
        collectGoodsApi.getCollectGoodsByCollectId(1L);
    }
    @Test
    public void getCollectGoodsByGoodsId() {
        collectGoodsApi.getCollectGoodsByGoodsId(1L);
    }
}