package org.danlee.cardealer.repositories;

import org.danlee.cardealer.db.MockDatabase;
import org.danlee.cardealer.entities.Buyer;
import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

@Repository
public class TransactionRepository {
    private final MockDatabase mockDatabase;

    public TransactionRepository(MockDatabase mockDatabase) {
        this.mockDatabase = mockDatabase;
    }

    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> allTransactions = mockDatabase.getAllTransactions();

        allTransactions.sort(Comparator.comparing(Transaction::getTransactionTime).reversed());
        return allTransactions;
    }

    public boolean isUnsold(Car car) {
        ArrayList<Transaction> allTransactions = mockDatabase.getAllTransactions();

        for (Transaction loopedTransaction : allTransactions) {
            if (loopedTransaction.getCar().getId().equals(car.getId())) {
                return false;
            }
        }

        return true;
    }

    public Transaction getTransactionByCar(Car car) {
        ArrayList<Transaction> allTransactions = mockDatabase.getAllTransactions();

        for (Transaction loopedTransaction : allTransactions) {
            if (loopedTransaction.getCar().getId().equals(car.getId())) {
                return loopedTransaction;
            }
        }

        return null;
    }


    public Buyer findBuyerById(UUID id) {
        ArrayList<Transaction> allTransactions = mockDatabase.getAllTransactions();

        for (Transaction loopedTransaction: allTransactions) {
            if (loopedTransaction.getBuyer().getId().equals(id)) {
                return loopedTransaction.getBuyer();
            }
        }

        return null;
    }

    public void save(Transaction transaction) {
        mockDatabase.addTransaction(transaction);
    }

    public Transaction findById(UUID id) {
        ArrayList<Transaction> allTransactions = mockDatabase.getAllTransactions();

        for (Transaction loopedTransaction: allTransactions) {
            if (loopedTransaction.getId().equals(id)) {
                return loopedTransaction;
            }
        }

        return null;
    }
}
