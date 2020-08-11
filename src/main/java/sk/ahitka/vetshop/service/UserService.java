package sk.ahitka.vetshop.service;

import sk.ahitka.vetshop.domain.model.User;
import lombok.NonNull;

public interface UserService {

    @NonNull User saveUser(@NonNull String username, @NonNull String password, @NonNull String email);
}
