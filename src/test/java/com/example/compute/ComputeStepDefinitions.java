package com.example.compute;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputeStepDefinitions {

    private CalculatorService calculatorService;
    private double result;

    @Given("the calculator service is running")
    public void theCalculatorServiceIsRunning() {
        calculatorService = new CalculatorService();
        assertTrue(calculatorService.isRunning());
    }

    @When("I send a request to add the numbers:")
    public void iSendARequestToAddTheNumbers(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = rows.get(0);
        
        double numberA = Double.parseDouble(row.get("numberA"));
        double numberB = Double.parseDouble(row.get("numberB"));
        
        result = calculatorService.compute(numberA, numberB);
    }

    @Then("the response should be:")
    public void theResponseShouldBe(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = rows.get(0);
        
        double expectedResult = Double.parseDouble(row.get("result"));
        assertEquals(expectedResult, result, 0.01);
    }
}