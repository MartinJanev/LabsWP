package mk.ukim.finki.wp.kol2025g3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate dateCreated;

    private Double amount;

    private Integer daysToExpire;

    @Enumerated(value = EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @ManyToOne
    private Vendor vendor;

    public Expense(String title, LocalDate dateCreated, Double amount, Integer daysToExpire, ExpenseCategory expenseCategory, Vendor vendor) {
        this.title = title;
        this.dateCreated = dateCreated;
        this.amount = amount;
        this.daysToExpire = daysToExpire;
        this.expenseCategory = expenseCategory;
        this.vendor = vendor;
    }

}
