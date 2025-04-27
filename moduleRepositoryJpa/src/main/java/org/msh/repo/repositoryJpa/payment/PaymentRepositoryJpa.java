//package org.msh.repositoryJpa.payment;
//
//import org.msh.entity.payment.GatewayEnt;
//import msh.controller.enums.PaymentGateway;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface PaymentRepositoryJpa extends JpaRepository<GatewayEnt, Long>
//{
//    @Query(value = "select * from tbl_payment_gateway p where p.payment_gateway = ':pg'"
//    ,nativeQuery = true)
//    Optional<GatewayEnt> myFindByPaymentGateway(@Param("pg") PaymentGateway paymentGateway);
//}
