
/*
 * Shinya Aoi
 *  04/26/2018
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class DateMoneyBillJUnit {

	/**
	 * Test methods for Money constructors and getters.
	 */
	@Test
	public void ConstructMoneyTest() {
		Money money1 = new Money(10);
		Money money2 = new Money(money1);
		Money money3 = new Money(20,99);
		assertEquals(10, money1.getDollars());
		assertEquals(0, money1.getCents());
		assertEquals("$10.00",money1.toString());
		assertEquals(money1,money2);
		assertEquals("$20.99", money3.toString());
		try{
			Money money4 = new Money(-10,-20);
			assertFalse(true);
		}catch (IllegalArgumentException ignored){}
	}

	/**
	 * Testing for setMoney
	 */
	@Test
	public void SetMoneyTest()
	{
		Money money1 = new Money(0,0);
		money1.setMoney(30,50);
        assertEquals(30, money1.getDollars());      
        assertEquals(50, money1.getCents());
        try{
        	Money money2 = new Money(-2,-49);
        	assertFalse(true);
		}catch (IllegalArgumentException e){}
	}

	/**
	 * Testing for copy constructor.
	 */
	@Test
	public void ConstructMoneyCopyTest() {
		Money money1 = new Money(0,0);
		money1.setMoney(10,40);		
		
        Money money2 = new Money(money1);
        
        assertEquals(10, money1.getDollars());       
        assertEquals(40, money2.getCents());
	}

	/**
	 * Testing for add methods.
	 */
	@Test
	public void AddMoneyTest(){
		Money money = new Money(30,40);
		Money money1 = new Money(20,70);
		money.add(money1);
		assertEquals("$51.10", money.toString());
		money1.add(20);
		assertEquals("$40.70", money1.toString());
		money1.add(20,5);
		assertEquals("$60.75", money1.toString());
		try{
			money.add(-30);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			money.add(-3,-6);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
	}

	/**
	 * Testing for print/toString.
	 */
	@Test
	public void PrintMoneyTest()
	{
		Money money1 = new Money(10);

        money1.setMoney(30,50);
        assertEquals("$30.50", money1.toString());

	}

	/**
	 * Testing for equals method.
	 */
	@Test
	public void EqualsMoneyTest(){
		Money money1 = new Money(20,30);
		Money money2 = new Money(money1);
		Money money3 = new Money(49,0);
		boolean result = money1.equals(money2);
		assertEquals(result, true);
		boolean result2 = money2.equals(money3);
		assertEquals(result2, false);
	}

	/**
	 * Test methods for Date class
	 */
	@Test
	public void ConstructDateTest(){
		Date date1 = new Date(12,31,2024);
		assertEquals(12, date1.getMonth());
		assertEquals(31,date1.getDay());
		assertEquals(2024, date1.getYear());
		try{
			Date dateError = new Date(13,4,2020);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			Date dateError2 = new Date(5,32,2020);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			Date dateError3 = new Date(2,4,2025);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
	}

	/**
	 * Testing for setDate - setMonth, setDay, and setYear
	 */
	@Test
	public void SetDateTest(){
		Date date1 = new Date(3,5,2014);
		date1.setYear(2024);
		date1.setDay(30);
		date1.setMonth(5);
		assertEquals(2024, date1.getYear());
		assertEquals(30, date1.getDay());
		assertEquals(5, date1.getMonth());
		try{
			date1.setMonth(0);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			date1.setMonth(13);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			date1.setDay(0);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			date1.setDay(32);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			date1.setYear(2013);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
		try{
			date1.setYear(2025);
			assertFalse(true);
		}catch (IllegalArgumentException e){}
	}

	/**
	 * Testing for isAfter method.
	 */
	@Test
	public void IsAfterDateTest(){
		Date date = new Date(3,5,2018);
		Date date2 = new Date(3,6,2018);
		Date date3 = new Date(4,5,2018);
		Date date4 = new Date(3,5,2019);
		date2.isAfter(date);
		date3.isAfter(date);
		date4.isAfter(date);
		boolean result = date.isAfter(date4);
		assertEquals(result,false);
	}

	/**
	 * Testing for toString method.
	 */
	@Test
	public void ToStringDateTest(){
		Date date1 = new Date(3,5,2018);
		assertEquals("03/05/2018", date1.toString());
	}

	/**
	 * Testing for equals method for Date class.
	 */
	@Test
	public void EqualsDateTest(){
		Date date1 = new Date(3,9,2017);
		Date date2 = new Date(3,9,2017);
		Date date3 = new Date(4,9,2017);
		assertEquals(true, date1.equals(date2));
		assertEquals(false, date1.equals(date3));
	}


	/**
	 * Testing Bill's constructors and getters.
	 */
	@Test
	public void ConstructBillTest(){
		Money money = new Money(30,99);
		Date dueDate = new Date(2, 5,2018);
		Bill bill1 = new Bill(money, dueDate, "Cell Phone");
		assertEquals(new Money(30,99), bill1.getAmount());
		assertEquals(new Date(2,5,2018), bill1.getDueDate());
		assertEquals("Cell Phone", bill1.getOriginator());
		Bill bill2 = new Bill(bill1);
		assertEquals(new Money(30,99), bill2.getAmount());
		assertEquals(new Date(2,5,2018), bill2.getDueDate());
		assertEquals("Cell Phone", bill2.getOriginator());
	}

	/**
	 * Testing Bill's isPaid() and setters.
	 */
	@Test
	public void BillIsPaidTest(){
		Money money = new Money(30,99);
		Date dueDate = new Date(2, 5,2018);
		Bill bill1 = new Bill(money, dueDate, "Cell Phone");
		Bill bill2 = new Bill(money, dueDate, "Shinya");
		assertEquals(false,bill1.isPaid());
		Date paidDate = new Date(2,4,2018);
		Date paidDate2 = new Date(2,6,2018);
		bill1.setPaid(paidDate);
		assertEquals(true, bill1.isPaid());
		Money money1 = new Money(12,0);
		assertEquals(false,bill1.setAmount(money1));
		assertEquals(false,bill2.setPaid(paidDate2));
	}
	/**
	 * Testing Bill's toString()
	 */
	@Test
	public void BillToStringTest(){
		Money money = new Money(30,99);
		Date dueDate = new Date(2, 5,2018);
		Bill bill1 = new Bill(money, dueDate, "Cell Phone");
		assertEquals("Amount is:$30.99, Due date:02/05/2018, To: Cell Phone, You have not paid yet!",bill1.toString());
		Date paidDate = new Date(2,4,2018);
		bill1.setPaid(paidDate);
		assertEquals("Amount is:$30.99, Due date:02/05/2018, To: Cell Phone, Paid date:02/04/2018",bill1.toString());

	}

	/**
	 * Testing for equals for the bill class.
	 */
	@Test
	public void BillEqualsTest(){
		Money money = new Money(30,99);
		Date dueDate = new Date(2, 5,2018);
		Bill bill1 = new Bill(money, dueDate, "Cell Phone");
		Money money1 = new Money(30,99);
		Date dueDate1 = new Date(2, 5,2018);
		Bill bill2 = new Bill(money1, dueDate1, "Cell Phone");
		Bill bill3 = new Bill(money, dueDate, "Shinya");
		assertEquals(true,bill1.equals(bill2));
		assertEquals(false, bill1.equals(bill3));
		bill3.setOriginator("Cell Phone");
		assertEquals(true,bill1.equals(bill3));
	}
}
