package com.test.jenkins;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import com.study.jenkins.BankAccount;

public class TestBaknAccount {

	@Test
	public void testDebit() {
		BankAccount acc = new BankAccount(10);
		double amt = acc.debit(5);
		Assert.assertEquals(5.0, amt);
	}
}