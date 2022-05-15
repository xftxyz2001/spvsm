package com.xftxyz.spvsm;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.xftxyz.spvsm.controller.activity.FileUtil;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void configReadTest() {
        // System.out.println("asdfasdfa");
        try {
            Map<String, String> map = FileUtil.readIniFile();
            // 遍历map
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
