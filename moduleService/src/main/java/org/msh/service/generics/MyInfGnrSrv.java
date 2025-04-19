package org.msh.service.generics;

public interface MyInfGnrSrv<T>
        extends MyFindInfGnrSrv<T>
                , MyDeleteInfGnrSrv<T>
                , MyAddInfGnrSrv<T>
                , MyUpdateInfGnrSrv<T>
                , MyValidateInfGnrSrv<T> {

}
