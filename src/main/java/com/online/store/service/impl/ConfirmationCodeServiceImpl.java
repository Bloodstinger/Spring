package com.online.store.service.impl;

import com.online.store.dao.ConfirmationCodeDao;
import com.online.store.model.ConfirmationCode;
import com.online.store.model.User;
import com.online.store.service.ConfirmationCodeService;
import com.online.store.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {

    private final ConfirmationCodeDao confirmationCodeDao;
    private final UserService userService;

    public ConfirmationCodeServiceImpl(ConfirmationCodeDao confirmationCodeDao, UserService userService) {
        this.confirmationCodeDao = confirmationCodeDao;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCode(User user) {
        List<ConfirmationCode> userList = confirmationCodeDao.getAllByUserOrderByIdDesc(user);
        ConfirmationCode code = userList.get(0);
        return code.getValue();
    }

    @Transactional
    @Override
    public void addCode(User user, String codeToAdd) {
        ConfirmationCode confirmationCode = new ConfirmationCode(user, codeToAdd);
        confirmationCodeDao.save(confirmationCode);
    }
}
