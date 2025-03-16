package com.esboco_comix.dao.impl.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAOUtil {
    public static void setParametroComCoringa(PreparedStatement pst, int indice, Object valor) throws SQLException {
        if (valor == null) {
            pst.setNull(indice, java.sql.Types.NULL);
            return;
        }
        
        if (valor instanceof String) {
            pst.setString(indice, valor != null ? "%" + valor + "%" : null);
            return;
        }
        
        if (valor instanceof Integer) {
            pst.setInt(indice, valor != null ? (Integer) valor : null);
            return;
        }
        
        if (valor instanceof Double) {
            pst.setDouble(indice, valor != null ? (Double) valor : null);
            return;
        }
        
        if (valor instanceof Boolean) {
            pst.setBoolean(indice, valor != null ? (Boolean) valor : null);
            return;
        }
        
        if (valor instanceof LocalDate) {
            pst.setDate(indice, valor != null ? java.sql.Date.valueOf((LocalDate) valor) : null);
            return;
        }

        throw new SQLException("Tipo de dado não suportado: " + valor.getClass().getName());
    }
}
