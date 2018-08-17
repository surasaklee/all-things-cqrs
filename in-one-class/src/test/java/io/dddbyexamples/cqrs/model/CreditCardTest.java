package io.dddbyexamples.cqrs.model;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CreditCardTest {

    @Test
    public void shouldNotBeAbleToWithdrawWhenThereIsNotEnoughMoney() {
        //given
        CreditCard creditCard = new CreditCard(TEN);

        //expect
        assertThatExceptionOfType(NotEnoughMoneyException.class)
                .isThrownBy(
                () -> creditCard.withdraw(new BigDecimal(100)));
    }

    @Test
    public void shouldBeAbleToWithdrawMoney() {
        //given
        CreditCard creditCard = new CreditCard(TEN);

        //when
        creditCard.withdraw(ONE);

        //expect
        assertThat(creditCard.availableBalance()).isEqualByComparingTo(new BigDecimal(9));
        assertThat(creditCard.getWithdrawals()).hasSize(1);
        assertThat(creditCard.getWithdrawals().get(0).getAmount()).isEqualByComparingTo(ONE);
    }

}