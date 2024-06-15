package com.example.projectbase.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompileUtil {
    public static String compileAndRunExercise(String code, String input, String output) {
        try {
            File tempFile = FileUtil.writeDataToFile(code);
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
            return "error";
        }

        return "success";
    }

    public static String compileAndRunCppExercise(String code, String input, String expectedOutput) {
        try {
            File tempFile = FileUtil.writeDataToFile(code);
            ProcessBuilder compileProcessBuilder = new ProcessBuilder("g++", tempFile.getAbsolutePath(), "-o", "outputExecutable");
            compileProcessBuilder.redirectErrorStream(true);
            Process compileProcess = compileProcessBuilder.start();
            int compileExitCode = compileProcess.waitFor();

            if (compileExitCode != 0) {
                return "failed";
            }

            ProcessBuilder runProcessBuilder = new ProcessBuilder("./outputExecutable");
            runProcessBuilder.redirectErrorStream(true);
            Process runProcess = runProcessBuilder.start();

            if (input != null && !input.isEmpty()) {
                runProcess.getOutputStream().write(input.getBytes());
                runProcess.getOutputStream().flush();
            }
            runProcess.getOutputStream().close();

            InputStream inputStream = runProcess.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }
            reader.close();

            if (!outputBuilder.toString().trim().equals(expectedOutput.trim())) {
                return "wrong";
            }

            int runExitCode = runProcess.waitFor();
            if (runExitCode != 0) {
                return "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "success";
    }

    public static String compileAndRunPythonExercise(String code, String input, String expectedOutput) {
        try {
            File tempFile = FileUtil.writeDataToFile(code);
            ProcessBuilder runProcessBuilder = new ProcessBuilder("python3", tempFile.getAbsolutePath());
            runProcessBuilder.redirectErrorStream(true);
            Process runProcess = runProcessBuilder.start();

            if (input != null && !input.isEmpty()) {
                runProcess.getOutputStream().write(input.getBytes());
                runProcess.getOutputStream().flush();
            }
            runProcess.getOutputStream().close();

            InputStream inputStream = runProcess.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }
            reader.close();

            if (!outputBuilder.toString().trim().equals(expectedOutput.trim())) {
                return "wrong";
            }

            int runExitCode = runProcess.waitFor();
            if (runExitCode != 0) {
                return "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "success";
    }

}
