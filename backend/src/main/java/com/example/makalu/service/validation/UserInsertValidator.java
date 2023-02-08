package com.example.makalu.service.validation;

import com.example.makalu.controller.exception.FieldMessage;
import com.example.makalu.dto.user.UserCreatePayload;
import com.example.makalu.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserCreatePayload> {

    private final UserRepository userRepository;

    public UserInsertValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserCreatePayload payload, ConstraintValidatorContext context) {
        List<FieldMessage> fieldsMessage = new ArrayList<>();
        userRepository.findByEmail(payload.email()).ifPresent(user -> {
            fieldsMessage.add(new FieldMessage("email", "User already registered"));
        });
        for (FieldMessage f : fieldsMessage) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.message())
                    .addPropertyNode(f.fieldName())
                    .addConstraintViolation();
        }
        return fieldsMessage.isEmpty();
    }

}
