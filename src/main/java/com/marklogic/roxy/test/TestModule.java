package com.marklogic.roxy.test;

/**
 * Captures the data for each test module as returned by invoking /test/?func=list.
 */
public class TestModule {

	private String test;
	private String suite;

	public TestModule(String test, String suite) {
		this.test = test;
		this.suite = suite;
	}

	@Override
	public String toString() {
		return String.format("[suite: %s, test: %s]", suite, test);
	}

	public String getTest() {
		return test;
	}

	public String getSuite() {
		return suite;
	}
}
