package it.craftopoly.co_auth.utils;

import com.google.gson.JsonObject;
import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.listener.SyncSchema;
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

    public static String generateTelegramCode(String uuid)
    {
        JsonObject response = HttpUtils.put(
                "/users/telegram/generate",
                uuid,
                null,
                JsonObject.class
        ).getAsJsonObject();

        if(response.get("code").getAsInt() != 200 &&
                response.get("code").getAsInt() != 409) {
            return CO_Auth.getInstance().getConfig().getString("messages.telegram_error");
        }

        if(response.get("code").getAsInt() == 409)
            return CO_Auth.getInstance().getConfig().getString("messages.telegram_already_connected");

        return CO_Auth.getInstance().getConfig().getString("messages.telegram_connect")
                .replace("{code}", response.get("param").getAsString());
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

    public static void sync(String uuid, String username)
    {
        for(int i = 0; i < 3; i++)
            HttpUtils.post(
                    "/users/sync",
                    uuid,
                    new SyncSchema(username),
                    JsonObject.class
            );
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

