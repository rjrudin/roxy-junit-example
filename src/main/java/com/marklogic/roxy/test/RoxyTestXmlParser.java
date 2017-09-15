package com.marklogic.roxy.test;

import java.util.Collection;

public interface RoxyTestXmlParser {

	Collection<TestModule> parseTestList(String xml);

	TestSuiteResult parseTestSuiteResult(String xml);
}
