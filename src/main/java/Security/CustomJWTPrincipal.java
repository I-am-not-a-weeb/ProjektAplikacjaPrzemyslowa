package Security;

import java.util.Collection;
import java.util.List;

public class CustomJWTPrincipal {
    private String username;
    private String email;
    private boolean role;


    public CustomJWTPrincipal(String username, String email, boolean role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean getRole() {
        return role;
    }

    public Collection asColletion()
    {
        return List.of(username, email,role);
    }
}
