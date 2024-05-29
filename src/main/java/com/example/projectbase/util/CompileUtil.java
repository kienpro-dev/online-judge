package com.example.projectbase.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompileUtil {
    public static String compileAndRunExercise(File tempFile, String input, String output) {
        try {
            ProcessBuilder compileProcess = new ProcessBuilder("java", tempFile.getAbsolutePath());
            compileProcess.redirectErrorStream(true);
            Process process = compileProcess.start();

            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }
            reader.close();
            if (!outputBuilder.toString().trim().equals(output.trim())) {
                return "wrong";
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }
}
