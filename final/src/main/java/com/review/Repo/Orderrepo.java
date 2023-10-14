package com.review.Repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.review.Model.Ordermodel;

public interface Orderrepo extends JpaRepository<Ordermodel, Long> {

}
