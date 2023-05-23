package gherkin

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import com.keyword.key as key
import com.kms.katalon.core.util.KeywordUtil


import internal.GlobalVariable

public class Login {
	@Given("I open application alfagift")
	def openApplication() {
		Mobile.startApplication(GlobalVariable.AppIdAndroid,false)
	}

	@Given("go to login page")
	def goToPageLogin() {
		Mobile.tap(findTestObject('Object Repository/btn.MenuLogin'),10)// Tap Login page
	}

	@When("I input credential (.*) and (.*)")
	def inputCredential(String phone, String password) {
		WebUI.setText(findTestObject('Object Repository/Login/txt.phone'), phone )
		WebUI.setText(findTestObject('Object Repository/Login/txt.password'), password)
		
	}

	@And("click button login")
	def clickBtnLogin() {
		WebUI.click(findTestObject('Object Repository/Login/btn.Submit'))
	}

	@Then("I navigated home alfagift Verify (.*) Account")
	def validateFormPageAfterLogin(String expectedUser) {
		String getTextCurrent = WebUI.getText(findTestObject('Object Repository/Home/getUserAccount'))
		key.compareValue(getTextCurrent, expectedUser)
		
		//Check Coachmark
		if(Mobile.verifyElementVisible(findTestObject('Object Repository/Home/PopUpPromo'), 2)){
			Mobile.tap(findTestObject('Object Repository/Home/ClosePopUpPromo'),5)
		}
	}
	
	@And("logout account")
	def logout() {
		WebUI.click(findTestObject('Object Repository/Login/btn.MenuProfile'))
		WebUI.click(findTestObject('Object Repository/Login/btn.logout'))	
	}
	

	@Then("I see failed login with invalid phone number device has logged in")
	def seeFailedCredential() {
		Mobile.verifyElementVisible(findTestObject('Object Repository/Login/Toast.LoginFailedDifferentDevice'), 0)
		String getReason  = WebUI.getText(findTestObject('Object Repository/Login/lbl.detailToast'))
		Mobile.tap(findTestObject('Object Repository/Login/Toast.btnOKFailed'),2)
		println ("Toast Pop up is Show " +getReason) 

	}
	
	
	@Then("I see error login invalid phone")
	def errorLogin() {
		if(Mobile.verifyElementVisible(findTestObject('Object Repository/Login/err.phonenumberinvalid'), 0)) {
			KeywordUtil.markPassed("Passed, error is show expected")
		}
		else {
			KeywordUtil.markFailed("Failed, error handling not show")
		}
	}
	
	@Then("I see error login password invalid")
	def errorLoginPassword() {
		if(Mobile.verifyElementVisible(findTestObject('Object Repository/Login/err.InvalidPassword'), 0)) {
			KeywordUtil.markPassed("Passed, error is show expected")
		}
		else {
			KeywordUtil.markFailed("Failed, error handling not show")
		}

	}
	
}

