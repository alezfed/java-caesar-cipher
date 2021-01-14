package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Cipher;
import com.epam.izh.rd.online.entity.Text;

import java.util.Locale;

public class CryptoService {

    public String transformString(String originalString, String charSetInString, int key) {
        char[] charSet = charSetInString.toCharArray();
        int capacity = charSet.length;
        char[] convertedChars = originalString.toCharArray();
        for (int i = 0; i < convertedChars.length; i++) {
            for (int j = 0; j < charSet.length; j++) {
                if (convertedChars[i] == charSet[j]) {
                    convertedChars[i] = charSet[(j + capacity + key) % capacity];
                    break;
                }
            }
        }
        return new String(convertedChars);
    }

    public Text transformText(Text originalText) {
        Text convertedText = new Text(originalText);
        Cipher cipher = originalText.getCipher();
        String convertedString = transformString(convertedText.getBeforeContent(), cipher.getCharSet(), cipher.getKey());
        convertedText.setAfterContent(transformString(convertedString, cipher.getCharSet().toUpperCase(Locale.ROOT)
                , cipher.getKey()));
        return convertedText;
    }
}