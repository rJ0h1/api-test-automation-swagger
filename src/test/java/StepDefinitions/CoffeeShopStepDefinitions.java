package StepDefinitions;

import com.example.coffeeshop.model.Employee;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class CoffeeShopStepDefinitions {

    //Generate this from https://webservice.toscacloud.com/training/ and update it here
    //Only valid for 24 hours
    static String key = "UPDATE_KEY_HERE";
    
    private Response response;

    @Given("user wants to update {string}")
    public void userWantsToUpdate(String endpoint) {
        if (endpoint.contains("employee")) {
            RestAssured.basePath = "/Employee";
        } else {
            Assertions.fail("Test Error: endpoint " + endpoint + " doesn't exist");
        }
    }

    @And("user requests list of existing employees")
    public void userRequestsListOfExistingEmployees() {

        String url = RestAssured.baseURI + RestAssured.basePath + "/" + key;

        response = given()
                .get(url);
    }

    @And("user receives a non-empty list of employees")
    public void userReceivesANonEmptyListOfEmployees() {

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode());
        Employee[] employees = response.as(Employee[].class);

    }
}
