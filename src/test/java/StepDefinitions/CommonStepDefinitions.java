package StepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import services.TestContext;

public class CommonStepDefinitions {
    private TestContext testContext;

    public CommonStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("user is calling the {string}")
    public void userIsCallingThe(String api) {
        try {
            RestAssured.baseURI = testContext.getContext(api).getScheme() +
                    testContext.getContext(api).getBaseUri();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
