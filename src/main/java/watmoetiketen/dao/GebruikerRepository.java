package watmoetiketen.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
    
    @Query("SELECT g FROM gebruiker g where g.naam =?1 AND g.wachtwoord =?2")
    Optional<Gebruiker> getGebruiker(String naam, String wachtwoord);

}

//package com.alliander.internet.contractloos.dao;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//public interface CaseRepository extends JpaRepository<ContractloosCase, Integer> {
//    
//    @Query("SELECT c FROM ContractloosCase c where c.securityToken = ?1")
//    Optional<ContractloosCase> findByToken(String token);
//
//    @Query("SELECT c FROM ContractloosCase c where c.code = ?1")
//    Optional<ContractloosCase> findByLogin(String token);
//}
