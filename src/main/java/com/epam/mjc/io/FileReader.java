package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            profile = new Profile();
            int ch;
            String fileContent = "";

            int count = 1;
            while ((ch = fis.read()) != -1){
                var currentChar = (char) ch;
                fileContent = fileContent.concat(String.valueOf(currentChar));
            }
            for (String row : fileContent.split("\n")){

                String[] split = row.split(": ");
                if (split[0].equals("Name")){
                    profile.setName(split[1]);
                }

                if (split[0].equals("Age")){
                    profile.setAge(Integer.parseInt(split[1]));
                }

                if (split[0].equals("Email")){
                    profile.setEmail(split[1]);
                }

                if (split[0].equals("Phone")){
                    profile.setPhone(Long.parseLong(split[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e){

            }

        }

        return profile;
    }
}
