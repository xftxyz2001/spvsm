package com.xftxyz.spvsm.controller.activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.xftxyz.spvsm.controller.service.XFSetting;

public class FileUtil {
    public static Map<String, String> readIniFile() throws Exception {
        InputStream in = FileUtil.class.getClassLoader().getResourceAsStream(XFSetting.configFilePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Properties props = new Properties();
        props.load(br);
        Map<String, String> map = new HashMap<String, String>();
        for (Object s : props.keySet()) {
            map.put(s.toString(), props.getProperty(s.toString()));
        }
        return map;
    }
}
