package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Text;

public interface TextRepository {

    int count();

    int save(Text text);

    Text read(int id);

    void clear();
}