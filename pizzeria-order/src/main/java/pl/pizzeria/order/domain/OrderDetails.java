package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = 221525872329117812L;

    private String name;
    private String comment;
}
