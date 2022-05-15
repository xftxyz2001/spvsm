package com.xftxyz.spvsm.controller.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.xftxyz.spvsm.controller.service.XFSetting;

/**
 * 文件工具类
 */
public class FileUtil {

    /**
     * 读取配置文件
     * 
     * @return 配置文件的内容
     * @throws IOException 加载配置文件时可能出现的异常
     */
    public static Map<String, Integer> readIniFile() {
        InputStream in = FileUtil.class.getClassLoader().getResourceAsStream(XFSetting.configFilePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Properties props = new Properties();
        try {
            props.load(br);
        } catch (IOException e) {
            try (PrintWriter pw = new PrintWriter("config.ini")) {
                pw.println("memorySize=" + XFSetting.DEFAULT_MEMORY_SIZE);
                pw.println("pageSize=" + XFSetting.DEFAULT_PAGE_SIZE);
                pw.println("maxSegmentNum=" + XFSetting.DEFAULT_MAX_SEGMENT_NUM);
                pw.println("maxSegmentSize=" + XFSetting.DEFAULT_MAX_SEGMENT_SIZE);
                pw.println("maxResidentSetNumber=" + XFSetting.DEFAULT_MAX_RESIDENT_SET_NUMBER);

            } catch (IOException e1) {
                throw new RuntimeException("初始化配置文件失败");
            }
            try {
                // 再次加载
                props.load(br);
            } catch (IOException e1) {
                throw new RuntimeException("初始化配置文件失败");
            }
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Object s : props.keySet()) {
            String key = s.toString();
            Integer value = Integer.parseInt(props.getProperty(key));
            map.put(key, value);
            // map.put(s.toString(), props.getProperty(s.toString()));
        }
        return map;
    }
}
