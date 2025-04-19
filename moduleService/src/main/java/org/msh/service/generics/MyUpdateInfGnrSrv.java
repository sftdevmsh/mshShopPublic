package org.msh.service.generics;

import org.msh.exceptions.MyExc;

public interface MyUpdateInfGnrSrv<T> {

    T updateSrv(T dto) throws MyExc;
}
