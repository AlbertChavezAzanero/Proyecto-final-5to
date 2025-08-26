package pe.leboulevard.demo.domain.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Auth {
    private String accessToken;
    private String refreshToken;
}
