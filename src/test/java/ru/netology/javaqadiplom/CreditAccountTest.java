package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    @Test
    public void sholdThrowExceptionBalance() {
        //Проверка выброса исключений для отрицательного баланса
        CreditAccount account = new CreditAccount(-10_000, 10_000, 10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.getBalance();
        });
    }
    @Test
    public void sholdThrowExceptionRate() {
        //Проверка выброса исключений для отрицательной ставки
        CreditAccount account = new CreditAccount(10_000, 10_000, -10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.getRate();
        });
    }

    @Test
    public void sholdThrowExceptionCreditLimit() {
        //проверка выброса исключений для отрицательного кредитного лимита
        CreditAccount account = new CreditAccount(10_000, -10_000, 10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.getCreditLimit();
        });
    }

    @Test
    public void shouldAddToPositiveBalance() {
        // пополнение сверх суммы лимита
        Account account = new CreditAccount(5_000, 5_000, 15);
        int amount = 3000;
        account.add(amount);
        int expected = 8000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldAddIfDebitMoreThanPay() {
        //пополнение при наличии задолженности
        Account account = new CreditAccount(3_000, 20_000, 15);
        int amount = 3000;
        account.add(amount);
        int expected = 6000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPaySuccessfully() {
        //успешный платеж
        CreditAccount account = new CreditAccount(100_000, 100_000, 20);
        int amount = 2000;
        account.pay(amount);
        int expected = account.getBalance() - amount;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void tryPayIfBalanceUnderAmount() {
        //поплытка платежа если средств недостаточно
        CreditAccount account = new CreditAccount(1000, 100_000, 20);
        int amount = 2000;
        account.pay(amount);
        Boolean expected = false;
        Boolean actual = account.pay(2000);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCountPercentRate() {
        //рассчет процентной ставки при наличии задолженнности
        CreditAccount account = new CreditAccount(95000, 100_000, 10);
        int expected = 500;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCountPercentRateIfBalanceOverCreditLimit() {
        //рассчет процентной ставки при отсутствии задолженности
        CreditAccount account = new CreditAccount(105_000, 100_000, 10);
        int expected = 0;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}

