package com.example.pantrysocks;

import com.example.pantrysocks.socks.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SocksRepository extends JpaRepository<Socks, String> {

}