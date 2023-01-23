package com.application.entities;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.application.enums.AccountStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Operation> operations;
}
