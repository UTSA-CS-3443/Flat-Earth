package com.flatearth;

import java.io.File;
import java.io.BufferedReader;
import java.text.ParseException;
import java.io.IOException;
import java.io.FileReader;

public class ParseMap {

    private String mapname;



    public ParseMap() {
        try {
            lineReaderParse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void lineReaderParse() throws IOException, ParseException {

        String line;
        File fl = new File("map.pp");
        FileReader frd = new FileReader(fl);
        BufferedReader brd = new BufferedReader(frd);

        line = brd.readLine();
        this.mapname = line;

        while ((line=brd.readLine())!=null)
            doSomethingWithLine(line);
        brd.close();
        frd.close();
    }

    public final void doSomethingWithLine(String line) throws ParseException {
        // Example of what to do for each line
        System.out.println(line);

    }

}