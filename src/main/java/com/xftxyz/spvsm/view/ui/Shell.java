package com.xftxyz.spvsm.view.ui;

import java.util.Map;

import com.xftxyz.spvsm.controller.activity.FileUtil;
import com.xftxyz.spvsm.controller.service.OS;
import com.xftxyz.spvsm.controller.service.XFSetting;
import com.xftxyz.spvsm.view.utils.Input;

/**
 * 主程序
 */
public class Shell {
    private OS os = new OS();

    public static void main(String[] args) {
        new Shell().run();
    }

    private void run() {
        printMessage();
        initialize();
        setReplacePolicy();
        System.out.println("输入h[elp]获取更多帮助信息");
        shell();
        Input.close();
    }

    /**
     * 打印一些说明信息
     */
    private void printMessage() {
        System.out.println("==============================" + XFSetting.name + " v " + XFSetting.version
                + "==============================\n");

        System.out.println(XFSetting.description + "\n");
    }

    private void initialize() {
        Map<String, Integer> config = FileUtil.readIniFile();
        os.init(config);
    }

    /**
     * 设置默认置换策略
     */
    public void setReplacePolicy() {
        System.out.print(">>> 请设置置换策略(0[FIFO] 1[LRU]): ");
        while (true) {
            String mess = Input.nextLine().trim();
            if ("0".equals(mess) || "FIFO".equals(mess)) {
                OS.setReplacePolicy(OS.REPLACE_POLICY.FIFO);
                System.out.println("设置置换策略为FIFO");
                break;
            } else if ("1".equals(mess) || "LRU".equals(mess)) {
                OS.setReplacePolicy(OS.REPLACE_POLICY.LRU);
                System.out.println("设置置换策略为LRU");
                break;
            } else {
                System.out.print(">>> 输入有误，请设置置换策略(0[FIFO] 1[LRU]): ");
            }
        }
    }

    /**
     * 1. create process pname segments
     * 2. destroy process pname
     * 3. show memory
     * 4. show process pname
     * 5. help or h
     * 6. quit or q
     * 8. address pname sgementNum segmentOffset
     */
    public void shell() {
        System.out.print(">>> ");
        while (true) {
            String command = Input.nextLine();
            if (command == null || command.trim().equals("")) {
                System.out.print(">>> ");
                continue;
            }

            String[] words = command.split(" ");
            if (words.length >= 4 && "create".equals(words[0].trim()) && "process".equals(words[1].trim())) {
                String processId = words[2].trim();
                int[] segments = new int[words.length - 3];
                try {
                    for (int i = 3, index = 0; i < words.length; i++, index++) {
                        segments[index] = Integer.parseInt(words[i]);
                        if (segments[index] <= 0) {
                            throw new Exception();
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("命令有误 段大小必须为正整数(help获取帮助)");
                    System.out.print(">>> ");
                    continue;
                }
                os.createProcess(processId, segments);

            } else if (words.length == 3 && "destroy".equals(words[0].trim()) && "process".equals(words[1].trim())) {
                String processId = words[2].trim();
                os.destroyProcess(processId);

            } else if (words.length == 2 && "show".equals(words[0].trim()) && "memory".equals(words[1].trim())) {
                os.showMemory();

            } else if (words.length == 3 && "show".equals(words[0].trim()) && "process".equals(words[1].trim())) {
                String processId = words[2].trim();
                os.showProcess(processId);

            } else if (words.length == 1 && "help".equals(words[0].trim()) || "h".equals(words[0].trim())) {
                System.out.println(XFSetting.helpMess);

            } else if (words.length == 1 && "quit".equals(words[0].trim()) || "q".equals(words[0].trim())) {
                System.out.println("quit");
                break;
            } else if (words.length == 4 && "address".equals(words[0].trim())) {
                String porcessId = words[1].trim();
                int segmentNum, segmentOffset;
                try {
                    segmentNum = Integer.parseInt(words[2].trim());
                    segmentOffset = Integer.parseInt(words[3].trim());
                    if (segmentNum < 0 || segmentOffset < 0) {
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    System.out.println("命令有误 段号和段偏移必须为正整数(help获取帮助)");
                    System.out.print(">>> ");
                    continue;
                }
                os.toPhysicalAddress(porcessId, segmentNum, segmentOffset);

            } else {
                System.out.println("命令有误(help获取帮助)");
            }

            System.out.print(">>> ");
        }
    }

}
