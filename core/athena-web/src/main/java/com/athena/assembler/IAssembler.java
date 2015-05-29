package com.athena.assembler;

/**
 * Created by epr on 29/05/15.
 */
public interface IAssembler<T,S> {

    public T toModel(S value);

    public S to(T model);
}
