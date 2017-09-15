package com.marklogic.junit.roxy;

import com.jayway.restassured.RestAssured;
import com.marklogic.roxy.test.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileReader;
import java.util.Collection;
import java.util.Properties;

/**
 * Sample JUnit test that uses RestAssured to invoke the Roxy Unit Test Framework. The key to this test is the use of
 * the Parameterized test runner. This allows every Roxy test to be treated as a single JUnit test.
 */
@RunWith(Parameterized.class)
public class RoxyTest extends Assert {

	private TestModule testModule;
	private static RoxyTestXmlParser roxyTestXmlParser = new JaxpRoxyTestXmlParser();

	/**
	 * This constructor is required so that the JUnit Parameterized test runner can pass each TestModule in to each
	 * run of this test class.
	 *
	 * @param testModule
	 */
	public RoxyTest(TestModule testModule) {
		this.testModule = testModule;
	}

	/**
	 * This sets up the parameters for our test by getting a list of all Roxy test modules.
	 *
	 * @return
	 * @throws Exception
	 */
	@Parameterized.Parameters(name = "{index}: {0}")
	public static Collection<TestModule> getRoxyTestModules() throws Exception {
		initializeRestAssured();

		String xml = RestAssured.get("/test/?func=list").body().asString();
		return roxyTestXmlParser.parseTestList(xml);
	}

	/**
	 * This is a simple way of initializing RestAssured based on the gradle.properties file in this project. In practice,
	 * it's more likely to use something like Spring's JUnit support for reading in configuration.
	 *
	 * @throws Exception
	 */
	public static void initializeRestAssured() throws Exception {
		Properties props = new Properties();
		props.load(new FileReader("gradle.properties"));

		RestAssured.baseURI = "http://" + props.getProperty("mlHost");
		RestAssured.port = Integer.parseInt(props.getProperty("mlRoxyTestPort"));
		RestAssured.authentication = RestAssured.digest(props.getProperty("mlUsername"), props.getProperty("mlPassword"));
	}

	/**
	 * This method is invoked by the Parameterized test runner, once for each TestModule returned by the method above.
	 */
	@Test
	public void runRoxyTests() {
		String path = String.format("/test?func=run&suite=%s&tests=%s", testModule.getSuite(), testModule.getTest());
		String xml = RestAssured.get(path).body().asString();
		TestSuiteResult result = roxyTestXmlParser.parseTestSuiteResult(xml);
		for (TestResult testResult : result.getTestResults()) {
			String failureXml = testResult.getFailureXml();
			if (failureXml != null) {
				fail(String.format("Test %s in suite %s failed, cause: %s", testResult.getName(), testModule.getSuite(), failureXml));
			}
		}
	}
}
