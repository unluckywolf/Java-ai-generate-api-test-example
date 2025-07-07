package com.example.compute;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ComputeController {
    
    private final CalculatorService calculatorService;
    
    public ComputeController() {
        this.calculatorService = new CalculatorService();
    }
    
    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> add(
            @RequestParam(required = false) String a,
            @RequestParam(required = false) String b) {
        
        try {
            // Validate required parameters
            if (a == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Missing parameter: a");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            
            if (b == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Missing parameter: b");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            
            // Validate input types
            double numberA;
            double numberB;
            
            try {
                numberA = Double.parseDouble(a);
                numberB = Double.parseDouble(b);
            } catch (NumberFormatException e) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid input type");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            
            // Calculate result
            double result = calculatorService.compute(numberA, numberB);
            
            // Return success response
            Map<String, Object> response = new HashMap<>();
            response.put("result", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}