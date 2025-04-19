package org.msh.service.generics;

import org.msh.exceptions.MyExc;

public interface MyAddInfGnrSrv<T> {

    T addSrv(T dto) throws MyExc;
}
