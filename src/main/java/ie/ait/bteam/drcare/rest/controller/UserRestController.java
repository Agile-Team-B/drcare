package ie.ait.bteam.drcare.rest.controller;

import ie.ait.bteam.drcare.rest.dto.UserLoginDTO;
import ie.ait.bteam.drcare.rest.exceptions.EntityNotFound;
import ie.ait.bteam.drcare.rest.dto.UserDTO;
import ie.ait.bteam.drcare.rest.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserRestController {

	private UserRestService userRestService;

	@Autowired
	public UserRestController(UserRestService userRestService) {
		this.userRestService = userRestService;
	}

	@PostMapping("create")
	@ResponseBody
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userToCreate, BindingResult result) {
		UserDTO createdUser = userRestService.createUser(userToCreate, result);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("{userId}")
	@ResponseBody
	public ResponseEntity<UserDTO> userDetails(@PathVariable Long userId) {
		try {
			UserDTO userDetails = userRestService.userDetails(userId);
			return new ResponseEntity<>(userDetails, HttpStatus.OK);
		} catch (EntityNotFound entityNotFound) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("list")
	public ResponseEntity<List<UserDTO>> listUsers() {
		return new ResponseEntity<>(userRestService.listUsers(), HttpStatus.OK);
	}

	@GetMapping("listUserTypes")
	public ResponseEntity<List<String>> listUserTypes() {
		return new ResponseEntity<>(userRestService.listUserTypes(), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userRestService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO userToCreate) {
		UserDTO createdUser = userRestService.loginUser(userToCreate.getEmail(), userToCreate.getUsername(), userToCreate.getPassword());
		if(createdUser == null){
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}
}
