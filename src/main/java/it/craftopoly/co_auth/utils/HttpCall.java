package it.craftopoly.co_auth.utils;

import it.craftopoly.co_auth.CO_Auth;
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
            return CO_Auth.getInstance().getConfig().getString("messages.authentication_error");
        if(response.getCode() != 200)
            return CO_Auth.getInstance().getConfig().getString("messages.authentication_error");
        return CO_Auth.getInstance().getConfig().getString("messages.authenticated");
    }

    public static String login(String username, String password)
    {
        Response response = HttpRequest.post("/users/signin", new UserAuth(username, password));
        if(response == null)
            return CO_Auth.getInstance().getConfig().getString("messages.authentication_error");
        if(response.getCode() != 200)
            return CO_Auth.getInstance().getConfig().getString("messages.authentication_failed");
        return CO_Auth.getInstance().getConfig().getString("messages.authenticated");
    }
}

