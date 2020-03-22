package com.pce.BookMeTutor.Config;

public class Constants {

	public static final String SECRET = "secretToEncrypt";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_FIELD_STRING = "Authorization";
	public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 8;
	public static final String BACKEND_URL = "http://bookmetutor-backend.ap-southeast-1.elasticbeanstalk.com/";

	public static final String USER_NOT_FOUND = "user not found";
	public static final String TUTOR_NOT_FOUND = "tutor not found";
	public static final String BOOKING_NOT_FOUND = "booking not found";
	public static final String ADDRESS_NOT_FOUND = "addresss id invalid";
	public static final String USER_EXISTS = "email id taken";
	public static final String SUBJECT_NOT_FOUND = "subject not found";
	public static final String INVALID_TOKEN = "invalid token or token expired";
	public static final String INVALID_REQUEST = "role is mandatory";
}
