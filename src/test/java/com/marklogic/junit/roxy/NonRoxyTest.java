package com.marklogic.junit.roxy;

import org.junit.Assert;
import org.junit.Test;

/**
 * This is only included to show how Roxy tests can be combined into the same suite as non-Roxy tests.
 */
public class NonRoxyTest extends Assert {

	@Test
	public void test() {
		assertEquals("1", "1");
	}
}

