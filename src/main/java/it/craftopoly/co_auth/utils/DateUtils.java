package it.craftopoly.co_auth.utils;

public class DateUtils
{
    public static String fixDate(String date)
    {
        String[] dates = date.split("-");
        return dates[2] + "/" + dates[1] + "/" + dates[0];
    }
}
