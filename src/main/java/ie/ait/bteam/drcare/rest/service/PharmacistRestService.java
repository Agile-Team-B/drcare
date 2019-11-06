package ie.ait.bteam.drcare.rest.service;

import ie.ait.bteam.drcare.data.model.User;
import ie.ait.bteam.drcare.data.service.UserService;
import ie.ait.bteam.drcare.rest.dto.UserDTO;
import ie.ait.bteam.drcare.rest.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class PharmacistRestService {

    private UserService userService;
    private Translator<User, UserDTO> userTranslator;

    @Autowired
    public PharmacistRestService(UserService userService, Translator<User, UserDTO> userTranslator) {
        this.userService = userService;
        this.userTranslator = userTranslator;
    }

    public UserDTO createPharmacist(UserDTO pharmacist, BindingResult result) {
        User createdPharmacist = userTranslator.translateTo(pharmacist);
        createdPharmacist = userService.createUser(createdPharmacist, result);

        return userTranslator.translateFrom(createdPharmacist);
    }

}