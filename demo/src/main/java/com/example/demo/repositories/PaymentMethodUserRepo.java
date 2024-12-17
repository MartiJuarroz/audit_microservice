// package com.example.demo.repositories;

// import com.example.demo.model.PaymentMethodUser;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface PaymentMethodUserRepo extends JpaRepository<PaymentMethodUser, String> {

//     List<PaymentMethodUser> findByUserIdAndPaymentMethodId(String userId, Long paymentMethodId);

//     @Query(value = "DELETE FROM payment_method_user WHERE user_id = :userId" +
//             " AND payment_method_id = :paymentMethodId ", nativeQuery = true)
//     void deletePMUser(@Param("userId")String userId, @Param("paymentMethodId")String paymentMethodId);
// }
