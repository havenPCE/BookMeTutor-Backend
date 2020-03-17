package com.pce.BookMeTutor.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pce.BookMeTutor.Model.Dao.Booking;
import com.pce.BookMeTutor.Model.Dao.Invoice;
import com.pce.BookMeTutor.Model.Dao.Tutor;
import com.pce.BookMeTutor.Model.Dto.Responses.BookingResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.InvoiceResponse;
import com.pce.BookMeTutor.Model.Dto.Responses.TutorDetailsResponse;
import com.pce.BookMeTutor.Repo.BookingRepo;
import com.pce.BookMeTutor.Repo.TutorRepo;
import com.pce.BookMeTutor.Repo.UserRepo;

@RestController
@CrossOrigin
public class TutorController {

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
			return new ResponseEntity<>("Tutor not found",
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
			return new ResponseEntity<>("Tutor not found!",
					HttpStatus.NOT_FOUND);
		tutor.setVerified(false);
		tutorRepo.save(tutor);
		return ResponseEntity.ok("Tutor Deleted!");
	}

	@GetMapping("/tutor/{email}/booking")
	public ResponseEntity<?> getTutorBooking(
			@PathVariable("email") String email) {
		Tutor tutor = tutorRepo.findByEmail(email);
		if (tutor == null)
			return new ResponseEntity<>("Tutor not found!",
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
			@PathVariable("id") long id) {
		Booking booking = bookingRepo.findById(id).get();
		if (booking == null)
			return new ResponseEntity<>("Booking not found!",
					HttpStatus.NOT_FOUND);
		booking.setStatus("Assigned");
		bookingRepo.save(booking);
		return ResponseEntity.ok("booking accepted!");
	}

	@GetMapping("/tutor/{email}/booking/{id}/reject")
	public ResponseEntity<?> rejectBooking(@PathVariable("email") String email,
			@PathVariable("id") long id) {
		Booking booking = bookingRepo.findById(id).get();
		if (booking == null)
			return new ResponseEntity<>("Booking not found!",
					HttpStatus.NOT_FOUND);
		Tutor tutor = tutorRepo.findFirstByOrderByLastSelectedAsc();
		if (tutor.getEmail().equals(booking.getHandler().getEmail()))
			return new ResponseEntity<>("Tutor not available",
					HttpStatus.EXPECTATION_FAILED);
		booking.getHandler().getBookings().remove(booking);
		booking.setHandler(tutor);
		bookingRepo.save(booking);
		Set<Booking> bookings = tutor.getBookings();
		bookings.add(booking);
		tutor.setBookings(bookings);
		tutorRepo.save(tutor);
		return ResponseEntity.ok("Booking assigned to : " + tutor.getFname()
				+ " " + tutor.getLname() + "(" + tutor.getEmail() + ")");
	}
}