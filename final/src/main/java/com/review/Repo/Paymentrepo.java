package com.review.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.review.Model.Paymentmodel;

public interface Paymentrepo extends JpaRepository<Paymentmodel, Long> {

}
