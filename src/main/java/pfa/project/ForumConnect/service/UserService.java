package pfa.project.ForumConnect.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfa.project.ForumConnect.exception.UserNotFoundException;
import pfa.project.ForumConnect.model.user;
import pfa.project.ForumConnect.repo.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo UserRepo;
    @Autowired
    public UserService(pfa.project.ForumConnect.repo.UserRepo userRepo) {
        UserRepo = userRepo;
    }
    public user addUser(user user){


        return UserRepo.save(user);
    }
    public List<user> findAllUsers(){

        return UserRepo.findAll();
    }
    public user UpdateUser(user user){
        return UserRepo.save(user);
    }
    public void deleteUser(String id){

        UserRepo.deleteById(id);
    }
    public user findUserById(String id){

        return UserRepo.findById(id).orElseThrow(()
                ->new UserNotFoundException("User by id "+id+" was not found :( "));
    }
}
