package model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pedido")
public class Pedido extends BaseLongEntity {

    //Campos

    @Column(name = "fechayhoraPedido")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechayhoraPedido;

    @Column(name = "estadoPedido")
    @NotNull
    @Size(min = 5,max = 50)
    private String estadoPedido;

    //Relaciones de tabla

    @JoinColumn(name = "id_cliente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_formapago")
    private FormaPago formaPago;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto")
    private List<Producto> productos;

    @OneToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    //Metodos

    public Pedido() {
    }

    public Pedido(@NotNull Date fechayhoraPedido, @NotNull String estadoPedido, Cliente cliente, FormaPago formaPago, Venta venta) {
        this.fechayhoraPedido = fechayhoraPedido;
        this.estadoPedido = estadoPedido;
        this.cliente = cliente;
        this.formaPago = formaPago;
        this.venta = venta;
    }

    public Pedido(@NotNull Date fechayhoraPedido, @NotNull String estadoPedido) {
        this.fechayhoraPedido = fechayhoraPedido;
        this.estadoPedido = estadoPedido;
    }

    @NotNull
    public Date getFechayhoraPedido() {
        return fechayhoraPedido;
    }

    public void setFechayhoraPedido(@NotNull Date fechayhoraPedido) {
        this.fechayhoraPedido = fechayhoraPedido;
    }

    @NotNull
    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(@NotNull String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
