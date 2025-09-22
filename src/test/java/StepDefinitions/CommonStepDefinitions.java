package StepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;

public class CommonStepDefinitions {
    @Given("user is calling the {string}")
    public void userIsCallingThe(String api) {
        if (api.contains("coffee-shop-api")) {
            RestAssured.baseURI = "https://webservice.toscacloud.com/api/training";
        }
        else {
            Assertions.fail("Test error: api not found");
        }
    }
}
