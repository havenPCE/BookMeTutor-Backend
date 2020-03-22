package com.pce.BookMeTutor.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pce.BookMeTutor.Config.Constants;
import com.pce.BookMeTutor.Model.Dao.Booking;
import com.pce.BookMeTutor.Model.Dao.Invoice;
import com.pce.BookMeTutor.Model.Dao.Tutor;
import com.pce.BookMeTutor.Model.Dao.UserEntity;
import com.pce.BookMeTutor.Model.Dto.Requests.CompletionRequest;
import com.pce.BookMeTutor.Model.Dto.Responses.BookingResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.InvoiceResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.MessageResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.TutorDetailsResponse;
import com.pce.BookMeTutor.Repo.BookingRepo;
import com.pce.BookMeTutor.Repo.TutorRepo;
import com.pce.BookMeTutor.Repo.UserRepo;
import com.pce.BookMeTutor.Services.EmailService;

@RestController
@CrossOrigin
public class TutorController {

	@Autowired
	EmailService emailService;

	@Autowired
	TutorRepo tutorRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	BookingRepo bookingRepo;

	@GetMapping("/tutor")
	public ResponseEntity<?> getAllTutors() {
		List<Tutor> tutors = tutorRepo.findAll();

		List<TutorDetailsResponse> tutorDetailsResponses = new ArrayList<TutorDetailsResponse>();

		tutors.forEach(tutor -> {
			TutorDetailsResponse tutorDetailsResponse = new TutorDetailsResponse();
			tutorDetailsResponse.setEmail(tutor.getEmail());
			tutorDetailsResponse.setFirst_name(tutor.getFname());
			tutorDetailsResponse.setLast_name(tutor.getLname());
			tutorDetailsResponse.setGender(tutor.getGender());
			tutorDetailsResponse.setLine1(tutor.getLine1());
			tutorDetailsResponse.setLine2(tutor.getLine2());
			tutorDetailsResponse.setCity(tutor.getCity());
			tutorDetailsResponse.setPincode(tutor.getPincode());
			tutorDetailsResponse.setQualification(tutor.getQualification());
			tutorDetailsResponse.setScreening(tutor.getScreening());
			tutorDetailsResponse.setLastSelected(tutor.getLastSelected());
			List<BookingResponse> bookingResponses = new ArrayList<BookingResponse>();
			tutor.getBookings().forEach(booking -> {
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
			tutorDetailsResponse.setBookingResponses(bookingResponses);
			tutorDetailsResponses.add(tutorDetailsResponse);
		});

		return ResponseEntity.ok(tutorDetailsResponses);
	}

	@GetMapping("tutor/{email}")
	public ResponseEntity<?> getTutor(@PathVariable("email") String email) {
		Tutor tutor = tutorRepo.findByEmail(email);
		if (tutor == null)
			return new ResponseEntity<>(Constants.TUTOR_NOT_FOUND,
					HttpStatus.NOT_FOUND);
		TutorDetailsResponse tutorDetailsResponse = new TutorDetailsResponse();
		tutorDetailsResponse.setEmail(tutor.getEmail());
		tutorDetailsResponse.setFirst_name(tutor.getFname());
		tutorDetailsResponse.setLast_name(tutor.getLname());
		tutorDetailsResponse.setGender(tutor.getGender());
		tutorDetailsResponse.setLine1(tutor.getLine1());
		tutorDetailsResponse.setLine2(tutor.getLine2());
		tutorDetailsResponse.setCity(tutor.getCity());
		tutorDetailsResponse.setPincode(tutor.getPincode());
		tutorDetailsResponse.setQualification(tutor.getQualification());
		tutorDetailsResponse.setScreening(tutor.getScreening());
		tutorDetailsResponse.setLastSelected(tutor.getLastSelected());
		List<BookingResponse> bookingResponses = new ArrayList<BookingResponse>();
		tutor.getBookings().forEach(booking -> {
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
		tutorDetailsResponse.setBookingResponses(bookingResponses);
		return ResponseEntity.ok(tutorDetailsResponse);
	}

	@DeleteMapping("/tutor/{email}")
	public ResponseEntity<?> deleteTutor(@PathVariable("email") String email) {
		Tutor tutor = tutorRepo.findByEmail(email);
		if (tutor == null)
			return new ResponseEntity<>(Constants.TUTOR_NOT_FOUND,
					HttpStatus.NOT_FOUND);
		tutor.setVerified(false);
		tutorRepo.save(tutor);
		return ResponseEntity.ok(new MessageResponse("tutor deactivated"));
	}

	@GetMapping("/tutor/{email}/booking")
	public ResponseEntity<?> getTutorBooking(
			@PathVariable("email") String email) {
		Tutor tutor = tutorRepo.findByEmail(email);
		if (tutor == null)
			return new ResponseEntity<>(Constants.TUTOR_NOT_FOUND,
					HttpStatus.NOT_FOUND);
		List<BookingResponse> bookingResponses = new ArrayList<BookingResponse>();
		tutor.getBookings().forEach(booking -> {
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

	@GetMapping("/tutor/{email}/booking/{id}/accept")
	public ResponseEntity<?> acceptBooking(@PathVariable("email") String email,
			@PathVariable("id") long id)
			throws MessagingException, IOException {
		Booking booking = bookingRepo.findById(id).get();
		if (booking == null)
			return new ResponseEntity<>(Constants.BOOKING_NOT_FOUND,
					HttpStatus.NOT_FOUND);
		booking.setStatus("assigned");
		bookingRepo.save(booking);
		sendEmailAccept(booking.getUser(), booking.getHandler());
		return ResponseEntity.ok(new MessageResponse("booking added"));
	}

	private void sendEmailAccept(UserEntity userEntity, Tutor tutor)
			throws MessagingException, IOException {
		String tutorEmail = tutor.getEmail();
		String subject = "Notice for your Booking";
		String userName = userEntity.getFname();
		String userEmail = userEntity.getEmail();
		emailService.sendMail(tutorEmail, subject,
				"Thank you for accepting booking," + tutor.getFname());
		emailService.sendMail(userEmail, subject, "<h4>Hi, " + userName
				+ ".\nA tutor has been assigned for your request. Please be ready to receive Mr/Ms."
				+ tutor.getFname() + " " + tutor.getLname() + ".</h4>");
	}

	@GetMapping("/tutor/{email}/booking/{id}/reject")
	public ResponseEntity<?> rejectBooking(@PathVariable("email") String email,
			@PathVariable("id") long id)
			throws MessagingException, IOException {
		Booking booking = bookingRepo.findById(id).get();
		if (booking == null)
			return new ResponseEntity<>(Constants.BOOKING_NOT_FOUND,
					HttpStatus.NOT_FOUND);
		Tutor tutor = tutorRepo.findFirstByOrderByLastSelectedAsc();
		if (tutor.getEmail().equals(booking.getHandler().getEmail()))
			return new ResponseEntity<>(Constants.TUTOR_NOT_FOUND,
					HttpStatus.EXPECTATION_FAILED);
		booking.getHandler().getBookings().remove(booking);
		booking.setHandler(tutor);
		bookingRepo.save(booking);
		Set<Booking> bookings = tutor.getBookings();
		bookings.add(booking);
		tutor.setBookings(bookings);
		tutorRepo.save(tutor);
		sendEmailReject(tutor);
		return ResponseEntity.ok(new MessageResponse(
				"Booking assigned to : " + tutor.getFname() + " "
						+ tutor.getLname() + "(" + tutor.getEmail() + ")"));
	}

	private void sendEmailReject(Tutor tutor)
			throws MessagingException, IOException {
		String tutorEmail = tutor.getEmail();
		String subject = "Notice for a new Booking";
		emailService.sendMail(tutorEmail, subject,
				"<h4>A new Booking has been to you," + tutor.getFname()
						+ ".\nPlease review it urgently on yout dashboard.</h4>");
	}

	@PostMapping("/tutor/{email}/booking/{id}/complete")
	public ResponseEntity<?> completeBooking(
			@PathVariable("email") String email, @PathVariable("id") long id,
			@RequestBody() CompletionRequest completionRequest) {
		Booking booking = bookingRepo.findById(id).get();
		if (booking == null)
			return new ResponseEntity<>(Constants.BOOKING_NOT_FOUND,
					HttpStatus.NOT_FOUND);
		Date currentTime = new Date(System.currentTimeMillis());

		if (completionRequest.getSecret().equals(booking.getSecret())) {
			if (currentTime.before(booking.getSchedule()))
				return new ResponseEntity<>("session is not finished",
						HttpStatus.NOT_ACCEPTABLE);
			booking.setStatus("completed");
			bookingRepo.save(booking);
			return ResponseEntity.ok(new MessageResponse("task completed"));
		}

		return new ResponseEntity<>(Constants.INVALID_REQUEST,
				HttpStatus.BAD_REQUEST);
	}
}
