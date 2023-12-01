package com.microjade.accounts.repository;

import com.microjade.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long>{

    Optional<Accounts> findAccountsByCustomerId(Long customerId);

    //Por detras crea un delete Custom
    //Como esta es una operacion que modifica los datos, necesitamos usar @Transactional y @Modify
    //Esto debe hacerse cada vez que creamos una query custom que modifica datos
    @Transactional //Si algo sale mal hace rollback
    @Modifying //Indicamos que con esta operacion modificamos los datos, se usa generalemente con transactional
    void deleteByCustomerId(Long customerId);

}
