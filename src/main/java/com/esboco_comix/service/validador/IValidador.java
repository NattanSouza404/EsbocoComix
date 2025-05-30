package com.esboco_comix.service.validador;

public interface IValidador<E> {
    void validar(E entidade) throws Exception;
}