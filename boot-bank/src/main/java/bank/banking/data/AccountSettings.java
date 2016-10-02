package bank.banking.data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Fabian Krüger
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class AccountSettings {

    @JsonIgnore
    private BigDecimal initialBalance;

    private final AccountType accountType;

    @JsonProperty
    public void setInitialBalance(final double initialBalance) {
        this.initialBalance = new BigDecimal(initialBalance);
    }
}
