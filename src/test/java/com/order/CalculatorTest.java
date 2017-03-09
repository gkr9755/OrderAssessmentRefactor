package com.order;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.order.model.Item;
import com.order.model.OrderLine;
import com.order.service.Order;
import com.order.util.Calculator;

public class CalculatorTest {

	private Calculator calculator;

	@Before
	public void setUp() {
		calculator = new Calculator();
	}

	/**
	 * Test rounding method
	 */
	@Test
	public void testRounding() {
		BigDecimal bd1 = new BigDecimal(25.249);
		BigDecimal bd2 = new BigDecimal(13.468);
		Assert.assertEquals(new BigDecimal(25.25).doubleValue(), Calculator.rounding(bd1).doubleValue(), 0.001);
		Assert.assertEquals(new BigDecimal(13.47).doubleValue(), Calculator.rounding(bd2).doubleValue(), 0.001);
	}

	/**
	 * Test calculate method, no assertions as method return type is void
	 * @throws Exception 
	 */
	@Test
	public void testCalculate() throws Exception {

		Map<String, Order> o = new LinkedHashMap<String, Order>();

		Order order1 = new Order();
		order1.add(new OrderLine(new Item("book", 12.49f), 1));
		o.put("Order 1", order1);

		Order order2 = new Order();
		order2.add(new OrderLine(new Item("imported box of chocolate", 10f), 1));
		o.put("Order 2", order2);

		Order order3 = new Order();
		order3.add(new OrderLine(new Item("Imported bottle of perfume", 27.99f), 1));
		o.put("Order 3", order3);

		calculator.calculate(o);
	}
}