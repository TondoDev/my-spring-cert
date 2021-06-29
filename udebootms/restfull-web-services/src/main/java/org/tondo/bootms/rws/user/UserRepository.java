package org.tondo.bootms.rws.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tondo.bootms.rws.beans.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
