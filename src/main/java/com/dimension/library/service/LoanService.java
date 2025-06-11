package com.dimension.library.service;

import com.dimension.library.entity.Book;
import com.dimension.library.entity.Loan;
import com.dimension.library.entity.User;
import com.dimension.library.repository.BookRepository;
import com.dimension.library.repository.LoanRepository;
import com.dimension.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Loan createLoan(Long bookId, Long userId) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            Book book = optionalBook.get();
            User user = optionalUser.get();

            // Check if book is available
            if (book.getAvailable()) {
                Loan loan = new Loan(book, user);

                // Mark book as not available
                book.setAvailable(false);
                bookRepository.save(book);

                return loanRepository.save(loan);
            }
        }
        return null;
    }

    public List<Loan> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    public Loan returnBook(Long loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);

        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();

            if (loan.getReturnDate() == null) {
                // Set return date
                loan.setReturnDate(LocalDate.now());

                // Mark book as available
                Book book = loan.getBook();
                book.setAvailable(true);
                bookRepository.save(book);

                return loanRepository.save(loan);
            }
        }
        return null;
    }
}