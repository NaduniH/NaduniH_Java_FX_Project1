package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartTm {
    private String itemCode;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double total;


}
