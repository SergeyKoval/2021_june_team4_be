package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.CouponIsAlreadyAssignedException;
import com.exadel.discount.exception.CouponNotFoundAtSerialNumberException;
import com.exadel.discount.exception.UserNotFoundException;
import com.exadel.discount.exception.UserSuchNameNotFoundException;
import com.exadel.discount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CouponService couponService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CouponService couponService) {
        this.userRepository = userRepository;
        this.couponService = couponService;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(UUID id) {
        User userDeleted = null;
        try {
            userDeleted = findUserById(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (userDeleted != null)
            userRepository.delete(userDeleted);
        return userDeleted;
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
        if (suchNameUserList.size()==0) throw new UserSuchNameNotFoundException(name);
        return suchNameUserList;
    }

    @Transactional
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
    public User addCouponToUser(UUID couponId, UUID orderId) {
        User user = findUserById(couponId);
        Coupon coupon = couponService.findCouponById(orderId);
        if (Objects.nonNull(coupon.getUser())) {
            throw new CouponIsAlreadyAssignedException(orderId,
                    coupon.getUser().getId());
        }
        user.addCoupon(coupon);
        coupon.setUser(user);
        return user;
    }

    @Transactional
    public User removeCouponFromUser(UUID userId, UUID couponId) {
        User user = null;
        Coupon coupon = null;
        try {
            coupon = couponService.findCouponById(couponId);
        } catch (CouponNotFoundAtSerialNumberException e) {
            e.printStackTrace();
        }
        try {
            user = findUserById(couponId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (user != null) {
            user.removeCoupon(coupon);
        }
        return user;
    }
}

