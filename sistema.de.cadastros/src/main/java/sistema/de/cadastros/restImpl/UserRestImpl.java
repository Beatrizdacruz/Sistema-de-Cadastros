package sistema.de.cadastros.restImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sistema.de.cadastros.constents.cadastroConstants;
import sistema.de.cadastros.rest.UserRest;
import sistema.de.cadastros.service.UserService;
import sistema.de.cadastros.utils.cadastroUtils;

import java.util.Map;

@RestController

public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return cadastroUtils.getResponseEntity(cadastroConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        //return new ResponseEntity<String>("{\"message\" : \"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}