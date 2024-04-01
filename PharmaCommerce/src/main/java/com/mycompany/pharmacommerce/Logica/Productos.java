package com.mycompany.pharmacommerce.Logica;
import java.time.LocalDate;

public class Productos {
    //Atributos
    private String id_producto;
    private String nombre;
    private String descripcion;
    private int precio_venta;
    private String id_proveedor;
    private int stock;
    private LocalDate fecha_vencimiento;
    private String id_categoria;
    
    //Constructor vacío
    public Productos() {
    }
    
    //Constructor con parametros
    public Productos(String id_producto, String nombre, String descripcion, int precio_venta, String id_proveedor, int stock, LocalDate fecha_vencimiento, String id_categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_venta = precio_venta;
        this.id_proveedor = id_proveedor;
        this.stock = stock;
        this.fecha_vencimiento = fecha_vencimiento;
        this.id_categoria = id_categoria;
    }
    
    //Metodos GET y SET
    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }
    
    
    
     
    
}
