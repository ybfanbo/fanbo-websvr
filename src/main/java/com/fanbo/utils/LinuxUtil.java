package com.fanbo.utils;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LinuxUtil {

    public static String exeCommand(String command) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec(command);
            InputStream is = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            Stream<String> stream = bufferedReader.lines();
            result = stream.filter(o -> !StringUtils.isEmpty(o)).collect(Collectors.joining("\r\n"));
        } catch (IOException e) {
            result = "error";
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public static String execShellFile(String cmd[]) {

        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process process = pb.start();
            process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = stdError.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {

            return e.getMessage();
        } catch (InterruptedException e) {
            return e.getMessage();
        }

        return sb.toString();
    }

}
