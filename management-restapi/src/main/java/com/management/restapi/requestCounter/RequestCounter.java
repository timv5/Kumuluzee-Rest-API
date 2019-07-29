package com.management.restapi.requestCounter;

import java.io.*;

public class RequestCounter {

    private String filename;

    public RequestCounter(final String filename){
        this.filename = filename;
    }

    public void count(){
        openFile();
    }

    private void openFile(){
        try{

            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename));

            // read
            String counter = bufferedReader.readLine();
            bufferedReader.close();

            // update
            int c = Integer.valueOf(counter);
            c += 1;

            // write
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename));
            writer.write(Integer.toString(c));
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
