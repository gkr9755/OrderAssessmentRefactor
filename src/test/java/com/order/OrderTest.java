package com.order;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.order.model.Item;
import com.order.model.OrderLine;
import com.order.service.Order;

public class OrderTest {

	private Order order;

	@Before
	public void init() {
		order = new Order();
	}

	/**
	 * Tests add and size methods
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAdd() throws Exception {
		// Add OrderLines to the Order
		order.add(new OrderLine(new Item("Imported bottle of perfume", 127.99f), 5));
		order.add(new OrderLine(new Item("bottle of perfume", 128.99f), 5));

		// Check size
		Assert.assertTrue(order.size() > 0);
		Assert.assertEquals(2, order.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddUsingNull() throws Exception {
		order.add(null);
	}

	/**
	 * Tests get method
	 */
	@Test
	public void testGet() throws Exception {
		Item item1 = new Item("Imported bottle of perfume", 127.99f);
		Item item2 = new Item("bottle of perfume", 128.99f);
		
		OrderLine orderLine1 = new OrderLine(item1, 5);
		OrderLine orderLine2 = new OrderLine(item2, 5);

		// Add OrderLines to the Order
		order.add(orderLine1);
		order.add(orderLine2);

		// verify order lines
		Assert.assertNotNull(order.get(0));
		Assert.assertEquals(orderLine1, order.get(0));
		Assert.assertEquals(orderLine2, order.get(1));

		// verify items
		Assert.assertNotNull(order.get(0).getItem());
		Assert.assertNotNull(order.get(1).getItem());
		Assert.assertEquals(item1, order.get(0).getItem());
		Assert.assertEquals(item2, order.get(1).getItem());
		
		// verify quantity
		Assert.assertEquals(5, order.get(0).getQuantity());
		Assert.assertEquals(5, order.get(1).getQuantity());

		// verify price
		Assert.assertEquals(127.99f, order.get(0).getItem().getPrice(), 0.001);
		Assert.assertEquals(128.99f, order.get(1).getItem().getPrice(), 0.001);

		// verify names
		Assert.assertEquals("Imported bottle of perfume", order.get(0).getItem().getDescription());
		Assert.assertEquals("bottle of perfume", order.get(1).getItem().getDescription());
	}
}