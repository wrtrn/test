package nbank.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class TransactionsResponse extends BaseModel {
    private Transaction[] transactions;

    @JsonCreator
    public TransactionsResponse(Transaction[] transactions) {
        this.transactions = transactions;
    }
}
