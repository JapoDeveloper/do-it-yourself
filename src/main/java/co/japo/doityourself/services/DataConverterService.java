package co.japo.doityourself.services;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.Base64;

@Service
public class DataConverterService {

    public String getBase64(byte[] bytes){
        return "data:image/png;base64,"+Base64.getEncoder().encodeToString(bytes);
    }

}