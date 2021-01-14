package com.epam.izh.rd.online.entity;

import java.util.StringJoiner;

public class Cipher {
    private String charSet;
    private int key;

    public Cipher(String charSet, int key) {
        this.charSet = charSet;
        this.key = key;
    }

    public Cipher(int key) {
        this.charSet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        this.key = key;
    }

    public Cipher(Cipher cipher) {
        this.charSet = cipher.charSet;
        this.key = cipher.key;
    }

    public int getCapacity() {
        return charSet.length();
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cipher cipher = (Cipher) o;

        if (key != cipher.key) return false;
        return charSet != null ? charSet.equals(cipher.charSet) : cipher.charSet == null;
    }

    @Override
    public int hashCode() {
        int result = charSet != null ? charSet.hashCode() : 0;
        result = 31 * result + key;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cipher.class.getSimpleName() + "[", "]")
                .add("charSet='" + charSet + "'")
                .add("key=" + key)
                .toString();
    }
}