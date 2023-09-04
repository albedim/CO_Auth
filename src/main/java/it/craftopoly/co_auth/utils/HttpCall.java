package it.craftopoly.co_auth.utils;

import com.google.gson.JsonObject;
import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.schema.UserRegisterSchema;
import it.craftopoly.co_auth.schema.UserSigninSchema;

public class HttpCall
{
    public static Boolean exists(String username)
    {
        JsonObject response = HttpUtils.get(
                "/users/" + username + "/exists",
                null,
                JsonObject.class
        ).getAsJsonObject();

        return response.get("param").getAsBoolean();
    }

    public static JsonObject getBan(String username)
    {
        JsonObject response = HttpUtils.get(
                "/bans/user/" + username,
                null,
                JsonObject.class
        ).getAsJsonObject();

        if(response.get("code").getAsInt() != 200)
           return null;

        return response.get("param").getAsJsonObject();
    }

    public static String create(String uuid, String username, String password)
    {
        JsonObject response = HttpUtils.post(
                "/users/create",
                null,
                new UserRegisterSchema(uuid, username, password),
                JsonObject.class
        ).getAsJsonObject();

        if(response.get("code").getAsInt() != 200)
            return CO_Auth.getInstance().getConfig().getString("messages.authentication_error");
        return CO_Auth.getInstance().getConfig().getString("messages.authenticated");
    }

    public static String login(String username, String password)
    {
        JsonObject response = HttpUtils.post(
                "/users/signin",
                null,
                new UserSigninSchema(username, password),
                JsonObject.class
        ).getAsJsonObject();

        if(response.get("code").getAsInt() != 200)
            return CO_Auth.getInstance().getConfig().getString("messages.authentication_failed");
        return CO_Auth.getInstance().getConfig().getString("messages.authenticated");
    }
}

