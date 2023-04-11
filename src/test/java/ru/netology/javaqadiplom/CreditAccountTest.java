package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    /**
     * // ВЫБРОС ИСКЛЮЧЕНИЙ
     */
    @Test
    public void sholdThrowExceptionBalance() {
        //test1
        //Проверка выброса исключений для отрицательного баланса
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-10_000, 10_000, 10);
        });
    }

    @Test
    public void sholdThrowExceptionRate() {
        //test2
        //Проверка выброса исключений для отрицательной ставки

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(10_000, 10_000, -10);
        });
    }

    @Test
    public void sholdThrowExceptionCreditLimit() {
        //test3
        //проверка выброса исключений для отрицательного кредитного лимита

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(10_000, -10_000, 10);
        });
    }

    /**
     * // Метод Add
     */
    @Test
    public void shouldAddToPositiveBalance() {
        //test4
        // пополнение сверх суммы лимита
        CreditAccount account = new CreditAccount(5_000, 5_000, 15);
        int amount = 3000;
        int expected = 8000;
        int actual = account.balance + amount;
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldAddIfDebitMoreThanPay() {
        //test5
        //пополнение при наличии задолженности
        CreditAccount account = new CreditAccount(3_000, 20_000, 15);
        int amount = 3000;
        int expected = 6000;
        int actual = account.balance + amount;
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void shouldAddAmountZero() {
        //test6
        //пополнение на 0
        CreditAccount account = new CreditAccount(100_000, 100_000, 20);
        int amount = 0;
        Boolean expected = false;
        Boolean actual = account.add(amount);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddAmountZBelowZero() {
        //test7
        //пополнение на отрицательную сумму
        CreditAccount account = new CreditAccount(100_000, 100_000, 20);
        int amount = -100;
        Boolean expected = false;
        Boolean actual = account.add(amount);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddIfBalanceEqualZero() {
        //test8
        //пополнение если на текущем остатке 0
        CreditAccount account = new CreditAccount(0, 100_000, 20);
        int amount = 5000;
        int expected = amount;
        int actual = account.balance + amount;
        Assertions.assertEquals(expected, actual);
    }

    /**
     * // Метод Pay
     */
    @Test
    public void shouldPaySuccessfully() {
        //test9
        //успешный платеж
        CreditAccount account = new CreditAccount(100_000, 100_000, 20);
        int amount = 2000;
        int expected = 98000;
        int actual = account.balance - amount;
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void tryPayIfBalanceUnderAmount() {
        //test10
        //поплытка платежа если средств недостаточно
        CreditAccount account = new CreditAccount(1000, 100_000, 20);
        int amount = 2000;
        account.pay(amount);
        Boolean expected = false;
        Boolean actual = account.pay(2000);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPayIfBalanceEqualAmount() {
        //test11
        //успешный платеж, если остаток равен сумме платежа
        CreditAccount account = new CreditAccount(10_000, 100_000, 20);
        int amount = 10_000;
        Boolean expected = false;
        Boolean actual = account.pay(amount);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldPayIfAmountBelowZero() {
        //test12
        //платеж на отрицательную сумму
        CreditAccount account = new CreditAccount(100_000, 100_000, 20);
        int amount = -5000;
        Boolean expected = false;
        Boolean actual = account.pay(amount);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPayAmountZero() {
        //test13
        //платеж на сумму 0
        CreditAccount account = new CreditAccount(100_000, 100_000, 20);
        int amount = 0;
        Boolean expected = false;
        Boolean actual = account.pay(amount);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TryPayIfLimitEqualZero() {
        //test14
        //Попытка оплаты если кредитный лимит равен 0
        CreditAccount account = new CreditAccount(0, 0, 20);
        int amount = 100;
        account.pay(amount);
        Boolean expected = false;
        Boolean actual = account.pay(2000);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * // Метод yearChange
     */
    @Test
    public void shouldCountPercentRate() {
        //test15
        //рассчет процентной ставки при наличии задолженнности
        CreditAccount account = new CreditAccount(95000, 100_000, 10);
        int expected = 500;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCountPercentRateIfBalanceOverCreditLimit() {
        //test16
        //рассчет процентной ставки если доступный остаток больше кредитного лимита
        CreditAccount account = new CreditAccount(105_000, 100_000, 10);
        int expected = 0;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCountPercentRateIfBalanceEqualCreditLimit() {
        //test17
        //рассчет процентной ставки если доступный остаток равен кредитному лимиту
        CreditAccount account = new CreditAccount(100_000, 100_000, 10);
        int expected = 0;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}
