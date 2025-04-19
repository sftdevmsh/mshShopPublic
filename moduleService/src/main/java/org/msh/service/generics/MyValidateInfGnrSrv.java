package org.msh.service.generics;

import org.msh.exceptions.MyExc;

public interface MyValidateInfGnrSrv<T> {

    void validateDto(T dto, Boolean checkId) throws MyExc;
}
