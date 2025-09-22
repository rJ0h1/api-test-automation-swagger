package StepDefinitions;

import com.example.coffeeshop.model.Employee;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class CoffeeShopStepDefinitions {

    Response response;
    Employee[] employees;

    //Generate this from https://webservice.toscacloud.com/training/ and update it here
    //Only valid for 24 hours
    static String key = "UPDATE_KEY_HERE";

    public CoffeeShopStepDefinitions() {
    }

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

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        System.out.println("/nstarting execution of tests");
        response = given()
                .log().all()
                .when()
                .get(url);
    }

    @And("user receives a non-empty list of employees")
    public void userReceivesANonEmptyListOfEmployees() {

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode());
        employees = response.as(Employee[].class);

    }

    @When("user requests to modify the name of the first employee")
    public void userRequestsToModifyTheNameOfTheFirstEmployee() {
        employees[0].setFirstName("Jack");
        employees[0].setLastName("Jones");

        String url = RestAssured.baseURI + RestAssured.basePath + "/" + key;

        response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(employees[0])
                .put(url);
    }

    @Then("request is successful")
    public void requestIsSuccessful() {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode());
    }


    @And("the list of existing employees is updated")
    public void theListOfExistingEmployeesIsUpdated() {
        userRequestsListOfExistingEmployees();
        Assertions.assertEquals("Jack", response.as(Employee[].class)[0].getFirstName());
        Assertions.assertEquals("Jones", response.as(Employee[].class)[0].getLastName());
    }
}
