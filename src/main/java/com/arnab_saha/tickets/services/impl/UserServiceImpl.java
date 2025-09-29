package com.arnab_saha.tickets.services.impl;

import com.arnab_saha.tickets.repositories.UserRepository;
import com.arnab_saha.tickets.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}
