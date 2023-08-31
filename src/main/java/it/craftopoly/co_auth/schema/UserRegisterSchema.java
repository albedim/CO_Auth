package it.craftopoly.co_auth.schema;

public class UserRegisterSchema
{
    private String username;
    private String password;
    private String uuid;

    public UserRegisterSchema(String uuid, String username, String password)
    {
        this.username = username;
        this.uuid = uuid;
        this.password = password;
    }
}
