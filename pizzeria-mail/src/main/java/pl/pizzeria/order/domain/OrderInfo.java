package pl.pizzeria.order.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 6232273446385478953L;

    private UUID orderId;
    private String comment;
    private BigDecimal price;
    private String name;

}
