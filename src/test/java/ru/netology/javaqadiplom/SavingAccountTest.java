package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    //********
    // ВЫБРОСЫ ИСКЛЮЧЕНИЙ ** СОЗДАНИЕ АККАУНТА
    //********

    @Test
    public void shouldTrowWhenInitialBalanceHigherMaxBalance() {
        //test1
        // проверка создания аккаунта, где текущий баланс больше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10_000, 1_000, 9_000, 1);
        });
    }

    @Test
    public void ShouldTrowWhenInitialBalanceLowerMinBalance() {
        // test2
        // проверка создания аккаунта, где текущий баланс меньше минимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(500, 1_000, 9_000, 1);
        });
    }

    @Test
    public void shouldTrowWithNegativeRate() {
        // test3
        //проверка создания аккаунта с отрицаельным процентом
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(3_000, 1_000, 10_000, -1);
        });
    }

    @Test
    public void shouldTrowWhenMinHigherMaxBalance() {
        //test4
        // проверка создания аккаунта, где минимальный баланс больше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(3_000, 10_000, 1_000, 1);
        });
    }

    @Test
    public void ShouldTrowWhenInitialBalanceHigherMaxBalance() {
        // test 5
        // проверка создания аккаунта, где текущий баланс выше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(9_001, 1_000, 9_000, 1);
        });
    }

    @Test
    public void shouldTrowWhenMinBalanceNegative() {
        //test6
        // проверка создания аккаунта, где минимальный баланс отрицательный
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(3_000, -10_000, 1_000, 1);
        });
    }

    //**************
    // ОПЛАТА PAY
    //**************
    @Test
    public void shouldPayLessThanBalanceAndMinBalance() {
        //test 7
        //проверка оплаты на сумму, меньшую чем текущий баланс, остаток в пределах минимального баланса
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.pay(2_000);
        int expected = 3_000 - 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPayAllInitialSumWhenMinBalanceZero() {
        //test 19
        //проверка оплаты на всю сумму текущего баланса (при минимальном 0)
        SavingAccount account = new SavingAccount(
                3_000,
                0,
                10_000,
                5
        );

        account.pay(3_000);
        int expected = 3_000 - 3_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPayUnderMinBalance() {
        //test 8
        //проверка оплаты на сумму, меньшую чем текущий баланс, остаток ниже минимального баланса
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(2_001);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPayMoreThanInitialAndMinBalance() {
        //test 9
        //проверка оплаты на сумму,большую текущего баланса, остаток ниже минимального баланса
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(1_001);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPayZero() {
        //test 10
        //проверка оплаты на 0
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPayNegative() {
        //test 11
        //проверка оплаты на отрицалельное число
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(-1);

        Assertions.assertEquals(expected, actual);
    }

    //**************
    // ПОПОЛНЕНИЕ ADD
    //**************
    @Test
    public void shouldAddLessThanMaxBalance() {
        // test 12
        //проверка пополнения баланса, где остаток ниже максимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(7_999);

        Assertions.assertEquals(2_000 + 7_999, account.getBalance());
    }

    @Test
    public void shouldAddToMaxBalance() {
        // test 13
        //проверка пополнения баланса, где остаток равен максимальному балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldNotAddZero() {
        // test 14
        // проверка  пополнения на 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        // test 15
        // проверка  пополнения, после чего остаток выше максимального лимита
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
    public void shouldNotAddNegative() {
        // test 16
        // проверка  пополнения на отрицательное число
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(-1);

        Assertions.assertEquals(expected, actual);
    }

    //**************
    // % ПОДСЧЕТ ПРОЦЕНТОВ
    //**************

    @Test
    public void shouldCountYearChange() {
        // test 17
        //проверка подсчета годового процента
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.yearChange();

        Assertions.assertEquals(3_000 / 100 * 5, account.yearChange());
    }

    @Test
    public void shouldCountYearChangeWithZeroRate() {
        // test 18
        //проверка подсчета годового процента при ставке 0
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                0
        );

        account.yearChange();

        Assertions.assertEquals(3_000 / 100 * 0, account.yearChange());
    }
}
