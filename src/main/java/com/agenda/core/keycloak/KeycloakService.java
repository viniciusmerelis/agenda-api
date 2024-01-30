package com.agenda.core.keycloak;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@Service
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("keycloak.resource")
    private String resource;

    public void createUser(User user) {
        int statusId = 0;
        try {
            UsersResource usersResource = getUsersResource();
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(user.getUsername());
            userRepresentation.setEmail(user.getEmail());
            userRepresentation.setFirstName(user.getFirstName());
            userRepresentation.setLastName(user.getLastName());
            userRepresentation.setEnabled(true);

            Response result = usersResource.create(userRepresentation);
            statusId = result.getStatus();

            if (statusId == 201) {
                String path = result.getLocation().getPath();
                String userId = path.substring(path.indexOf("/") + 1);
                CredentialRepresentation passwordCredential = new CredentialRepresentation();
                passwordCredential.setTemporary(false);
                passwordCredential.setType(CredentialRepresentation.PASSWORD);
                passwordCredential.setValue(user.getPassword());
                usersResource.get(userId).resetPassword(passwordCredential);

                RealmResource realmResource = getRealmResource();
                RoleRepresentation roleRepresentation = realmResource.roles().get("user").toRepresentation();
                realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

            } else if (statusId == 409) {
                //TODO: Lançar exceção (Usuario já existe)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UsersResource getUsersResource() {
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }

    private RealmResource getRealmResource() {
        Keycloak keycloak = KeycloakBuilder.builder()
             .serverUrl(serverUrl)
             .grantType(OAuth2Constants.PASSWORD)
             .realm(realm)
             .clientId(resource)
             .username("user")
             .password("password")
             .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
             .build();

        keycloak.tokenManager().getAccessToken();
        RealmResource realmResource = keycloak.realm("realm-name");

        return keycloak.realm(realm);
    }
}
