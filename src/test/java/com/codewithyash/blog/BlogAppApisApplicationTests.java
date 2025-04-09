package com.codewithyash.blog;

import com.codewithyash.blog.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogAppApisApplicationTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void repoTest() {
        String className = this.userRepo.getClass().getName();
        String packageName = this.userRepo.getClass().getPackageName();
        System.out.println(className);
        System.out.println(packageName);
    }
}
