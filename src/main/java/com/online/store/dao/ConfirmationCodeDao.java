package com.online.store.dao;

import com.online.store.model.ConfirmationCode;
import com.online.store.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmationCodeDao extends CrudRepository<ConfirmationCode, Long> {

    List<ConfirmationCode> getAllByUserOrderByIdDesc(User user);

}
