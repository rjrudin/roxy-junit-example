package com.marklogic.roxy.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Captures the data for each test suite result as returned by invoking /test/?func=run&suite=(name)&tests=(paths).
 */
public class TestSuiteResult {

	private String name;
	private int total;
	private int passed;
	private int failed;
	private double time;
	private List<TestResult> testResults = new ArrayList<>();

	public TestSuiteResult(String name, int total, int passed, int failed, double time) {
		this.name = name;
		this.total = total;
		this.passed = passed;
		this.failed = failed;
		this.time = time;
	}

	public void addTestResult(TestResult testResult) {
		if (testResults == null) {
			testResults = new ArrayList<>();
		}
		testResults.add(testResult);
	}

	public String getName() {
		return name;
	}

	public int getTotal() {
		return total;
	}

	public int getPassed() {
		return passed;
	}

	public int getFailed() {
		return failed;
	}

	public double getTime() {
		return time;
	}

	public List<TestResult> getTestResults() {
		return testResults;
	}
}
