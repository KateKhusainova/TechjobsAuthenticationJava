package org.launchcode.techjobsauth.models.data;

import org.launchcode.techjobsauth.models.User;
import org.springframework.data.repository.CrudRepository;

// 1.3. Create a UserRepository.
//Add the special query method to find a user by username.

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
