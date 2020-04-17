package net;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Oi {
    public static void main(String[] args) throws IOException {
        File file1 = new File("D:\\安装包\\uu.jpg");
        File file2 = new File("D:\\安装包\\uu1.jpg");
        new Thread(() -> {
            try {
                Files.copy(file1.toPath(), file2.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                byte[] bytes = Files.readAllBytes(file1.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
