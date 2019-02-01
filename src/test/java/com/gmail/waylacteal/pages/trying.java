package com.gmail.waylacteal.pages;

import com.gmail.waylacteal.ExcelReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class trying {
    public static void main(String[] args) {
        ArrayList<String[]> ansList = new ArrayList<String[]>();
        ansList.add(new String[]{"'Very interested'","'Just looking'"});
        ansList.add(new String[]{"'6–15'", "'1–5'", "'16–25'", "'26–50'", "'50+'"});
        ansList.add(new String[]{"'Yes'", "'No'", "'Other'"});
        System.out.print(ansList.get(0)[0]);
    }
}
