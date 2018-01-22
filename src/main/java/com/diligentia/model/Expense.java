package com.diligentia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

@Entity()
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private LocalDate date;
    private String name;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private ExpenceCategory expenceCategory = ExpenceCategory.FOOD;

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ExpenceCategory getExpenceCategory() {
        return expenceCategory;
    }

    public void setExpenceCategory(ExpenceCategory expenceCategory) {
        this.expenceCategory = expenceCategory;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", expenceCategory=" + expenceCategory +
                '}';
    }
}