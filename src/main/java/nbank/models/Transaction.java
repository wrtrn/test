package nbank.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction extends BaseModel {
    private long id;
    private int amount;
    private String type;
    private String timestamp;
    private int relatedAccountId;
}
