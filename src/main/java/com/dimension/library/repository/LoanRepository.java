package com.dimension.library.repository;

import com.dimension.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    //@Query: Custom Queries
    //JPQL
    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId")
    List<Loan> findByUserId(@Param("userId") Long userId);

    @Query("SELECT l FROM Loan l WHERE l.book.id = :bookId AND l.returnDate IS NULL")
    Loan findActiveLoanByBookId(@Param("bookId") Long bookId);
}
