package com.epam.izh.rd.online.entity;

import java.util.StringJoiner;

public class Text {
    private String beforeContent;
    private String afterContent;
    private Cipher cipher;

    public Text(String content, Cipher cipher) {
        this.beforeContent = content;
        this.afterContent = "";
        this.cipher = cipher;
    }

    public Text(Text text) {
        beforeContent = text.beforeContent;
        afterContent = text.afterContent;
        cipher = text.cipher;
    }

    public String getBeforeContent() {
        return beforeContent;
    }

    public void setBeforeContent(String beforeContent) {
        this.beforeContent = beforeContent;
    }

    public String getAfterContent() {
        return afterContent;
    }

    public void setAfterContent(String afterContent) {
        this.afterContent = afterContent;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public void setCipher(Cipher cipher) {
        this.cipher = new Cipher(cipher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text = (Text) o;

        if (beforeContent != null ? !beforeContent.equals(text.beforeContent) : text.beforeContent != null)
            return false;
        if (afterContent != null ? !afterContent.equals(text.afterContent) : text.afterContent != null) return false;
        return cipher != null ? cipher.equals(text.cipher) : text.cipher == null;
    }

    @Override
    public int hashCode() {
        int result = beforeContent != null ? beforeContent.hashCode() : 0;
        result = 31 * result + (afterContent != null ? afterContent.hashCode() : 0);
        result = 31 * result + (cipher != null ? cipher.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Text.class.getSimpleName() + "[", "]")
                .add("beforeContent='" + beforeContent + "'")
                .add("afterContent='" + afterContent + "'")
                .add("cipher=" + cipher)
                .toString();
    }
}