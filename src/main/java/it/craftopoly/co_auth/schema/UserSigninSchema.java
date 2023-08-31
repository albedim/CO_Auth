package it.craftopoly.co_auth.schema;

public class UserSigninSchema
{
    private String username;
    private String password;

    public UserSigninSchema(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}
