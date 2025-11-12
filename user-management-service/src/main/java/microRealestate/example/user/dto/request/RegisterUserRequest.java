package microRealestate.example.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;  // unique
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
    // Payment info if needed for Stripe
    private String paymentAccountId;
}
