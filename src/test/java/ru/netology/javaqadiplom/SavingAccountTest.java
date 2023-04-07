package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        //проверка пополнения баланса в пределах максимального лимита
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(1);

        Assertions.assertEquals(2_000 + 1, account.getBalance());
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        // проверка попытки пополнения баланса свыше лимита
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(8_001);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddZero() {
        // проверка попытки пополнения на 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(0);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldNotAddNegative() {
        //проверка пополнения на отрицательное число
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual  = account.add(-1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddMaxBalance() {
        // проверка поплнения на максимальную сумму в пределах баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(10_000, account.getBalance());
    }
    @Test
    public void shouldPayLessThanBalance() {
        //проверка оплаты на сумму, меньшую чем баланс
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(2_999);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPayAllBalanceSum() {
        //проверка оплаты на сумму, равную балансу
        SavingAccount account = new SavingAccount(
                3_000,
                1000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(3_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPayMoreBalanceSum() {
        // проверка попытки оплаты на сумму, большую чем баланс
        SavingAccount account = new SavingAccount(
                3_000,
                3_000,
                10_000,
                5
        );

        boolean expected = false;
        boolean actual = account.pay(3001);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void shouldCountYearChange() {
        //проверка подсчета годового процента
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.yearChange();

        Assertions.assertEquals(3_000/100*5, account.yearChange());
    }


    @Test
    public void shouldTrowWithNegativeRate() {
        //проверка попытки подсчета годовых процентов с отрицаельным процентом
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                -1
        );

        Assertions.assertThrows(IllegalArgumentException.class,() ->{
            account.yearChange();
        });
    }
    @Test
    public void shouldTrowWithNegativeInitialBalance() {
        //проверка попытки создания аккаута с отрицательным текущим счетом
        SavingAccount account = new SavingAccount(
                -3_000,
                1_000,
                10_000,
                1
        );

        Assertions.assertThrows(IllegalArgumentException.class,() ->{
            account.getBalance();
        });
    }
    @Test
    public void shouldTrowWhenMinHigherMaxBalance() {
        // проверка создания аккаунта, где максимальный баланс ниже минимального
        SavingAccount account = new SavingAccount(
                3_000,
                10_000,
                1_000,
                1
        );

        Assertions.assertThrows(IllegalArgumentException.class,() ->{
            account.getBalance();
        });
    }
}
