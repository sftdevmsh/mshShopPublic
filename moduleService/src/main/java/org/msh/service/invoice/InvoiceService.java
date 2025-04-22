package org.msh.service.invoice;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.config.mapper.invoice.InvoiceMapper;
import org.msh.dto.invoice.InvoiceDto;
import org.msh.dto.payment.GotoPaymentDto;
import org.msh.entity.invoice.InvoiceEnt;
import org.msh.entity.invoice.InvoiceItemEnt;
import org.msh.entity.product.ColorEnt;
import org.msh.entity.product.ProductEnt;
import org.msh.entity.product.SizeEnt;
import org.msh.entity.user.UserEnt;
import org.msh.enums.OrderStatus;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.invoice.InvoiceItemRepositoryJpa;
import org.msh.repositoryJpa.invoice.InvoiceRepositoryJpa;
import org.msh.service.generics.MyGenericService;
import org.msh.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InvoiceService implements MyGenericService<InvoiceDto> {

    private final InvoiceRepositoryJpa invoiceRepositoryJpa;
    private final ProductService productService;
    //
    private final InvoiceMapper invoiceMapper;
    private final ModelMapper modelMapper;



    @Autowired
    public InvoiceService(InvoiceRepositoryJpa invoiceRepositoryJpa
            , InvoiceItemRepositoryJpa invoiceItemRepositoryJpa, ProductService productService, InvoiceMapper invoiceMapper
            , ModelMapper modelMapper)
    {
        this.invoiceRepositoryJpa = invoiceRepositoryJpa;
        this.productService = productService;
        this.invoiceMapper = invoiceMapper;
        this.modelMapper = modelMapper;
    }



    @Override
    public InvoiceDto findByIdSrv(Long id) {
        //
        InvoiceDto dto = null;
        //
        try {
            InvoiceEnt invoiceEnt = invoiceRepositoryJpa.findById(id).orElseThrow();
            dto =modelMapper.map(invoiceEnt, InvoiceDto.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return dto;
    }

    @Override
    public List<InvoiceDto> findAllSrv()
    {
        return invoiceRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc()
                .findAll()
                .stream()
                .map(x-> modelMapper.map(x, InvoiceDto.class))
                .toList();
    }

    @Override
    public Page<InvoiceDto> findAllSrv(Integer page, Integer size)
    {
        return invoiceRepositoryJpa
                .findAll(
                        Pageable
                                .ofSize(size)
                                .withPage(page)
                )
                .map(x-> modelMapper.map(x, InvoiceDto.class));
    }


    @Override
    public Boolean deleteByIdSrv(Long id) {
        validationModelId(id);
        invoiceRepositoryJpa.deleteById(id);
        return true;
    }

    @Override
    public InvoiceDto addSrv(InvoiceDto dto) {
        validateDto(dto,false);
        //
        InvoiceEnt ent = modelMapper.map(dto, InvoiceEnt.class);
        //
        if(ent.getCreateDate()==null)
            ent.setCreateDate(LocalDateTime.now());
        //
        return modelMapper.map(invoiceRepositoryJpa.save(ent) , InvoiceDto.class);
    }

    @Override
    public InvoiceDto updateSrv(InvoiceDto dto)  {
        validateDto(dto,true);
        //
        InvoiceDto resDto = null;
        InvoiceEnt dbEnt = null;
        InvoiceEnt ent = null;
        try {
            ent = invoiceMapper.map(dto);
            dbEnt = invoiceRepositoryJpa.findFirstById(dto.getId()).orElseThrow();
            //
            dbEnt.setCreateDate(Optional.ofNullable(ent.getCreateDate()).orElse(dbEnt.getCreateDate()));
            dbEnt.setPaidDate(Optional.ofNullable(ent.getPaidDate()).orElse(dbEnt.getPaidDate()));
            dbEnt.setTotalAmount(Optional.ofNullable(ent.getTotalAmount()).orElse(dbEnt.getTotalAmount()));
            dbEnt.setOrderStatus(Optional.ofNullable(ent.getOrderStatus()).orElse(dbEnt.getOrderStatus()));
            dbEnt.setInvoiceItemEnts(Optional.ofNullable(ent.getInvoiceItemEnts()).orElse(dbEnt.getInvoiceItemEnts()));
            //
            OrderStatus status = null;
            for(OrderStatus s : OrderStatus.values())
                if(s.name().equalsIgnoreCase(dbEnt.getOrderStatus().toString()))
                    status = s;
            dbEnt.setOrderStatus(status);
            //
            resDto = invoiceMapper.map(invoiceRepositoryJpa.save(dbEnt));
            //
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return resDto;
    }




    //region private methods for validation
    @SneakyThrows
    @Override
    public void validationModelId(Long id)
    {
        if(id == null || id<=0)
            throw new Exception("Error! validationModelId _ wrong id");
    }

    @SneakyThrows
    @Override
    public void validateDto(InvoiceDto dto, Boolean checkId) {
        if(dto == null)
            throw new MyExc("validateDto ...");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new MyExc("validateDto ...");
        if(dto.getTotalAmount()==null)
            throw new MyExc("validateDto ...");
    }
    //endregion







    public List<InvoiceDto> findAllByUserEnt_IdSrv(Long id)
    {
        return invoiceRepositoryJpa
                .findFirstByUserEnt_Id(id)
                .stream()
                .map(invoiceMapper::map).toList();
    }


    //Note: @Transactional in paymentController
    public InvoiceEnt saveAndCreate(GotoPaymentDto gotoPaymentDto, UserEnt userEnt)
    {
        //InvoiceDto resDto = null;
        InvoiceEnt entRes = null;
        Set<InvoiceItemEnt> invoiceItemEnts = new HashSet<>();
        List<Long> productIds = new ArrayList<>();
        //
        if(gotoPaymentDto.getBasketItems()!=null && !gotoPaymentDto.getBasketItems().isEmpty()) {
            //
            List<GotoPaymentDto.BasketItem> basketItems = gotoPaymentDto.getBasketItems();
            //
            for (GotoPaymentDto.BasketItem bi : basketItems) {
                productIds.add(bi.getProductId());//to query prices from db later
                //
                invoiceItemEnts.add(
                        InvoiceItemEnt
                                .builder()
                                .productEnt(ProductEnt.builder().id(bi.getProductId()).build())
                                .colorEnt(ColorEnt.builder().id(bi.getColorId()).build())
                                .sizeEnt(SizeEnt.builder().id(bi.getSizeId()).build())
                                .quantity(bi.getQuantity())
                                // .price() :
                                // the list of (productId, price) couples; will be later queried in repository
                                // for all basketItems altogether
                                // using "strProductIds"
                                .build()
                );
            }
            //
            //list of desired products' prices:
            long totalAmount = 0L;
            try {
                HashMap<Long, Long> productPrices = productService.getProductPrices(productIds).orElseThrow();
                for (InvoiceItemEnt ii : invoiceItemEnts) {
                    //
                    Long price = productPrices.get(ii.getProductEnt().getId());
                    //
                    ii.setPrice(price);
                    //
                    price *= ii.getQuantity();
                    totalAmount += price;
                }
            } catch (Exception e) {
                System.out.println("invoiceService BasketItem price");
                System.out.println(e.getMessage());
            }
            //
            //
            InvoiceEnt ent = InvoiceEnt
                    .builder()
                    .userEnt(userEnt)
                    .invoiceItemEnts(invoiceItemEnts)
                    .totalAmount(totalAmount)
                    .paidDate(null)
                    .orderStatus(OrderStatus.Progressing)
                    .createDate(LocalDateTime.now())
                    .build();
            //
            //
            entRes = invoiceRepositoryJpa.save(ent);//ent with Id after saving
            //
            //resDto = invoiceMapper.map(ent);
        }
        //return resDto;
        return entRes;
    }



}
