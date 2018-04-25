package com.lgren.supermarket;

import com.lgren.api.moudle.CartApi;
import com.lgren.api.moudle.CartGoodsApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupermarketApplicationTests {
    @Autowired
    private CartGoodsApi cartGoodsApi;
    @Autowired
    private CartApi cartApi;

	@Test
	public void contextLoads() {
	}
	@Test
	public void getAllCartGoods() {
        cartGoodsApi.getAllCartGoods();
	}

	@Test
	public void getAllCart() {
        cartApi.getAllCart();
	}

	@Test
	public void getCartGoodsByCartId() {
        cartGoodsApi.getCartGoodsByCartId(1L);
	}

	@Test
	public void getCartByUserId() {
        cartApi.getCartByUserId(1L);
	}

}
