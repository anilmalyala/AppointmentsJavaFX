package com.csia.anish.repo;

import com.csia.anish.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<User,String> {
}
