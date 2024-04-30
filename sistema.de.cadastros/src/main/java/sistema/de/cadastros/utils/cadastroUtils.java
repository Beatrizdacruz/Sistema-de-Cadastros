package sistema.de.cadastros.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class cadastroUtils {

    private cadastroUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus HttpStatus){
        return new ResponseEntity<String>("{\"message\" : \""+responseMessage+"\"}", HttpStatus);
    }
}
