package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }
    @Test
    public void shouldPayLessThanBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(3_000 - 1_000, account.getBalance());
    }

    @Test
    public void shouldPayAllBalanceSum() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(3_000 - 3_000, account.getBalance());
    }

    @Test
    public void shouldNotPayMoreBalanceSum() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_001);

        Assertions.assertEquals(3_000, account.getBalance());
    }
    @Test
    public void shouldCountYearChange() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.yearChange();

        Assertions.assertEquals(3_000/100*5, account.yearChange());
    }
}
