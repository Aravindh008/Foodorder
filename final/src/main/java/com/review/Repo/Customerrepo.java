package com.review.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.review.Model.Customermodel;

public interface Customerrepo extends JpaRepository<Customermodel , Long> {

}
