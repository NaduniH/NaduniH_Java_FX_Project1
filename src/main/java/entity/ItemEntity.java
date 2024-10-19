package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ItemEntity {
    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private Integer qty;


}
