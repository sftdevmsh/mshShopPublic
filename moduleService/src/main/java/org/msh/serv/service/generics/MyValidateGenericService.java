package org.msh.serv.service.generics;

import org.msh.common.exceptions.MyExc;

public interface MyValidateGenericService<T> {

    void validateDto(T dto, Boolean checkId) throws MyExc;
}
