package com.pce.BookMeTutor.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

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
import com.pce.BookMeTutor.Model.Dto.Requests.PhoneRequest;
import com.pce.BookMeTutor.Model.Dto.Requests.UpdateBookingRequest;
import com.pce.BookMeTutor.Model.Dto.Responses.AddressResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.BookingResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.InvoiceResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.UserDetailsResponse;
import com.pce.BookMeTutor.Repo.AddressRepo;
import com.pce.BookMeTutor.Repo.BookingRepo;
import com.pce.BookMeTutor.Repo.TutorRepo;
import com.pce.BookMeTutor.Repo.UserRepo;
import com.pce.BookMeTutor.Services.EmailService;

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
	
	@Autowired
	EmailService emailService;

	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {

		List<UserEntity> usersEntities = userRepo.findAll();

		List<UserDetailsResponse> userDetailsResponses = new ArrayList<UserDetailsResponse>();

		usersEntities.forEach(user -> {
			UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
			userDetailsResponse.setEmail(user.getEmail());
			userDetailsResponse.setFirst_name(user.getFname());
			userDetailsResponse.setLast_name(user.getLname());
			userDetailsResponse.setGender(user.getGender());
			List<AddressResponse> addressResponses = new ArrayList<AddressResponse>();
			user.getAddresses().forEach(address -> {
				AddressResponse addressResponse = new AddressResponse();
				addressResponse.setId(address.getId());
				addressResponse.setLine_1(address.getLine1());
				addressResponse.setLine_2(address.getLine2());
				addressResponse.setCity(address.getCity());
				addressResponse.setPincode(address.getPincode());
				addressResponses.add(addressResponse);
			});
			userDetailsResponse.setAddressResponses(addressResponses);

			List<String> phonesList = new ArrayList<String>();

			user.getPhones().forEach(phone -> phonesList.add(phone));

			userDetailsResponse.setPhones(phonesList);

			List<BookingResponse> bookingResponses = new ArrayList<BookingResponse>();
			user.getBookings().forEach(booking -> {
				BookingResponse bookingResponse = new BookingResponse();
				bookingResponse.setId(booking.getId());
				bookingResponse.setBoard(booking.getBoard());
				bookingResponse.setClassNumber(booking.getClassNumber());
				bookingResponse.setSubject(booking.getSubject());
				bookingResponse.setTopic(booking.getTopic());
				bookingResponse.setLine1(booking.getLine1());
				bookingResponse.setLine2(booking.getLine2());
				bookingResponse.setCity(booking.getCity());
				bookingResponse.setPincode(booking.getPincode());
				bookingResponse.setComment(booking.getComment());
				bookingResponse.setDeadline(booking.getDeadline());
				bookingResponse.setHandler(booking.getHandler().getFname() + " "
						+ booking.getHandler().getLname());
				bookingResponse.setReason(booking.getReason());
				bookingResponse.setRescheduled(booking.isRescheduled());
				bookingResponse.setSchedule(booking.getSchedule());
				bookingResponse.setScore(booking.getScore());
				bookingResponse.setSecret(booking.getSecret());
				bookingResponse.setStatus(booking.getStatus());
				bookingResponse.setStudent(booking.getUser().getFname() + " "
						+ booking.getUser().getLname());
				InvoiceResponse invoiceResponse = new InvoiceResponse();
				Invoice invoice = booking.getInvoice();
				invoiceResponse.setId(invoice.getId());
				invoiceResponse.setAmount(invoice.getAmount());
				invoiceResponse.setMethod(invoice.getMethod());
				bookingResponse.setInvoiceResponse(invoiceResponse);
				bookingResponses.add(bookingResponse);
			});
			userDetailsResponse.setBookingResponses(bookingResponses);
			userDetailsResponses.add(userDetailsResponse);
		});

		return ResponseEntity.ok(userDetailsResponses);
	}

	@DeleteMapping("/user/{email}")
	public ResponseEntity<?> deleteUser(@PathVariable("email") String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);
		userEntity.setVerified(false);
		userRepo.save(userEntity);
		return ResponseEntity.ok("User Deleted!");
	}

	@GetMapping("/user/{email}")
	public ResponseEntity<?> getUser(@PathVariable("email") String email) {
		UserEntity user = userRepo.findByEmail(email);
		if (user == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);

		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setEmail(user.getEmail());
		userDetailsResponse.setFirst_name(user.getFname());
		userDetailsResponse.setLast_name(user.getLname());
		userDetailsResponse.setGender(user.getGender());
		List<AddressResponse> addressResponses = new ArrayList<AddressResponse>();
		user.getAddresses().forEach(address -> {
			AddressResponse addressResponse = new AddressResponse();
			addressResponse.setId(address.getId());
			addressResponse.setLine_1(address.getLine1());
			addressResponse.setLine_2(address.getLine2());
			addressResponse.setCity(address.getCity());
			addressResponse.setPincode(address.getPincode());
			addressResponses.add(addressResponse);
		});
		userDetailsResponse.setAddressResponses(addressResponses);

		List<String> phonesList = new ArrayList<String>();

		user.getPhones().forEach(phone -> phonesList.add(phone));

		userDetailsResponse.setPhones(phonesList);

		List<BookingResponse> bookingResponses = new ArrayList<BookingResponse>();
		user.getBookings().forEach(booking -> {
			BookingResponse bookingResponse = new BookingResponse();
			bookingResponse.setId(booking.getId());
			bookingResponse.setBoard(booking.getBoard());
			bookingResponse.setClassNumber(booking.getClassNumber());
			bookingResponse.setSubject(booking.getSubject());
			bookingResponse.setTopic(booking.getTopic());
			bookingResponse.setLine1(booking.getLine1());
			bookingResponse.setLine2(booking.getLine2());
			bookingResponse.setCity(booking.getCity());
			bookingResponse.setPincode(booking.getPincode());
			bookingResponse.setComment(booking.getComment());
			bookingResponse.setDeadline(booking.getDeadline());
			bookingResponse.setHandler(booking.getHandler().getFname() + " "
					+ booking.getHandler().getLname());
			bookingResponse.setReason(booking.getReason());
			bookingResponse.setRescheduled(booking.isRescheduled());
			bookingResponse.setSchedule(booking.getSchedule());
			bookingResponse.setScore(booking.getScore());
			bookingResponse.setSecret(booking.getSecret());
			bookingResponse.setStatus(booking.getStatus());
			bookingResponse.setStudent(booking.getUser().getFname() + " "
					+ booking.getUser().getLname());
			InvoiceResponse invoiceResponse = new InvoiceResponse();
			Invoice invoice = booking.getInvoice();
			invoiceResponse.setId(invoice.getId());
			invoiceResponse.setAmount(invoice.getAmount());
			invoiceResponse.setMethod(invoice.getMethod());
			bookingResponse.setInvoiceResponse(invoiceResponse);
			bookingResponses.add(bookingResponse);
		});
		userDetailsResponse.setBookingResponses(bookingResponses);

		return ResponseEntity.ok(userDetailsResponse);

	}

	@GetMapping("user/{email}/address")
	public ResponseEntity<?> getUserAddress(
			@PathVariable("email") String email) {
		UserEntity user = userRepo.findByEmail(email);
		if (user == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);
		List<AddressResponse> addressResponses = new ArrayList<AddressResponse>();
		user.getAddresses().forEach(address -> {
			AddressResponse addressResponse = new AddressResponse();
			addressResponse.setId(address.getId());
			addressResponse.setLine_1(address.getLine1());
			addressResponse.setLine_2(address.getLine2());
			addressResponse.setCity(address.getCity());
			addressResponse.setPincode(address.getPincode());
			addressResponses.add(addressResponse);
		});

		return ResponseEntity.ok(addressResponses);
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
		addressEntity.setUser(userEntity);

		addressEntity = addressRepo.save(addressEntity);

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

		boolean removed = userRepo.findByEmail(email).getAddresses()
				.remove(addressEntity);

		addressRepo.delete(addressEntity);

		if (removed)
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

		return ResponseEntity.ok("address updated!");

	}

	@GetMapping("user/{email}/booking")
	public ResponseEntity<?> getUserBookings(
			@PathVariable("email") String email) {

		UserEntity user = userRepo.findByEmail(email);
		if (user == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);

		List<BookingResponse> bookingResponses = new ArrayList<BookingResponse>();
		user.getBookings().forEach(booking -> {
			BookingResponse bookingResponse = new BookingResponse();
			bookingResponse.setId(booking.getId());
			bookingResponse.setBoard(booking.getBoard());
			bookingResponse.setClassNumber(booking.getClassNumber());
			bookingResponse.setSubject(booking.getSubject());
			bookingResponse.setTopic(booking.getTopic());
			bookingResponse.setLine1(booking.getLine1());
			bookingResponse.setLine2(booking.getLine2());
			bookingResponse.setCity(booking.getCity());
			bookingResponse.setPincode(booking.getPincode());
			bookingResponse.setComment(booking.getComment());
			bookingResponse.setDeadline(booking.getDeadline());
			bookingResponse.setHandler(booking.getHandler().getFname() + " "
					+ booking.getHandler().getLname());
			bookingResponse.setReason(booking.getReason());
			bookingResponse.setRescheduled(booking.isRescheduled());
			bookingResponse.setSchedule(booking.getSchedule());
			bookingResponse.setScore(booking.getScore());
			bookingResponse.setSecret(booking.getSecret());
			bookingResponse.setStatus(booking.getStatus());
			bookingResponse.setStudent(booking.getUser().getFname() + " "
					+ booking.getUser().getLname());
			InvoiceResponse invoiceResponse = new InvoiceResponse();
			Invoice invoice = booking.getInvoice();
			invoiceResponse.setId(invoice.getId());
			invoiceResponse.setAmount(invoice.getAmount());
			invoiceResponse.setMethod(invoice.getMethod());
			bookingResponse.setInvoiceResponse(invoiceResponse);
			bookingResponses.add(bookingResponse);
		});

		return ResponseEntity.ok(bookingResponses);

	}

	@PostMapping("user/{email}/booking")
	public ResponseEntity<?> createBooking(@PathVariable("email") String email,
			@RequestBody() BookingCreationRequest bookingCreationRequest) throws MessagingException, IOException {
		Booking booking = setBooking(bookingCreationRequest);
		Tutor handler = tutorRepo.findFirstByOrderByLastSelectedAsc();
		booking.setHandler(handler);
		handler.setLastSelected(new Date(System.currentTimeMillis()));
		UserEntity userEntity = userRepo.findByEmail(email);
		booking.setUser(userEntity);
		Invoice invoice = new Invoice();
		invoice.setAmount(bookingCreationRequest.getAmount());
		invoice.setMethod(bookingCreationRequest.getMethod());
		booking.setInvoice(invoice);
		booking = bookingRepo.save(booking);
		userEntity.getBookings().add(booking);
		userRepo.save(userEntity);
		handler.getBookings().add(booking);
		tutorRepo.save(handler);
		sendEmail(userEntity, handler);
		return ResponseEntity.ok("Booking id : " + booking.getId());
	}
	
	private void sendEmail(UserEntity userEntity, Tutor tutor) throws MessagingException, IOException {
		String tutorEmail = tutor.getEmail();
		String subject = "Notice for new Booking";
		String userName = userEntity.getFname();
		String userEmail = userEntity.getEmail();
		emailService.sendMail(tutorEmail, subject, "<h4>A new Booking has been to you,"
				+ tutor.getFname() + ".\nPlease review it urgently on yout dashboard.</h4>");
		emailService.sendMail(userEmail, subject, "<h4>Thank you for choosing us, "
				+ userName +".\nPlease check your bookings page for further info and SECRET to handle session.</h4>");
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
			@PathVariable("id") long id,
			@RequestBody() CancellationRequest cancellationRequest) {
		Booking booking = bookingRepo.findById(id).get();
		if (booking == null)
			return new ResponseEntity<>("booking not found!",
					HttpStatus.NOT_FOUND);
		booking.setReason(cancellationRequest.getReason());
		booking.setStatus("cancelled");
		bookingRepo.save(booking);
		return ResponseEntity.ok("Booking cancelled!");
	}

	@PutMapping("user/{email}/booking/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable("email") String email,
			@PathVariable("id") long id,
			@RequestBody() UpdateBookingRequest updateBookingRequest) {

		Booking booking = bookingRepo.findById(id).get();
		if (booking == null)
			return new ResponseEntity<>("booking not found!",
					HttpStatus.NOT_FOUND);

		String comment = updateBookingRequest.getComment();
		Integer score = updateBookingRequest.getScore();
		Date date = updateBookingRequest.getNew_date();

		if (comment != null)
			booking.setComment(comment);
		if (score != null)
			booking.setScore(score);
		if (date != null) {
			booking.setSchedule(date);
			booking.setDeadline(new Date(date.getTime() - 1000 * 60 * 60 * 3));
		}
		bookingRepo.save(booking);
		return ResponseEntity.ok("booking updated!");

	}

	@GetMapping("user/{email}/booking/{id}")
	public ResponseEntity<?> getBooking(@PathVariable("email") String email,
			@PathVariable("id") long id) {
		Booking booking = bookingRepo.findById(id).get();

		if (booking == null) {
			return new ResponseEntity<>("Booking not found!",
					HttpStatus.NOT_FOUND);
		}
		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setId(booking.getId());
		bookingResponse.setBoard(booking.getBoard());
		bookingResponse.setClassNumber(booking.getClassNumber());
		bookingResponse.setSubject(booking.getSubject());
		bookingResponse.setTopic(booking.getTopic());
		bookingResponse.setLine1(booking.getLine1());
		bookingResponse.setLine2(booking.getLine2());
		bookingResponse.setCity(booking.getCity());
		bookingResponse.setPincode(booking.getPincode());
		bookingResponse.setComment(booking.getComment());
		bookingResponse.setDeadline(booking.getDeadline());
		bookingResponse.setHandler(booking.getHandler().getFname() + " "
				+ booking.getHandler().getLname());
		bookingResponse.setReason(booking.getReason());
		bookingResponse.setRescheduled(booking.isRescheduled());
		bookingResponse.setSchedule(booking.getSchedule());
		bookingResponse.setScore(booking.getScore());
		bookingResponse.setSecret(booking.getSecret());
		bookingResponse.setStatus(booking.getStatus());
		bookingResponse.setStudent(booking.getUser().getFname() + " "
				+ booking.getUser().getLname());
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		Invoice invoice = booking.getInvoice();
		invoiceResponse.setId(invoice.getId());
		invoiceResponse.setAmount(invoice.getAmount());
		invoiceResponse.setMethod(invoice.getMethod());
		bookingResponse.setInvoiceResponse(invoiceResponse);

		return ResponseEntity.ok(bookingResponse);

	}

	@PostMapping("user/{email}/phone")
	public ResponseEntity<?> addPhone(@PathVariable("email") String email,
			@RequestBody() PhoneRequest phoneRequest) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			return new ResponseEntity<>("User not found!",
					HttpStatus.NOT_FOUND);
		Set<String> phonesList = userEntity.getPhones();
		phonesList.add(phoneRequest.getPhone());
		userEntity.setPhones(phonesList);
		userRepo.save(userEntity);
		return ResponseEntity.ok("New Phone Number added!");
	}

}
