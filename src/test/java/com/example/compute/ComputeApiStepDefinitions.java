package com.example.compute;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(classes = ComputeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.profiles.active=test"})
public class ComputeApiStepDefinitions {

    @LocalServerPort
    private int port;

    private Response response;

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        RestAssured.baseURI = "http://localhost:" + port;
        response = given()
                .when()
                .get(endpoint);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("the response content-type should be {string}")
    public void theResponseContentTypeShouldBe(String expectedContentType) {
        response.then().contentType(expectedContentType);
    }

    @And("the response body should contain:")
    public void theResponseBodyShouldContain(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String key = row.get("key");
            String value = row.get("value");
            
            if (isNumeric(value)) {
                response.then().body(key, equalTo(Double.parseDouble(value)));
            } else {
                response.then().body(key, equalTo(value));
            }
        }
    }

    @And("the response body should contain error message {string}")
    public void theResponseBodyShouldContainErrorMessage(String expectedErrorMessage) {
        response.then().body("error", equalTo(expectedErrorMessage));
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}