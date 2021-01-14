package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Text;
import com.epam.izh.rd.online.exception.NotFoundException;

import java.util.Arrays;

public class RamTextRepository implements TextRepository {
    private Text[] texts;

    public RamTextRepository() {
        this.texts = new Text[0];
    }

    @Override
    public int count() {
        return texts.length;
    }

    @Override
    public int save(Text text) {
        int countTexts = count();
        texts = Arrays.copyOf(texts, countTexts + 1);
        texts[countTexts] = new Text(text);
        return ++countTexts;
    }

    @Override
    public Text read(int id) {
        Text text;
        try {
            text = new Text(texts[id]);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Текст с идентификатором id = " + id + " не существует.");
        }
        return text;
    }

    @Override
    public void clear() {
        texts = new Text[0];
    }
}