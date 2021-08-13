package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository를 상속하면 자동으로 IOC
public interface UserRepository extends JpaRepository<User, Integer> {
}
