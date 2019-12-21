package com.util;

import com.commonData.Student;
import com.commonData.User;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DbUtil {


    public static List<User> getUserList(int i) {
        return IntStream.range(0, i)
                .mapToObj(e -> new User(e, "name" + e, "pass" + e, "e" + e, e, null, Date.from(Instant.now()), e))
                .collect(Collectors.toList());
    }

    public static List<Student> getStudentList(int i) {
        return IntStream.range(0, i)
                .mapToObj(e -> new Student(e, "name" + e, e, Date.from(Instant.now())))
                .collect(Collectors.toList());
    }
}
