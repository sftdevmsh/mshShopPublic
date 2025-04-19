package org.msh.controller.panel.myGenerics;

public interface MyGenericController<T> extends
        MyFindController<T>
        ,MyDeleteController<T>
        , MyAddController<T>
        ,MyUpdateController<T>
{
}
