import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.swing.JFrame.*;

class currentTime {
    SimpleDateFormat df;

    public currentTime() {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public String getTime() {
        return df.format(new Date());
    }
}


class MyExtendsJFrame extends JFrame implements ActionListener, Runnable, KeyListener {

    JTextField textIP, textPort;
    JTextArea textRecv, textSend;
    JButton buttonSend, buttonClear, buttonOption, button_ipset, button_green, button_blue, button_bg1, button_bg2, button_Font, button_Font_Color;
    JLabel label_IP, label_port, decoration, bg_ground;
    String defaultIP, defaultPort, LocalIP, OtherIp;
    currentTime ct = new currentTime();

    int flags = 0;

    public MyExtendsJFrame() {
        defaultIP = "127.0.0.1";
        defaultPort = "2017";
        try {
            LocalIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        getContentPane().setBackground(new Color(209, 221, 240));
        setTitle("Chatme");
        setBounds(160, 100, 450, 820);
        setLayout(null);
        init();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void init() {//��ӵĿؼ�

        // ��ǩ

//        label_IP = new JLabel("IP:");
//        label_IP.setBounds(5,370,50,30);
//        add(label_IP);


//        decoration = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Test.png")));
//        decoration.setBounds(2,2,170,360);
//        add(decoration);

//        label_port = new JLabel("PORT:");
//        label_port.setBounds(5,440,50,30);
//        add(label_port);

        textSend = new JTextArea();
        textSend.setOpaque(false);
        textSend.setBorder(null);
        textSend.setFont(new Font("等线Light", 30, 25));
        add(textSend);
        textSend.addKeyListener(this);

        JScrollPane scrollPane1 = new JScrollPane(textSend);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(0, 710, 362, 40);
        scrollPane1.setBorder(null);
        scrollPane1.getViewport().setOpaque(false);
        add(scrollPane1);

        textRecv = new JTextArea();
//        textRecv.setBounds(175,0,525,400);
        textRecv.setOpaque(false);
        textRecv.setBorder(null);
        textRecv.setFont(new Font("等线Light", 30, 17));
        add(textRecv);

        JScrollPane scrollPane = new JScrollPane(textRecv);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 45, 442, 665);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);

        bg_ground = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("bg_pic2.png")));
        bg_ground.setBounds(0, 20, 450, 700);
        bg_ground.setBorder(null);
        add(bg_ground);
//        ((JPanel)this.getContentPane()).setOpaque(false);
//        getLayeredPane().add(bg_ground,new Integer(Integer.MIN_VALUE));

//        textIP = new JTextField(16);
//        textIP.setBounds(45,370,100,30);
//        textIP.setText(defaultIP);
//        textIP.addMouseListener(this);
//        add(textIP);
//
//        textPort = new JTextField(4);
//        textPort.setBounds(45,440,40,30);
//        textPort.setText(defaultPort);
//        textPort.addMouseListener(this);
//        add(textPort);

        buttonOption = new JButton("Option");
        buttonOption.setBounds(0, 0, 60, 45);
        buttonOption.setBorder(null);
        buttonOption.setBackground(new Color(149, 203, 232));
        buttonOption.addActionListener(this);//��ӹ���
        add(buttonOption);

        textIP = new JTextField(16);
        textIP.setBounds(60, 0, 312, 45);
        textIP.setFont(new Font("等线Light", 30, 20));
        textIP.setText("             " + defaultIP);
        textIP.setBorder(null);
        textIP.setBackground(new Color(150, 210, 240));
        textIP.setEditable(false);
        add(textIP);


        buttonClear = new JButton("Clear");
        buttonClear.setBounds(372, 0, 60, 45);
        buttonClear.setBorder(null);
        buttonClear.setBackground(new Color(149, 203, 232));
        buttonClear.addActionListener(this);//��ӹ���
        add(buttonClear);

        buttonSend = new JButton("Send");
        buttonSend.setBounds(362, 710, 70, 40);
        buttonSend.setBorder(null);
        buttonSend.setBackground(new Color(149, 203, 232));
        buttonSend.addActionListener(this);//��ӹ���
        this.getLayeredPane().add(buttonSend, new Integer(Integer.MAX_VALUE));
        add(buttonSend);

        button_Font = new JButton("字体");
        button_Font.setBounds(0, 750, 70, 20);
        button_Font.setBorder(null);
        button_Font.setBackground(new Color(183, 183, 182));
        button_Font.addActionListener(this);//��ӹ���
        this.getLayeredPane().add(button_Font, new Integer(Integer.MAX_VALUE));
        add(button_Font);

        button_Font_Color = new JButton("字体颜色");
        button_Font_Color.setBounds(85, 750, 70, 20);
        button_Font_Color.setBorder(null);
        button_Font_Color.setBackground(new Color(183, 183, 182));
        button_Font_Color.addActionListener(this);//��ӹ���
        this.getLayeredPane().add(button_Font_Color, new Integer(Integer.MAX_VALUE));
        add(button_Font_Color);

        button_bg1 = new JButton("背景更换");
        button_bg1.setBounds(170, 750, 70, 20);
        button_bg1.setBorder(null);
        button_bg1.setBackground(new Color(183, 183, 182));
        button_bg1.addActionListener(this);//��ӹ���
        this.getLayeredPane().add(button_bg1, new Integer(Integer.MAX_VALUE));
        add(button_bg1);

        button_blue = new JButton("蓝色主题");
        button_blue.setBounds(255, 750, 70, 20);
        button_blue.setBorder(null);
        button_blue.setBackground(new Color(183, 183, 182));
        button_blue.addActionListener(this);//��ӹ���
        this.getLayeredPane().add(button_blue, new Integer(Integer.MAX_VALUE));
        add(button_blue);

        button_green = new JButton("绿色主题");
        button_green.setBounds(340, 750, 70, 20);
        button_green.setBorder(null);
        button_green.setBackground(new Color(183, 183, 182));
        button_green.addActionListener(this);//��ӹ���
        this.getLayeredPane().add(button_green, new Integer(Integer.MAX_VALUE));
        add(button_green);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSend) {
            if (textSend.getText().equals("")) {
                JOptionPane.showConfirmDialog(null, "不能发送空消息", "提示", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            } else {

                byte data[] = (LocalIP + '\n' + textSend.getText()).getBytes();
                try {
                    InetAddress address = InetAddress.getByName(OtherIp);
                    DatagramPacket SendPacket = new DatagramPacket(data, data.length, address, Integer.parseInt(defaultPort));


                    DatagramSocket Post = new DatagramSocket();
                    Post.send(SendPacket); //��������
                    textRecv.setText(textRecv.getText() + '\n' + ct.getTime() + "   " + "我" + "\n" + textSend.getText());
                    textSend.setText("");
                } catch (Exception e1) {
                }
            }
        }

        if (e.getSource() == buttonClear) {
            textRecv.setText("");
        }

        if (e.getSource() == buttonOption) {
            String ip = JOptionPane.showInputDialog("请输入对方的IP地址：", "127.0.0.1");
            textIP.setText("              " + ip);
        }

        if (e.getSource() == button_Font) {
            zitis zt = new zitis(this);
            textSend.setFont(zt.f);
            textRecv.setFont(zt.f);
        }

        if (e.getSource() == button_Font_Color) {
            Color newcolor = JColorChooser.showDialog(this, "调色板",
                    textSend.getForeground());
            textSend.setForeground(newcolor);
            textRecv.setForeground(newcolor);
        }

        if (e.getSource() == button_bg1) {
            if (flags == 0) {
                bg_ground = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("bg_pic2.png")));
                bg_ground.setBounds(0, 20, 450, 700);
                bg_ground.setBorder(null);
                add(bg_ground);
            } else {

            }
        }

        if (e.getSource() == button_blue) {
            buttonSend.setBackground(new Color(149, 203, 232));
            buttonClear.setBackground(new Color(149, 203, 232));
            textIP.setBackground(new Color(150, 210, 240));
            button_Font_Color.setBackground(new Color(183, 183, 182));
            button_Font.setBackground(new Color(183, 183, 182));
            button_bg1.setBackground(new Color(183, 183, 182));
            button_blue.setBackground(new Color(183, 183, 182));
            button_green.setBackground(new Color(183, 183, 182));
            buttonOption.setBackground(new Color(149, 203, 232));
        }

        if (e.getSource() == button_green) {
            buttonSend.setBackground(new Color(95, 232, 51));
            buttonClear.setBackground(new Color(95, 232, 51));
            textIP.setBackground(new Color(127, 240, 120));
            button_Font_Color.setBackground(new Color(115, 80, 183));
            button_bg1.setBackground(new Color(115, 80, 183));
            button_Font.setBackground(new Color(115, 80, 183));
            button_blue.setBackground(new Color(115, 80, 183));
            button_green.setBackground(new Color(115, 80, 183));
            buttonOption.setBackground(new Color(95, 232, 51));
        }
    }


    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            buttonSend.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    public void run()//�����߳�
    {
        byte data[] = new byte[1024];
        try {
            DatagramSocket Recv = new DatagramSocket(Integer.parseInt(defaultPort));//����UDP���ն���
            DatagramPacket pack = new DatagramPacket(data, data.length);//����UDP�������ݰ�
            System.out.println(new String(pack.getData(), 0, pack.getLength()));
            while (true) {
                Recv.receive(pack); //����һ�����ݰ�
                String s = new String(pack.getData(), 0, pack.getLength());  //��ȡ���ݰ�
                textRecv.setText(textRecv.getText() + '\n' + ct.getTime() + "   " + s);  //��ʾ����
            }
        } catch (Exception e1) {
        }
    }

}


class Computer {
    public static void main(String args[]) {

        MyExtendsJFrame frame = new MyExtendsJFrame();//����������򴰿�

        Thread readData = new Thread(frame);//���������߳�
        readData.start();    //�����߳�

    }
}

class zitis
        implements ActionListener, ItemListener {
    JTextField text1 = new JTextField("楷体"), text2 = new JTextField("PLAIN"),
            text3 = new JTextField("14", 2);
    java.awt.List list1 = new java.awt.List(6, false),
            list2 = new java.awt.List(6, false), list3 = new java.awt.List(6, false);
    String zixing[] = {
            "常规", "斜体", "粗体"};
    String daxiao[] = {
            "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26",
            "28", "34"};
    Font f;
    String aa = "楷体";
    int cc = 14;
    private JLabel lb1 = new JLabel("字体"), lb2 = new JLabel("字形"),
            lb3 = new JLabel("大小");
    private JButton b_queding = new JButton("确定"), b_quxiao = new JButton("取消");
    private JDialog dialog;

    //字体对话框类的构造方法
    public zitis(JFrame f) {
        dialog = new JDialog(f, "字体", true);
        dialog.setLocation(150, 150);
        dialog.setSize(400, 230);
        dialog.setResizable(false);
        Container dd = dialog.getContentPane();
        dd.setLayout(null);
        lb1.setBounds(4, 2, 30, 20);
        dd.add(lb1);
        lb2.setBounds(150, 2, 30, 20);
        dd.add(lb2);
        lb3.setBounds(250, 2, 30, 20);
        dd.add(lb3);
        text1.setBounds(4, 24, 140, 20);
        dd.add(text1);
        text2.setBounds(150, 24, 95, 20);
        dd.add(text2);
        text3.setBounds(250, 24, 50, 20);
        dd.add(text3);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String fontName[] = ge.getAvailableFontFamilyNames();
        for (int i = 0; i < fontName.length; i++) {
            list1.add(fontName[i]);
        }
        for (int j = 0; j < zixing.length; j++) {
            list2.add(zixing[j]);
        }
        for (int k = 0; k < daxiao.length; k++) {
            list3.add(daxiao[k]);
        }
        list1.setBounds(4, 46, 140, 130);
        dd.add(list1);
        list2.setBounds(150, 46, 95, 130);
        dd.add(list2);
        list3.setBounds(250, 46, 50, 130);
        dd.add(list3);
        b_queding.setBounds(315, 60, 60, 20);
        dd.add(b_queding);
        b_quxiao.setBounds(315, 100, 60, 20);
        dd.add(b_quxiao);
        b_queding.addActionListener(this);
        b_quxiao.addActionListener(this);
        list1.addItemListener(this);
        list2.addItemListener(this);
        list3.addItemListener(this);
        dialog.show();
    }

    //响应按钮事件的方法
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_queding) {
            aa = text1.getText();
            cc = Integer.parseInt(text3.getText());
            if (list2.getSelectedIndex() == 0) {
                f = new Font(aa, Font.PLAIN, cc);
            } else if (list2.getSelectedIndex() == 1) {
                f = new Font(aa, Font.ITALIC, cc);
            } else if (list2.getSelectedIndex() == 2) {
                f = new Font(aa, Font.BOLD, cc);
            } else {
                f = new Font(aa, Font.PLAIN, cc);
            }
            dialog.show(false);
        } else {
            dialog.show(false);
        }
    }

    //响应列表框事件的方法
    public void itemStateChanged(ItemEvent e) {
        String name1 = list1.getSelectedItem();
        String name2 = list2.getSelectedItem();
        String name3 = list3.getSelectedItem();
        if (name1 != null) {
            text1.setText(name1);
        }
        if (name2 != null) {
            text2.setText(name2);
        }
        if (name3 != null) {
            text3.setText(name3);
        }
    }
}


//class Option extends JFrame implements ActionListener{
//
//    JLabel ip;
//    JTextField ipset;
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        getContentPane().setBackground(new Color(209, 221, 240));
//        setTitle("OptionPanel");
//        setBounds(160, 350, 300, 400);
//        setLayout(null);
//        init();
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    private void init() {
//        ip = new JLabel("ip");
//        ip.setBounds(20,40,20,20);
//        add(ip);
//
//        ipset = new JTextField("127.0.0.1",20);
//        ipset.setBounds(50,40,40,20);
//
//
//    }
//}


