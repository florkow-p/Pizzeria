package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = -7322759631220603229L;

    private Set<OrderItem> orderItemSet;
    private OrderDetails orderDetails;
    private BigDecimal price;
}
