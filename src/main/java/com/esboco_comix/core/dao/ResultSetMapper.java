package com.esboco_comix.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<E, D> {
    E mapearEntidade(ResultSet rs) throws SQLException;
    D mapearDTO(ResultSet rs) throws SQLException;
}
