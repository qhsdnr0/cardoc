package Cardoc.cardoc.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@NoArgsConstructor
public class UnauthorizedException extends RuntimeException{
}
