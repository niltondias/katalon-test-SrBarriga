import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;
import testlink.api.java.client.TestLinkAPIClient;

class ResultToTestLinkAfterTestCase {
	
	/***
	 * Campos que serão atualizados no Testlink
	 */
	static final String TESTLINK_KEY = "d79484602fd73d22a82a826300d02ee4";
	static final String TESTLINK_URL = "http://localhost/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
	static final String TESTLINK_PROJECT_NAME = "MeuProjeto";
	static final String TESTLINK_PLAN_NAME = "MeuPlano";
	static final String TESTLINK_CASE_NAME = "LoginNaPagina";
	static final String TESTLINK_BUILD_NAME = "release1";
	static final String TESTLINK_TEST_OBS = "Teste executado na ferramenta Katalon e resultado enviado para o Testlink."
	String notes = null;

	public static void updateResults(String testCaseName, String exception, String results) throws TestLinkAPIException {
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL);
		testLink.repor
		testLink.reportTestCaseResult(TESTLINK_PROJECT_NAME,
				TESTLINK_PLAN_NAME,
				testCaseName,
				TESTLINK_BUILD_NAME,
				exception,
				results);
		
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		String result = (testCaseContext.getTestCaseStatus() == 'FAILED') ? TestLinkAPIResults.TEST_FAILED : TestLinkAPIResults.TEST_PASSED;
		updateResults(TESTLINK_CASE_NAME, notes, result);
		
		WebUI.closeBrowser();
	}
	
	
	
}