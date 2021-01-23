package pl.pizzeria.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.pizzeria.meal.domain.dinner.DinnerDto;
import pl.pizzeria.meal.domain.mapper.DinnerMapper;
import pl.pizzeria.meal.domain.mapper.MealMapper;
import pl.pizzeria.meal.domain.mapper.PizzaMapper;
import pl.pizzeria.meal.domain.pizza.PizzaDto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_history")
public class Order implements Serializable {
    private static final long serialVersionUID = -7322759631220603229L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_history_id")
    private Set<OrderItemEntity> orderItems;

    @Transient
    @JsonIgnore
    private Set<OrderItem> orderItemSet;

    @Embedded
    private OrderDetails orderDetails;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationTime;

    private BigDecimal price;

    public Order convertToEntity() {
        if(orderItems == null) {
            orderItems = new HashSet<>();
        }

        orderItemSet.forEach(orderItem -> {
            if (orderItem.getMeal() instanceof PizzaDto) {
                OrderItemEntity orderItemEntity = new OrderItemEntity(null,
                        PizzaMapper.INSTANCE.pizzaDtoToPizza((PizzaDto) orderItem.getMeal()),
                        orderItem.getQuantity());
                orderItems.add(orderItemEntity);
            } else if (orderItem.getMeal() instanceof DinnerDto) {
                OrderItemEntity orderItemEntity = new OrderItemEntity(null,
                        DinnerMapper.INSTANCE.dinnerDtoToDinner((DinnerDto) orderItem.getMeal()),
                        orderItem.getQuantity());
                orderItems.add(orderItemEntity);
            } else {
                OrderItemEntity orderItemEntity = new OrderItemEntity(null,
                        MealMapper.INSTANCE.mealDtoToMeal(orderItem.getMeal()),
                        orderItem.getQuantity());
                orderItems.add(orderItemEntity);
            }
        });

        return this;
    }
}
