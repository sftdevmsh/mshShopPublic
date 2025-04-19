//package org.msh.service.generics;
//
//import org.modelmapper.ModelMapper;
//import org.msh.dto.generics.Dto;
////import org.msh.exceptions.MyExc;
//import org.msh.repositoryJpa.generics.MyGenericRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MyGenericService<dtoT extends Dto, entT extends Ent> implements MyInfGnrSrv<dtoT>{
//
//    private final MyGenericRepository<entT> myGenericRepository;
//    private final ModelMapper modelMapper;
//    private final Class<dtoT> clsDto;
//    private final Class<entT> clsEnt;
//
//    @Autowired
//    public MyGenericService(
//            MyGenericRepository<entT> myGenericRepository
//            , ModelMapper modelMappers, ModelMapper modelMapper, Class<dtoT> clsDto, Class<entT> clsEnt) {
//        this.myGenericRepository = myGenericRepository;
//        this.modelMapper = modelMapper;
//        this.clsDto = clsDto;
//        this.clsEnt = clsEnt;
//    }
//
//    @Override
//    public dtoT findByIdSrv(Long id) {
//        return modelMapper
//                .map(myGenericRepository.findById(id), clsDto);
//    }
//
//    @Override
//    public List<dtoT> findAllSrv() {
//        return myGenericRepository
//                .findAll()
//                .stream()
//                .map(x->modelMapper.map(x,clsDto))
//                .toList();
//    }
//
//    @Override
//    public Page<dtoT> findAllSrv(Integer page, Integer size) {
//        page = validatePage(page);
//        size = validateSize(size);
//        return myGenericRepository
//                .findAll(Pageable.ofSize(size).withPage(page))
//                .map(x->modelMapper.map(x,clsDto));
//    }
//
//    private Integer validatePage(Integer page) {
//        if(page < 1)
//            page = 1;
//        return page;
//    }
//
//    private Integer validateSize(Integer size) {
//        if(size < 10)
//            size = 10;
//        return size;
//    }
//
//
//    @Override
//    public Boolean deleteByIdSrv(Long id) {
//        myGenericRepository.deleteById(id);
//        return true;
//    }
//
//    @Override
//    public dtoT addSrv(dtoT dto) throws MyExc {
//        validateDto(dto, false);
//        myGenericRepository.save()
//        return ;
//    }
//
//
//    @Override
//    public dtoT updateSrv(dtoT dto) throws MyExc {
//        validateDto(dto, true);
//        return null;
//    }
//
//    @Override
//    public void validateDto(dtoT dto, Boolean checkId) throws MyExc {
//        if(checkId && (dto.getId() == null || dto.getId()<1))
//            throw new MyExc("Error! validationModelProduct _ wrong id");
//        if(dto == null)
//            throw new MyExc("Error! validationModelProduct _ null product");
//    }
//}
