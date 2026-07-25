package com.esboco_comix.core.dao.transaction;

import java.sql.Connection;

import com.esboco_comix.core.utils.ConexaoFactory;

public class TransactionExecutor {

    public <T> T execute(TransactionAction<T> callback) {

        try (Connection conn = ConexaoFactory.getConexao()) {

            conn.setAutoCommit(false);

            try {
                T resultado = callback.execute(conn);

                conn.commit();

                return resultado;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface TransactionAction<T> {
        T execute(Connection connection) throws Exception;
    }

}