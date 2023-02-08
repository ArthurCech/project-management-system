package com.example.makalu.service.validation;

import com.example.makalu.controller.exception.FieldMessage;
import com.example.makalu.domain.User;
import com.example.makalu.dto.user.UserUpdatePayload;
import com.example.makalu.repository.UserRepository;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdatePayload> {

    private final HttpServletRequest request;
    private final UserRepository userRepository;

    public UserUpdateValidator(HttpServletRequest request,
                               UserRepository userRepository) {
        this.request = request;
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdatePayload payload, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        Map<String, String> uriVars = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> fieldsMessage = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByEmail(payload.email());
        if (userOptional.isPresent() && userId != userOptional.get().getId()) {
            fieldsMessage.add(new FieldMessage("email", "User already registered"));
        }
        for (FieldMessage f : fieldsMessage) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.message())
                    .addPropertyNode(f.fieldName())
                    .addConstraintViolation();
        }
        return fieldsMessage.isEmpty();
    }

}
