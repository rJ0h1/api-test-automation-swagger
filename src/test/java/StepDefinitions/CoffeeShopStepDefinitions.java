package StepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;

public class CoffeeShopStepDefinitions {
    @Given("user wants to update {string}")
    public void userWantsToUpdate(String endpoint) {
        if (endpoint.contains("employee")) {
            RestAssured.basePath = "/Employee";
        }
        else {
            Assertions.fail("Test Error: endpoint " + endpoint + " doesn't exist");
        }
    }
}
