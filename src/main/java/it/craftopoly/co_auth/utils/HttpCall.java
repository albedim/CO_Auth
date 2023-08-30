package it.craftopoly.co_auth.utils;

import it.craftopoly.co_auth.schema.UserAuth;

public class HttpCall
{
    public static Boolean exists(String username)
    {
        Response response = HttpRequest.get("/users/" + username + "/exists");
        if(response == null)
            return false;
        return Boolean.parseBoolean(response.getParam().toString());
    }

    public static String create(String username, String password)
    {
        Response response = HttpRequest.post("/users/create", new UserAuth(username, password));
        if(response == null)
            return Message.AUTHENTICATION_ERROR;
        if(response.getCode() != 200)
            return Message.AUTHENTICATION_ERROR;
        return Message.AUTHENTICATED;
    }

    public static String login(String username, String password)
    {
        Response response = HttpRequest.post("/users/signin", new UserAuth(username, password));
        if(response == null)
            return Message.AUTHENTICATION_ERROR;
        if(response.getCode() != 200)
            return Message.AUTHENTICATION_FAILED;
        return Message.AUTHENTICATED;
    }
}

