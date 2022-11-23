package com.startuppoc.usermanage.controller;


import com.startuppoc.usermanage.model.Role;
import com.startuppoc.usermanage.model.RoleName;
import com.startuppoc.usermanage.model.User;
import com.startuppoc.usermanage.payload.ApiResponse;
import com.startuppoc.usermanage.payload.LoginRequest;
import com.startuppoc.usermanage.payload.SignUpRequest;
import com.startuppoc.usermanage.repository.RoleRepository;
import com.startuppoc.usermanage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.startuppoc.usermanage.model.RoleName.ROLE_ADMIN;
import static com.startuppoc.usermanage.model.RoleName.ROLE_VIEW;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Value("${login.attempt}")
    private int loginAttemptExceed;

    @Value("${login.pending.status}")
    private int loginPendingStatus;

    @GetMapping("/get-all-locked-account")
    public ResponseEntity<?> getAllLockedAccounts() {

        try {
            List<User> users= userRepository.findByLoginAttempt(loginAttemptExceed);
            return ResponseEntity.ok(new ApiResponse(true,users,"Successfully retrieved all locked accounts"));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"",e.getMessage()));
        }

    }

    @GetMapping("/get-all-pending-account")
    public ResponseEntity<?> getAllPendingAccounts() {

        try {
            List<User> users= userRepository.findByPendingStatus(loginPendingStatus);
            return ResponseEntity.ok(new ApiResponse(true,users,"Successfully retrieved all pending accounts"));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"",e.getMessage()));
        }

    }

    @PostMapping("/unlock_account_by_id")
    public ResponseEntity<?> unlockAccount(@RequestBody User users){
        try {
            Optional<User> user= userRepository.findById(users.getId());
            user.get().setLoginAttemptCount(0);
            userRepository.save(user.get());
            return ResponseEntity.ok(new ApiResponse(true,"","Successfully account unlocked"));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"",e.getMessage()));
        }

    }

    @PostMapping("/activate_pending_account_by_id")
    public ResponseEntity<?> activatePendingAccountById(@RequestBody User users){
        try {
            Optional<User> user= userRepository.findById(users.getId());
            user.get().setStatus(3);
            userRepository.save(user.get());
            return ResponseEntity.ok(new ApiResponse(true,"","Successfully account activated"));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"",e.getMessage()));
        }

    }

    @GetMapping("/get-all-user")
    public ResponseEntity<?> getAllUserWithPublicAccess(){
        try {
            List<User> user= userRepository.findAll();
            Optional<Role> roles= roleRepository.findByName(ROLE_VIEW);
            List<User> result = user.stream().filter(x-> {
                return x.getRoles().stream().allMatch(y-> y.getId() ==roles.get().getId());
            }).collect(Collectors.toList());

            return ResponseEntity.ok(new ApiResponse(true,result.stream().map(x->x.getFirstname()),"Successfully data retrieved"));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"",e.getMessage()));
        }

    }


    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody User users){
        try {
            Optional<User> user= userRepository.findById(users.getId());
           if (users.getFirstname() !=null)
               user.get().setFirstname(users.getFirstname());
           if (users.getLastname() !=null)
               user.get().setLastname(users.getLastname());
           if (users.getEmail() !=null)
            user.get().setEmail(users.getEmail());
           user.get().setUsername(users.getEmail());

            userRepository.save(user.get());
            return ResponseEntity.ok(new ApiResponse(true,"","Successfully account updated"));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"",e.getMessage()));
        }

    }


    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody User users){
        try {
            Optional<User> user= userRepository.findById(users.getId());
            Optional<Role> roles= roleRepository.findByName(ROLE_ADMIN);
            if(user.get().getRoles().stream().allMatch(y-> y.getId() ==roles.get().getId())){
                return ResponseEntity.ok(new ApiResponse(false,"","Sorry!,you don't have permission to delete this account"));

            }else {
                user.get().setStatus(1);
                userRepository.save(user.get());
                return ResponseEntity.ok(new ApiResponse(true,"","Successfully account deleted"));

            }
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"",e.getMessage()));
        }

    }

}
