package com.pce.BookMeTutor.Controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pce.BookMeTutor.Config.Constants;
import com.pce.BookMeTutor.Model.Dao.Tutor;
import com.pce.BookMeTutor.Model.Dao.UserEntity;
import com.pce.BookMeTutor.Model.Dto.Requests.AuthenticationRequest;
import com.pce.BookMeTutor.Model.Dto.Requests.ForgotRequest;
import com.pce.BookMeTutor.Model.Dto.Requests.StudentRegistrationRequest;
import com.pce.BookMeTutor.Model.Dto.Requests.TutorRegistrationRequest;
import com.pce.BookMeTutor.Model.Dto.Responses.AuthenticationResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.MessageResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.RegistrationResponse;
import com.pce.BookMeTutor.Repo.TutorRepo;
import com.pce.BookMeTutor.Repo.UserRepo;
import com.pce.BookMeTutor.Services.EmailService;
import com.pce.BookMeTutor.Services.JwtTokenService;
import com.pce.BookMeTutor.Services.MyUserDetailService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TutorRepo tutorRepo;

	@Autowired
	private EmailService emailService;

	Map<String, String> resetRequest = new HashMap<String, String>();

	@GetMapping("/confirm-password")
	public ResponseEntity<?> changePassword(@RequestParam() String mail,
			@RequestParam() String jwt, @RequestParam() String role) {

		String userName = jwtTokenService.getUsernameFromToken(jwt);

		if (!userName.equalsIgnoreCase(mail)) {
			return new ResponseEntity<>(Constants.INVALID_TOKEN,
					HttpStatus.BAD_REQUEST);
		} else {
			if (role.equalsIgnoreCase("student")) {
				UserEntity userEntity = userRepo.findByEmail(mail);
				userEntity.setPassword(resetRequest.get(mail));
				userRepo.save(userEntity);
				resetRequest.remove(mail);
				return ResponseEntity.ok("<h2>Password reset complete!</h2>");
			}
			if (role.equalsIgnoreCase("tutor")) {
				Tutor tutor = tutorRepo.findByEmail(mail);
				tutor.setPassword(resetRequest.get(mail));
				tutorRepo.save(tutor);
				resetRequest.remove(mail);
				return ResponseEntity.ok("<h2>Password reset complete!</h2>");
			}
			return new ResponseEntity<>(Constants.INVALID_REQUEST,
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> resetPassword(
			@RequestBody ForgotRequest forgotRequest)
			throws MessagingException, IOException {

		String mail = forgotRequest.getEmail();
		String password = encoder.encode(forgotRequest.getPassword());
		String role = forgotRequest.getRole();
		String token;

		if (role.equalsIgnoreCase("student")) {
			UserEntity userEntity = userRepo.findByEmail(mail);
			if (userEntity == null)
				return new ResponseEntity<>(Constants.USER_NOT_FOUND,
						HttpStatus.NOT_FOUND);
			else {

				resetRequest.put(mail, password);

				token = jwtTokenService.generateToken(
						myUserDetailService.loadUserByUsername(mail));

				emailService.sendMail(mail, "confirm password reset",
						"<h4>Click on the link below to  reset password\n\n"
								+ "<a href=\"" + Constants.BACKEND_URL
								+ "account/confirm-password?mail=" + mail
								+ "&jwt=" + token + "&role=student"
								+ "\">Click me!</a></h4>");
				return ResponseEntity
						.ok(new MessageResponse("check email to confirm!"));
			}
		}
		if (forgotRequest.getRole().equalsIgnoreCase("tutor")) {
			Tutor tutor = tutorRepo.findByEmail(mail);
			if (tutor == null)
				return new ResponseEntity<>(Constants.TUTOR_NOT_FOUND,
						HttpStatus.NOT_FOUND);
			else {
				resetRequest.put(mail, password);

				token = jwtTokenService.generateToken(
						myUserDetailService.loadUserByUsername(mail));

				emailService.sendMail(mail, "confirm password reset",
						"<h4>Click on the link below to  reset password\n\n"
								+ "<a href=" + Constants.BACKEND_URL
								+ "account/confirm-password?mail=" + mail
								+ "&jwt=" + token + "&role=tutor"
								+ "\">Click me!</a></h4>");
				return ResponseEntity
						.ok(new MessageResponse("check email to confirm!"));
			}
		}

		return new ResponseEntity<>(Constants.INVALID_REQUEST,
				HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(
			@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticate(authenticationRequest.getEmail(),
					authenticationRequest.getPassword());

			final UserDetails userDetails = myUserDetailService
					.loadUserByUsername(authenticationRequest.getEmail());

			final String token = jwtTokenService.generateToken(userDetails);

			return ResponseEntity.ok(new AuthenticationResponse(token));
		}

		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.UNAUTHORIZED);
		}

	}

	private void authenticate(String username, String password)
			throws Exception {

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username,
							password));

		} catch (DisabledException e) {

			throw new Exception("user not verified or disabled", e);

		} catch (BadCredentialsException e) {

			throw new Exception("invalid email id or password", e);

		}

	}

	@GetMapping("/verify")
	public ResponseEntity<?> verifyMail(@RequestParam() String mail,
			@RequestParam() String jwt, @RequestParam() String role) {

		String userName = jwtTokenService.getUsernameFromToken(jwt);

		if (!userName.equalsIgnoreCase(mail)) {
			return new ResponseEntity<>(Constants.INVALID_TOKEN,
					HttpStatus.BAD_REQUEST);
		} else {
			if (role.equalsIgnoreCase("student")) {
				UserEntity userEntity = userRepo.findByEmail(mail);
				userEntity.setVerified(true);
				userRepo.save(userEntity);
				return ResponseEntity.ok("<h2>Student account verified!</h2>");
			}
			if (role.equalsIgnoreCase("tutor")) {
				Tutor tutor = tutorRepo.findByEmail(mail);
				tutor.setVerified(true);
				tutorRepo.save(tutor);
				return ResponseEntity.ok("<h2>Tutor account verified!</h2>");
			}
			return new ResponseEntity<>(Constants.INVALID_REQUEST,
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/register-student")
	public ResponseEntity<?> registerStudent(
			@RequestBody StudentRegistrationRequest studentRegistrationRequest)
			throws MessagingException, IOException {

		UserEntity userEntity = userRepo
				.findByEmail(studentRegistrationRequest.getEmail());

		if (userEntity != null) {
			return new ResponseEntity<>(Constants.USER_EXISTS,
					HttpStatus.CONFLICT);
		}

		else {
			userEntity = userRepo
					.save(createStudent(studentRegistrationRequest));
			String mail = userEntity.getEmail();
			String token = jwtTokenService.generateToken(
					myUserDetailService.loadUserByUsername(mail));
			emailService.sendMail(userEntity.getEmail(),
					"Registration Confirmation",
					"<h4>Thank you for joining us.\n\nPlease verify your mail using the link given below.\n"
							+ "<a href=\"" + Constants.BACKEND_URL
							+ "account/verify?mail=" + mail + "&jwt=" + token
							+ "&role=student" + "\">Click me!</a></h4>");
			return ResponseEntity.ok(new RegistrationResponse(mail, token,
					new Date(System.currentTimeMillis())));
		}
	}

	@PostMapping("/register-tutor")
	public ResponseEntity<?> registerTutor(
			@RequestBody TutorRegistrationRequest tutorRegistrationRequest)
			throws MessagingException, IOException {

		Tutor tutor = tutorRepo
				.findByEmail(tutorRegistrationRequest.getEmail());

		if (tutor != null) {
			return new ResponseEntity<>(Constants.USER_EXISTS,
					HttpStatus.CONFLICT);
		}

		else {
			tutor = tutorRepo.save(createTutor(tutorRegistrationRequest));
			String mail = tutor.getEmail();
			String token = jwtTokenService.generateToken(
					myUserDetailService.loadUserByUsername(mail));
			emailService.sendMail(tutor.getEmail(), "Registration Confirmation",
					"<h4>Thank you for joining us.\n\nPlease verify your mail using the link given below.\n"
							+ "<a href=\"" + Constants.BACKEND_URL
							+ "account/verify?mail=" + mail + "&jwt=" + token
							+ "&role=tutor" + "\">Click me!</a></h4>");
			return ResponseEntity.ok(new RegistrationResponse(mail, token,
					new Date(System.currentTimeMillis())));
		}
	}

	private UserEntity createStudent(
			StudentRegistrationRequest studentRegistrationRequest) {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(studentRegistrationRequest.getEmail());
		userEntity.setPassword(
				encoder.encode(studentRegistrationRequest.getPassword()));
		userEntity.setFname(studentRegistrationRequest.getFirst_name());
		userEntity.setLname(studentRegistrationRequest.getLast_name());
		userEntity.setGender(studentRegistrationRequest.getGender());
		Set<String> phoneSet = new HashSet<String>();
		phoneSet.add(studentRegistrationRequest.getPhone());
		userEntity.setPhones(phoneSet);
		return userEntity;
	}

	private Tutor createTutor(
			TutorRegistrationRequest tutorRegistrationRequest) {
		Tutor tutor = new Tutor();
		tutor.setEmail(tutorRegistrationRequest.getEmail());
		tutor.setPassword(
				encoder.encode(tutorRegistrationRequest.getPassword()));
		tutor.setFname(tutorRegistrationRequest.getFirst_name());
		tutor.setLname(tutorRegistrationRequest.getLast_name());;
		tutor.setGender(tutorRegistrationRequest.getGender());
		tutor.setQualification(tutorRegistrationRequest.getQualification());
		tutor.setLine1(tutorRegistrationRequest.getLine1());
		tutor.setLine2(tutorRegistrationRequest.getLine2());
		tutor.setCity(tutorRegistrationRequest.getCity());
		tutor.setPincode(tutorRegistrationRequest.getCity());
		Set<String> phoneSet = new HashSet<String>();
		phoneSet.add(tutorRegistrationRequest.getPhone());
		tutor.setPhone(phoneSet);
		tutor.setLastSelected(new Date(System.currentTimeMillis()));
		return tutor;
	}

}
