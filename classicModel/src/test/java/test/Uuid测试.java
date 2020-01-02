package test;

import java.util.UUID;

public class Uuid测试 {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.getClass());
        System.out.println(uuid);
        System.out.println(uuid.hashCode());
        System.out.println(uuid.toString());



    }


}
