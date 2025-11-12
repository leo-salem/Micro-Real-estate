package microRealestate.example.user.service.keycloakAdmin;


import microRealestate.example.user.dto.request.RegisterUserRequest;

import java.util.Map;

public interface KeycloakAdminService {
    String createUser(String token, RegisterUserRequest userRequest);
    String getAdminAccessToken();
    Map<String, Object> getClientRoleRepresentation(String token, String roleName);
    void assignClientRoleToUser(String userId, String roleName);
    Map<String, Object> getRealmRoleRepresentation(String token, String roleName);
    void assignRealmRoleToUser(String userId, String roleName);
}
