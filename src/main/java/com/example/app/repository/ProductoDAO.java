package com.example.app.repository;

import com.example.app.model.Producto;
import com.example.app.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public List<Producto> obtenerProductos(){

        List<Producto> lista =new ArrayList<>();
        String sql ="SELECT * FROM producto";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getLong("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
