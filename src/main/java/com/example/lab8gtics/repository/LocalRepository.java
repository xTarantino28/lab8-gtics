package com.example.lab8gtics.repository;
import com.example.lab8gtics.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {
}
