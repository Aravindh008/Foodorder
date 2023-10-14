package com.review.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.Model.Paymentmodel;
import com.review.Repo.Paymentrepo;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class Paymentcontroller {
	 @Autowired
	    private Paymentrepo paymentRepository;

	    @GetMapping
	    public List<Paymentmodel> getAllPayments() {
	        return paymentRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Paymentmodel> getPaymentById(@PathVariable Long id) {
	        return paymentRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Paymentmodel createPayment(@RequestBody Paymentmodel payment) {
	        return paymentRepository.save(payment);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Paymentmodel> updatePayment(@PathVariable Long id, @RequestBody Paymentmodel updatedPayment) {
	        return paymentRepository.findById(id).map(payment -> {
	            payment.setTransactionId(updatedPayment.getTransactionId());
	            payment.setAmount(updatedPayment.getAmount());
	            payment.setStatus(updatedPayment.getStatus());
	            paymentRepository.save(payment);
	            return ResponseEntity.ok(payment);
	        }).orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
	        if (paymentRepository.existsById(id)) {
	            paymentRepository.deleteById(id);
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
