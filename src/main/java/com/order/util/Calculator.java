package com.order.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import com.order.service.Order;

public class Calculator {

	public static BigDecimal rounding(BigDecimal value) {
		return value.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * receives a collection of orders. For each order, iterates on the order lines 
	 * and calculate the total price which is the item's (price * quantity) + taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without taxes for 
	 * this order
	 */
	public void calculate(Map<String, Order> o) {

	    BigDecimal salesTaxPercent = new BigDecimal(".10");
	    BigDecimal importedSalesTaxPercent = new BigDecimal(".15");

		BigDecimal grandtotal = BigDecimal.ZERO;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");

			Order r = entry.getValue();

			BigDecimal totalTax = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;

			// Iterate through the items in the order
			for (int i = 0; i < r.size(); i++) {

				// Calculate the taxes
			    BigDecimal tax;


				final BigDecimal price = rounding(new BigDecimal(Float.toString(r.get(i).getItem().getPrice())))
						.multiply(new BigDecimal(Integer.toString(r.get(i).getQuantity())));
				if (r.get(i).getItem().getDescription().toLowerCase().contains("imported")) {
					// Extra 5% tax on imported items
					tax = rounding(importedSalesTaxPercent.multiply(price));
				} else {
					tax = rounding(salesTaxPercent.multiply(price));
				}
				

				// Calculate the total price
				BigDecimal totalprice = price.add(tax);
				
				// Print out the item's total price
				System.out.println(r.get(i).getQuantity()+" "+r.get(i).getItem().getDescription() + ": " + totalprice.doubleValue());

				// Keep a running total
				totalTax = totalTax.add(tax);
				total = total.add(price);
			}

			// Print out the total taxes
			System.out.println("Sales Tax: " + totalTax.doubleValue());

			//NOTE: commented this to not include tax in the 'total'
			//total = total.add(totalTax);

			// Print out the total amount
			System.out.println("Total: " + total.doubleValue());
			grandtotal = grandtotal.add(total);
		}

		System.out.println("Sum of orders: " + grandtotal.doubleValue());
	}
}