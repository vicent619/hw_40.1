package app.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Instant creationDate;
    private BigDecimal totalCost;
    private List<Product> products;
}
