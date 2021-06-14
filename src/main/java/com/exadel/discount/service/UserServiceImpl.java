package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.CouponNotFoundException;
import com.exadel.discount.exception.UserNotFoundException;
import com.exadel.discount.exception.UserSuchNameNotFoundException;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CouponRepository couponRepository) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
    }

    @Transactional
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> deleteUser(UUID id) {
        User deletedUser = findUserById(id);
        userRepository.delete(deletedUser);
        return findAllUsers();
    }

    @Override
    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> findAllUsers() {
        return StreamSupport.
                stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findUsersByName(String name) {
        List<User> suchNameUserList = userRepository.findUsersByName(name);
        if (suchNameUserList.size() == 0) throw new UserSuchNameNotFoundException(name);
        return suchNameUserList;
    }

    @Transactional
    @Override
    public User editUser(UUID id, User user) {
        User editedUser = findUserById(id);
        editedUser.setFirstName(user.getFirstName());
        editedUser.setLastName(user.getLastName());
        editedUser.setEmail(user.getEmail());
        editedUser.setPhone(user.getPhone());
        editedUser.setRole(user.getRole());
        editedUser.setLogin(user.getLogin());
        editedUser.setPassword(user.getPassword());
        return editedUser;
    }

    @Transactional
    @Override
    public User addNewCouponToUser(Coupon coupon, UUID userId) {
        User user = findUserById(userId);
        coupon.setUser(user);
        couponRepository.save(coupon);
        user.addCoupon(coupon);
        coupon.setUser(user);
        return user;
    }

    @Transactional
    @Override
    public User removeCouponFromUser(UUID userId, Coupon coupon) {
        couponRepository.delete(coupon);
        findUserById(userId).removeCoupon(coupon);
        return findUserById(userId);
    }
}

