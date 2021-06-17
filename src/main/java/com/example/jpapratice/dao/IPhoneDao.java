package com.example.jpapratice.dao;

import com.example.jpapratice.dao.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneDao extends JpaRepository<Phone, Long> {
}
