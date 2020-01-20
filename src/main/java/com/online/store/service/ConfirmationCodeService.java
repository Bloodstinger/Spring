package com.online.store.service;

import com.online.store.model.User;

public interface ConfirmationCodeService {

    String getCode(User user);

    void addCode(User user, String codeToAdd);

}
