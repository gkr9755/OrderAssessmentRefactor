package com.order;

import java.util.LinkedHashMap;
import java.util.Map;

import com.order.model.Item;
import com.order.model.OrderLine;
import com.order.service.Order;
import com.order.util.Calculator;

/* ****************************************************************************************
 
Please remove all bugs from the code below to get the following output:

<pre>

*******Order 1*******
1 book: 13.74
1 music CD: 16.49
1 chocolate bar: 0.94
Sales Tax: 2.84
Total: 28.33
*******Order 2*******
1 imported box of chocolate: 11.5
1 imported bottle of perfume: 54.62
Sales Tax: 8.62
Total: 57.5
*******Order 3*******
1 Imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 10.73
1 box of imported chocolates: 12.94
Sales Tax: 8.77
Total: 67.98
Sum of orders: 153.81
 
</pre>
 
******************************************************************************************** */

public class Foo {

	public static void main(String[] args) throws Exception {

		Map<String, Order> o = new LinkedHashMap<String, Order>();

		Order order1 = new Order();

		order1.add(new OrderLine(new Item("book", 12.49f), 1));
		order1.add(new OrderLine(new Item("music CD", 14.99f), 1));
		order1.add(new OrderLine(new Item("chocolate bar", 0.85f), 1));

		o.put("Order 1", order1);

		Order order2 = new Order();
		order2.add(new OrderLine(new Item("imported box of chocolate", 10f), 1));
		order2.add(new OrderLine(new Item("imported bottle of perfume", 47.50f), 1));

		o.put("Order 2", order2);

		Order order3 = new Order();
		order3.add(new OrderLine(new Item("Imported bottle of perfume", 27.99f), 1));
		order3.add(new OrderLine(new Item("bottle of perfume", 18.99f), 1));
		order3.add(new OrderLine(new Item("packet of headache pills", 9.75f), 1));
		order3.add(new OrderLine(new Item("box of imported chocolates", 11.25f), 1));

		o.put("Order 3", order3);

		new Calculator().calculate(o);

	}
}