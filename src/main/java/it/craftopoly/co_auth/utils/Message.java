package it.craftopoly.co_auth.utils;

public final class Message
{
    public static final String AUTHENTICATE =
            "§a§lAutenticazione\n\n§8· §7Benvenuto §a{username}§7, Crea il tuo " +
                    "§aID§7 personale\nper iniziare a giocare\n\n  §7/register <password>";

    public static final String AUTHENTICATED =
            "§aAutenticazione avvenuta con successo.";

    public static final String PREMIUM_AUTHENTICATED =
            "§aAutenticazione automatica avvenuta con successo.";

    public static final String AUTHENTICATION_ERROR =
            "§cErrore durante l'autenticazione.";

    public static final String ALREADY_AUTHENTICATED =
            "§cSei già stato autenticato.";

    public static final String AUTHENTICATION_FAILED =
            "§cPassword sbagliata, riprova.";

    public static final String LOGIN =
            "§a§lAutenticazione\n\n§8· §7Bentornato §a{username}§7, Accedi con la\n  tua password" +
                    "per iniziare a giocare\n\n  §7/login <password>";
}
