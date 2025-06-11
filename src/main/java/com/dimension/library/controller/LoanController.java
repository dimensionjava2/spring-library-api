package com.dimension.library.controller;

import com.dimension.library.entity.Loan;
import com.dimension.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestParam Long bookId, @RequestParam Long userId) {

        Loan loan = loanService.createLoan(bookId, userId);

        if (loan != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(loan);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUserId(@PathVariable Long userId) {

        List<Loan> loans = loanService.getLoansByUserId(userId);
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnBook(@PathVariable Long id) {

        Loan returnedLoan = loanService.returnBook(id);
        if (returnedLoan != null) {
            return ResponseEntity.ok(returnedLoan);
        }
        return ResponseEntity.badRequest().build();
    }
}