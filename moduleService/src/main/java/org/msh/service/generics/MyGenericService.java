package org.msh.service.generics;

import lombok.SneakyThrows;
import org.msh.dto.product.ProductDto;
import org.msh.exceptions.MyExc;

public interface MyGenericService<T>
        extends MyFindGenericService<T>
                , MyDeleteGenericService<T>
                , MyAddGenericService<T>
                , MyUpdateGenericService<T>
                , MyValidateGenericService<T> {

    @SneakyThrows
    void validateDto(T dto, Boolean checkId) throws MyExc;

    @SneakyThrows
    void validationModelId(Long id);




}
