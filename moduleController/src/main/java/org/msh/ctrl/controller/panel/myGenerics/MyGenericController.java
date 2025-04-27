package org.msh.ctrl.controller.panel.myGenerics;

public interface MyGenericController<T> extends
        MyFindController<T>
        ,MyDeleteController<T>
        , MyAddController<T>
        ,MyUpdateController<T>
{
}
