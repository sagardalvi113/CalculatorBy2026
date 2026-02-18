package com.expense.manager.controller;

import com.expense.manager.model.Expense;
import com.expense.manager.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense expenseDetails) {
        Expense updatedExpense = expenseService.updateExpense(id, expenseDetails);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Expense deleted successfully");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String category) {
        List<Expense> expenses = expenseService.getExpensesByCategory(category);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Expense>> getExpensesByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Expense> expenses = expenseService.getExpensesByDateRange(start, end);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/category/{category}/date-range")
    public ResponseEntity<List<Expense>> getExpensesByCategoryAndDateRange(
            @PathVariable String category,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Expense> expenses = expenseService.getExpensesByCategoryAndDateRange(category, start, end);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Double>> getTotalExpenses() {
        Double total = expenseService.getTotalExpenses();
        Map<String, Double> response = new HashMap<>();
        response.put("total", total);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total/category/{category}")
    public ResponseEntity<Map<String, Double>> getTotalExpensesByCategory(@PathVariable String category) {
        Double total = expenseService.getTotalExpensesByCategory(category);
        Map<String, Double> response = new HashMap<>();
        response.put("total", total);
        response.put("category", (double) category.hashCode());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total/date-range")
    public ResponseEntity<Map<String, Double>> getTotalExpensesByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        Double total = expenseService.getTotalExpensesByDateRange(start, end);
        Map<String, Double> response = new HashMap<>();
        response.put("total", total);
        return ResponseEntity.ok(response);
    }
}
