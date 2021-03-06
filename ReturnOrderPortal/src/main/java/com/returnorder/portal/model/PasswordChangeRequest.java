package com.returnorder.portal.model;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PasswordChangeRequest {
	private String username;
	private String oldPassword;
	private String newPassword;
}
