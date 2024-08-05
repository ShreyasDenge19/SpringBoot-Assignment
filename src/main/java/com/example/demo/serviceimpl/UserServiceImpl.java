package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	 	@Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;


		@Override
		public void registerUser(User user) {
			// TODO Auto-generated method stub
			user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userRepository.save(user);
		}

		@Override
		public boolean loginUser(User user) {
			// TODO Auto-generated method stub
			User existingUser = userRepository.findByUsername(user.getUsername());
	        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
	            return true;
	        }

			return false;
		}
}
