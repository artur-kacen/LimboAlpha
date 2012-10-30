package com.limbo.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.limbo.app.domain.Repair;

@Component
public class RepairValidator implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return Repair.class.isAssignableFrom(clazz);
		// TODO Auto-generated method stub
	}

	
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clientFullName",
				"required.repair_clientFullName", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiptDate",
				"required.repair_receiptDate", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "complains",
				"required.repair_complains", "Field name is required.");

	}

}
