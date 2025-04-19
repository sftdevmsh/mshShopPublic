package org.msh.controller.panel.myGenerics;

import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MyDeleteController<T> {
    @DeleteMapping("/del/{id}")
    PanelApiResponseWrapper<Boolean> deleteByIdCtrl(@PathVariable Long id);
}
