**Run your Roxy tests as JUnit tests** 

This project shows how to integrate your Roxy unit tests into a standard JUnit test suite, which can contain any number
of other non-Roxy tests. Every Roxy test is reported as a separate JUnit test.

The project includes reusable code in src/main/java for capturing the XML outputted by the Roxy Unit Test Framework
that defines all the available test suites, and then the results for running one or more test suites. The code under
src/test is an example of how to make use of this reusable code with JUnit used for running the test, and RestAssured
for invoking the Roxy Unit Test Framework.  

While ml-gradle is used to deploy the application that has the Roxy test code in it, ml-gradle (nor Gradle) is needed
to run the tests. The test can be run via "gradle test", or via any IDE that is integrated with JUnit. 

To try this out locally, just do the following:

1. Verify that the username/password and ports in gradle.properties will work for your ML server
1. Run "gradle mlDeploy" to deploy the test application
1. Run "gradle test" to run the tests. Every Roxy test will be reported back as a separate JUnit test. At least one of
the Roxy tests - text-input.xqy - should fail on purpose so you can see that both successes and failures are reported. 

You can then inspect the test report at ./build/reports/tests/test/index.html . 

You can also run the tests in src/test/java in your favorite IDE too. A non-Roxy test is included so you can see that
Roxy tests can be included in the same suite.
