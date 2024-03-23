package com.sdp.artgallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdp.artgallery.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>
{

}
