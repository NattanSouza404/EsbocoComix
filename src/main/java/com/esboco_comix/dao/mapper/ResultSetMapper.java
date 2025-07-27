package com.esboco_comix.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<Entidade, DTO> {
    public Entidade mapearEntidade(ResultSet rs) throws SQLException;
    public DTO mapearDTO(ResultSet rs) throws SQLException;
}
