package com.example.userapi.Repository;

import com.example.userapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAllBy(Pageable pageable);

    Page<User> findByName(String name, Pageable pageable);

    Slice<User> findByNameAndAndSurname(String name, String surname, Pageable pageable);
}
