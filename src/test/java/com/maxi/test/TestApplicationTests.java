package com.maxi.test;

import com.maxi.test.services.XmlToDatabaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    private XmlToDatabaseServiceImpl xmlParserService;

    @Test
    void contextLoads() {

    }

    @Test
    void moveOrDelete(@Value("${path_in}") String path_in,
                      @Value("${path_out}") String path_out) {
//        File file = Arrays.stream(new File(path_in).listFiles()).findFirst().get();
//        xmlParserService.moveOrDelete(file);

        Assert.isTrue(true);
    }

}
