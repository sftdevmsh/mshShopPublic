package org.msh.service.invoice;

import org.modelmapper.ModelMapper;
import org.msh.config.mapper.invoice.InvoiceMapper;
import org.msh.dto.invoice.InvoiceDto;
import org.msh.dto.payment.GotoPaymentDto;
import org.msh.entity.invoice.InvoiceEnt;
import org.msh.entity.invoice.InvoiceItemEnt;
import org.msh.entity.product.ColorEnt;
import org.msh.entity.product.ProductEnt;
import org.msh.entity.product.SizeEnt;
import org.msh.enums.OrderStatus;
import org.msh.repositoryJpa.order.InvoiceItemRepositoryJpa;
import org.msh.repositoryJpa.order.InvoiceRepositoryJpa;
import org.msh.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InvoiceService {

    private final InvoiceRepositoryJpa invoiceRepositoryJpa;
    private final InvoiceItemRepositoryJpa invoiceItemRepositoryJpa;
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
        this.invoiceItemRepositoryJpa = invoiceItemRepositoryJpa;
        this.productService = productService;
        this.invoiceMapper = invoiceMapper;
        this.modelMapper = modelMapper;
    }


    //Note: @Transactional in paymentController
    public InvoiceEnt saveAndCreate(GotoPaymentDto gotoPaymentDto)
    {
        //InvoiceDto dtoRes = null;
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
            //dtoRes = invoiceMapper.map(ent);
        }
        //return dtoRes;
        return entRes;
    }


}
