package com.expense.manager.service;

import com.expense.manager.exception.ResourceNotFoundException;
import com.expense.manager.model.Expense;
import com.expense.manager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = getExpenseById(id);
        
        expense.setDescription(expenseDetails.getDescription());
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDate(expenseDetails.getDate());
        
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate);
    }

    public List<Expense> getExpensesByCategoryAndDateRange(String category, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByCategoryAndDateBetween(category, startDate, endDate);
    }

    public Double getTotalExpenses() {
        return expenseRepository.findAll().stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public Double getTotalExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category).stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public Double getTotalExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate).stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
