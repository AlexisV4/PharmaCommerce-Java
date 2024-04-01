package com.mycompany.pharmacommerce;

import com.mycompany.pharmacommerce.Logica.Productos;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class Conexion {
    String bd = "pharmacommerce";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "Sebas1127*";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;
    Statement statement;
    ResultSet rs;

    public Conexion(String bd) {
        this.bd = bd;
    }

    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
            System.out.println("SE CONECTO A BASE DE DATOS " + bd);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("NO SE CONECTO A BASE DE DATOS " + bd);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }

    public void desconectar() {
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Conexion conexion = new Conexion("pharmacommerce");
        conexion.conectar();
    }

    public boolean insertarProducto(String idProducto, String nombre, String descripcion, double precioVenta, String idProveedor, int stock, String vencimiento, String idCategoria) {
        boolean insercionExitosa = false;
        try {
            conectar(); // Conecta a la base de datos

            // Preparar la consulta SQL
            String query = "INSERT INTO productos (id_producto, nombre, descripcion, precio_venta, id_proveedor, stock, fecha_vencimiento, id_categoria) VALUES "
                    + "('" + idProducto + "', '" + nombre + "', '" + descripcion + "', " + precioVenta + ", '" + idProveedor + "', " + stock + ", '" + vencimiento + "', '" + idCategoria + "')";

            // Crear un objeto Statement
            Statement statement = cx.createStatement();

            // Ejecutar la consulta SQL
            int filasAfectadas = statement.executeUpdate(query);

            // Comprobar si se insertó correctamente
            if (filasAfectadas > 0) {
                System.out.println("Se insertó el producto correctamente.");
                insercionExitosa = true;
            } else {
                System.out.println("No se pudo insertar el producto.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar el producto: " + ex.getMessage());
        } finally {
            desconectar(); // Desconecta de la base de datos
        }
        return insercionExitosa;
    }

    public List<Productos> buscarProductos(String terminoBusqueda) {
        List<Productos> resultados = new ArrayList<>();
        try {
            conectar(); // Conectar a la base de datos

            // Preparar la consulta SQL
            String query = "SELECT * FROM productos WHERE nombre LIKE ?";

            // Crear un objeto PreparedStatement
            PreparedStatement preparedStatement = cx.prepareStatement(query);

            // Establecer el parámetro para la consulta preparada
            preparedStatement.setString(1, "%" + terminoBusqueda + "%");

            // Ejecutar la consulta SQL
            ResultSet rs = preparedStatement.executeQuery();

            // Recorrer los resultados y crear objetos Productos
            while (rs.next()) {
                Productos producto = new Productos();
                producto.setId_producto(rs.getString("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio_venta(rs.getInt("precio_venta"));
                producto.setId_proveedor(rs.getString("id_proveedor"));
                producto.setStock(rs.getInt("stock"));
                producto.setFecha_vencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
                producto.setId_categoria(rs.getString("id_categoria"));
                resultados.add(producto);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar productos: " + ex.getMessage());
        } finally {
            desconectar(); // Desconectar de la base de datos
        }
        return resultados;
    }
    
    public boolean actualizarProducto(String id, String campo, String valorNuevo) {
        boolean actualizacionExitosa = false;
        try {
            conectar(); // Conectar a la base de datos

            // Preparar la consulta SQL para la actualización
            String query = "UPDATE productos SET " + campo + " = ? WHERE id_producto = ?";

            // Crear un objeto PreparedStatement
            PreparedStatement preparedStatement = cx.prepareStatement(query);

            // Establecer los parámetros para la consulta preparada
            preparedStatement.setString(1, valorNuevo);
            preparedStatement.setString(2, id);

            // Ejecutar la consulta SQL
            int filasAfectadas = preparedStatement.executeUpdate();

            // Comprobar si se actualizó correctamente
            if (filasAfectadas > 0) {
                System.out.println("Se actualizó el producto correctamente.");
                actualizacionExitosa = true;
            } else {
                System.out.println("No se pudo actualizar el producto.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el producto: " + ex.getMessage());
        } finally {
            desconectar(); // Desconectar de la base de datos
        }
        return actualizacionExitosa;
    }
    
    public boolean eliminarProducto(String id) {
        boolean eliminacionExitosa = false;
        try {
            conectar(); // Conectar a la base de datos

            // Preparar la consulta SQL para la eliminación
            String query = "DELETE FROM productos WHERE id_producto = ?";

            // Crear un objeto PreparedStatement
            PreparedStatement preparedStatement = cx.prepareStatement(query);

            // Establecer el parámetro para la consulta preparada
            preparedStatement.setString(1, id);

            // Ejecutar la consulta SQL
            int filasAfectadas = preparedStatement.executeUpdate();

            // Comprobar si se eliminó correctamente
            if (filasAfectadas > 0) {
                System.out.println("Se eliminó el producto correctamente.");
                eliminacionExitosa = true;
            } else {
                System.out.println("No se pudo eliminar el producto.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el producto: " + ex.getMessage());
        } finally {
            desconectar(); // Desconectar de la base de datos
        }
        return eliminacionExitosa;
    }
}
