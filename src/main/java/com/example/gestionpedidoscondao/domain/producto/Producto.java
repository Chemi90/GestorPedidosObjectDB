package com.example.gestionpedidoscondao.domain.producto;

import com.example.gestionpedidoscondao.domain.itemPedido.ItemPedido;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase Producto.
 * Representa un producto en la base de datos.
 * Esta clase está mapeada a la tabla "Productos" en la base de datos y
 * contiene información sobre el producto, incluyendo su identificador único, nombre, precio,
 * cantidad disponible, y un conjunto de ítems de pedido asociados al producto.
 *
 * @author Author Name
 * @version 1.0
 * @since 2023-11-21
 */
@Data
@Entity
@Table(name = "Productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private double precio;

    private int cantidadDisponible;

    @OneToMany(mappedBy = "producto")
    private Set<ItemPedido> itemsPedidos = new HashSet<>();
}
