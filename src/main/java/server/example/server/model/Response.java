package server.example.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    protected LocalDateTime timestam;
    protected  int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String  message;
    protected  String  developerMessage;
    protected Map<?,?> data;


}
