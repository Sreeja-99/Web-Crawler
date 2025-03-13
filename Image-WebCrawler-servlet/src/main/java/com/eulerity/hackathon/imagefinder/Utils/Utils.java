package com.eulerity.hackathon.imagefinder.Utils;

import com.eulerity.hackathon.imagefinder.Exceptions.InvalidUrlException;

public class Utils {

    public static String getDomain(String url) throws InvalidUrlException {

        String domainUrl;

        String[] parts = url.split("/");

        if (url.matches("^.*://.*$")) {

            if (!url.startsWith("https://"))
                throw new InvalidUrlException("Please provide a secure URL");

            domainUrl = parts[2];

        } else {

            if (!url.matches("^[^/\\:]+\\..+(\\..+)?.*"))
                throw new InvalidUrlException();

            domainUrl = parts[0];
        }

        String[] domainParts = domainUrl.split("\\.");

        if (domainParts.length < 2)
            throw new InvalidUrlException();

        if (domainParts.length == 2)
            return domainParts[0];
        else
            return domainParts[1];


    }

    public static String getFullDomain(String url) throws InvalidUrlException {

        String[] parts = url.split("/");

        if (url.matches("^.*://.*$")) {

            if (!url.startsWith("https://"))
                throw new InvalidUrlException("Please provide a secure URL");

            return "https://" + parts[2];

        } else {

            if (!url.matches("^[^/\\:]+\\..+(\\..+)?.*"))
                throw new InvalidUrlException();

            return parts[0];
        }
    }

    public static String getSubDomain(String url) throws InvalidUrlException {

        String[] parts = url.split("/");

        if (url.matches("^.*://.*$")) {

            if (!url.startsWith("https://"))
                throw new InvalidUrlException("Please provide a secure URL");

            if (parts.length == 2)
                return "";

            return parts[2];

        } else {

            if (!url.matches("^[^/\\:]+\\..+(\\..+)?.*"))
                throw new InvalidUrlException();

            StringBuilder sb = new StringBuilder();

            if (parts.length == 1)
                return "";

            for (int i = 1; i < parts.length; i++)
                sb.append(parts[i]).append("/");

            return sb.substring(0, sb.length() - 1);
        }

    }

}
