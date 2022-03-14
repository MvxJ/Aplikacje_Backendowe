package com.company;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class ActualAndGlobalTime {

    public static void main(String[] args)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();

        Instant globalDateTime = Instant.now();

        System.out.println("First method: " + dtf.format(localDateTime));
        System.out.println( "First method:: " + globalDateTime );
        System.out.println();

        Date now = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
        System.out.println("Second method: " + now);
        TimeZone.setDefault( TimeZone.getTimeZone("UTC"));
        System.out.println("Second method: " + now);

    }
}
