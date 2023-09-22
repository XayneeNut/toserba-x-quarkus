package org.gusanta.toserba.exception;

import lombok.Getter;

public enum ExceptionCode {
	F_NV("Form not Valid"),
	CFG_R("Config is required"),
	AUTH_PROBLEM("Authentication Problem, common is token expired"),
	CFG_VALUE_NO_VALID("Config Value Not Valid"),
	JWT_PAYLOAD_NO_VALID("Jwt payload is not valid"),
	JWT_KEY_ID_NOT_FOUND("Jwt KeyId not found"),
	JWT_UNAUTHENTICATION("Jwt is not permit"),
	JWT_IS_EXPIRED("Jwt is expired"),
	JWT_SIG_NOT_VALID("Jwt signature not valid"),
	//---------- HANYA MODIF DIBAGIAN BAWAH INI
	CONTACT_BAD_DATA("Contact has bad data, please fix it first"),
	CTT_NF("Contact Type not found / active"),
	CNT_NF("Contact Not Found"),
	CONTACT_LINK_OCCUPIED("Contact Link Already Occupied"),
	LEGACY_NOT_FOUND("Legacy not found"),
	CONTACT_AND_TYPE_NOT_LINKED("contact and type not linked"),
	GROUP_NOT_FOUND("Group not found");

	@Getter
	private final String message;

	ExceptionCode(String message) {
		this.message = message;
	}
	
}
