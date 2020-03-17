package com.pce.BookMeTutor.Controllers;

import com.pce.BookMeTutor.Model.Dto.Requests.UpdateBookingRequest;

import java.lang.Integer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pce.BookMeTutor.Model.Dao.AddressEntity;
import com.pce.BookMeTutor.Model.Dao.Booking;
import com.pce.BookMeTutor.Model.Dao.Invoice;
import com.pce.BookMeTutor.Model.Dao.Tutor;
import com.pce.BookMeTutor.Model.Dao.UserEntity;
import com.pce.BookMeTutor.Model.Dto.Requests.AddressRequest;
import com.pce.BookMeTutor.Model.Dto.Requests.BookingCreationRequest;
import com.pce.BookMeTutor.Model.Dto.Requests.CancellationRequest;
import com.pce.BookMeTutor.Model.Dto.Responses.UserDetailsResponse;
import com.pce.BookMeTutor.Repo.AddressRepo;
import com.pce.BookMeTutor.Repo.BookingRepo;
import com.pce.BookMeTutor.Repo.TutorRepo;
import com.pce.BookMeTutor.Repo.UserRepo;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	TutorRepo tutorRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	BookingRepo bookingRepo;

	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userRepo.findAll());
	}

	@GetMapping("/user/{email}")
	public ResponseEntity<?> getUser(@PathVariable("email") String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);
		return ResponseEntity
				.ok(new UserDetailsResponse(email, userEntity.getFname(),
						userEntity.getLname(), userEntity.getGender()));
	}

	@GetMapping("user/{email}/address")
	public ResponseEntity<?> getUserAddress(
			@PathVariable("email") String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(userEntity.getAddresses());
	}

	@PostMapping("user/{email}/address")
	public ResponseEntity<?> getSpecificAddress(
			@PathVariable("email") String email,
			@RequestBody() AddressRequest addressRequest) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);
		Set<AddressEntity> addressEntities = userEntity.getAddresses();
		if (addressEntities == null)
			addressEntities = new HashSet<AddressEntity>();

		AddressEntity addressEntity = new AddressEntity();

		addressEntity.setLine1(addressRequest.getLine_1());
		addressEntity.setLine2(addressRequest.getLine_2());
		addressEntity.setCity(addressRequest.getCity());
		addressEntity.setPincode(addressRequest.getPincode());

		addressEntities.add(addressEntity);

		userEntity.setAddresses(addressEntities);

		userRepo.save(userEntity);

		return ResponseEntity.ok("New Address Saved!");

	}

	@DeleteMapping("user/{email}/address/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable("email") String email,
			@PathVariable("id") long id) {
		AddressEntity addressEntity = addressRepo.findById(id).get();
		if (addressEntity == null)
			return new ResponseEntity<>("Address id invalid!",
					HttpStatus.NOT_FOUND);

		Set<AddressEntity> addressEntities = userRepo.findByEmail(email)
				.getAddresses();

		if (addressEntities.remove(addressEntity))
			return ResponseEntity.ok("Deleted 1 record");

		else
			return ResponseEntity.ok("Deleted 0 record");

	}

	@PutMapping("user/{email}/address/{id}")
	public ResponseEntity<?> updateAddress(@PathVariable("email") String email,
			@PathVariable("id") long id,
			@RequestBody() AddressRequest addressRequest) {
		AddressEntity addressEntity = addressRepo.findById(id).get();
		if (addressEntity == null)
			return new ResponseEntity<>("Address Id invalid!",
					HttpStatus.NOT_FOUND);

		String line1 = addressRequest.getLine_1();
		String line2 = addressRequest.getLine_2();
		String city = addressRequest.getCity();
		String pincode = addressRequest.getPincode();

		if (line1 != null)
			addressEntity.setLine1(line1);
		if (line2 != null)
			addressEntity.setLine1(line2);
		if (city != null)
			addressEntity.setLine1(city);
		if (pincode != null)
			addressEntity.setLine1(pincode);

		return ResponseEntity.ok(addressRepo.save(addressEntity));

	}

	@GetMapping("user/{email}/booking")
	public ResponseEntity<?> getUserBookings(
			@PathVariable("email") String email) {

		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);

		return ResponseEntity.ok(userEntity.getBookings());

	}

	@PostMapping("user/{email}/booking")
	public ResponseEntity<?> createBooking(@PathVariable("email") String email,
			@RequestBody() BookingCreationRequest bookingCreationRequest) {
		Booking booking = setBooking(bookingCreationRequest);
		Tutor handler = tutorRepo.findFirstByOrderByLastSelectedAsc();
		booking.setHandler(handler);
		UserEntity userEntity = userRepo.findByEmail(email);
		booking.setUser(userEntity);
		Invoice invoice = new Invoice();
		invoice.setAmount(bookingCreationRequest.getAmount());
		invoice.setMethod(bookingCreationRequest.getMethod());
		invoice.setSource(bookingCreationRequest.getSource());
		booking.setInvoice(invoice);
		booking = bookingRepo.save(booking);
		userEntity.getBookings().add(booking);
		userRepo.save(userEntity);
		handler.getBookings().add(booking);
		tutorRepo.save(handler);
		return ResponseEntity.ok(booking);
	}

	private Booking setBooking(BookingCreationRequest bookingCreationRequest) {
		Booking booking = new Booking();
		booking.setBoard(bookingCreationRequest.getBoard());
		booking.setSubject(bookingCreationRequest.getSubject());
		booking.setTopic(bookingCreationRequest.getTopic());
		booking.setClassNumber(bookingCreationRequest.getClassNumber());
		booking.setLine1(bookingCreationRequest.getLine1());
		booking.setLine2(bookingCreationRequest.getLine2());
		booking.setCity(bookingCreationRequest.getCity());
		booking.setPincode(bookingCreationRequest.getPincode());
		booking.setSchedule(bookingCreationRequest.getSchedule());
		booking.setDeadline(
				new Date(bookingCreationRequest.getSchedule().getTime()
						- 1000 * 60 * 60 * 3));
		booking.setSecret(RandomString.make(4).toUpperCase());
		return booking;
	}
	
	@DeleteMapping("user/{email}/booking/{id}")
	public ResponseEntity<?> cancelBooking(@PathVariable("email") String email,
			@PathVariable("id") long id, @RequestBody() CancellationRequest cancellationRequest) {
		Booking booking = bookingRepo.findById(id).get();
		if(booking == null) 
			return new ResponseEntity<>("booking not found!", HttpStatus.NOT_FOUND);
		booking.setReason(cancellationRequest.getReason());
		booking.setStatus("cancelled");
		return ResponseEntity.ok(bookingRepo.save(booking));	
	}
	
	@PutMapping("user/{email}/booking/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable("email") String email,
			@PathVariable("id") long id, @RequestBody() UpdateBookingRequest updateBookingRequest) {
		
		Booking booking = bookingRepo.findById(id).get();
		if(booking == null)
			return new ResponseEntity<>("booking not found!", HttpStatus.NOT_FOUND);
		
		String comment = updateBookingRequest.getComment();
		Integer score = updateBookingRequest.getScore();
		Date date = updateBookingRequest.getNew_date();
		
		if(comment != null)
			booking.setComment(comment);
		if(score != null)
			booking.setScore(score);
		if(date != null) {
			booking.setSchedule(date);
			booking.setDeadline(new Date(date.getTime() - 1000 * 60 * 60 * 3));
		}
		return ResponseEntity.ok(bookingRepo.save(booking));	
		
	}

}
