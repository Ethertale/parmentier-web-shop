package io.ethertale.parmentierpenshop.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "pen_id")
    private Pen pen;

    @Column(nullable = false)
    private int quantity;

    public BigDecimal getSubtotal() {
        return pen.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

}
