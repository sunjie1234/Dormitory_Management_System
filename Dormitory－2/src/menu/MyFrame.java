package menu;
import DB.DBUtil;
import Entity.*;
import menu.ImagePanel;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
public class MyFrame extends JFrame {
    DBUtil DB = new DBUtil();
    public MyFrame(String title) {
        this.setResizable(false);
        this.setTitle(title);
        this.setSize(700, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel parent_panel = new JPanel();
        parent_panel.setLayout(null);
        ImagePanel panel = new ImagePanel(700,600,"D:\\IDEA\\project\\dormitory－2\\Dormitory－2\\src\\images\\bg.jpg");
        panel.setLayout(null);
        panel.add(new Button("panel2"));
        ImagePanel imagePanel = new ImagePanel(700,600,"D:\\IDEA\\project\\dormitory－2\\Dormitory－2\\src\\images\\bg.jpg");
        imagePanel.setLayout(null);
        modify_password(panel,imagePanel,parent_panel);
        parent_panel.add(imagePanel);
        imagePanel.setBounds(0,0,700,600);
        denglu(imagePanel,panel,parent_panel,this);
        this.add(parent_panel);
        this.setVisible(true);
    }
    //孙文朋
    public void denglu(JPanel panel,JPanel panel2,JPanel parent,JFrame frame2){
        //登录
        JLabel label1 = new JLabel("登录");
        label1.setFont(new Font("微软雅黑",Font.PLAIN,30));
        panel.add(label1);
        label1.setBounds(310,0,100,100);

        //用户
        JLabel label2 = new JLabel("账号");
        panel.add(label2);
        label2.setFont(new Font("微软雅黑",Font.PLAIN,25));
        label2.setBounds(200,100,100,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(270,135,200,30);

        //密码
        JLabel label3 = new JLabel("密码");
        panel.add(label3);
        label3.setFont(new Font("微软雅黑",Font.PLAIN,25));
        label3.setBounds(200,200,100,100);
        JTextField text2 = new JTextField(10);
        panel.add(text2);
        text2.setBounds(270,235,200,30);

        //身份选择
        JLabel label4 = new JLabel("身份");
        panel.add(label4);
        label4.setFont(new Font("微软雅黑",Font.PLAIN,25));
        label4.setBounds(200,290,100,100);
        JComboBox<String> user = new JComboBox<>();
        user.addItem("学生");
        user.addItem("宿管");
        user.addItem("超级管理员");
        panel.add(user);
        user.setBounds(270,325,130,30);
        //修改密码
        JButton bt1 = new JButton("登录");
        JButton bt2 = new JButton("修改密码");
        panel.add(bt1);
        panel.add(bt2);
        bt1.setBounds(230,400,100,50);
        bt2.setBounds(400,400,100,50);

        //登录按钮的监听事件
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = user.getSelectedIndex();
                int state=DB.load(select,text1.getText(),text2.getText());
                if(text1.getText().equals("")||text2.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"账号密码不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==0)
                {
                    JOptionPane.showMessageDialog(null,"登录成功","消息提示",JOptionPane.WARNING_MESSAGE);
                    frame2.setVisible(false);
                    if(select==0){
                    JFrame frame = new JFrame("宿舍管理系统");
                    frame.setSize(900,800);
                    frame.setVisible(false);
                    frame.setResizable(false);
                    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    JPanel parent = new JPanel();
                    parent.setLayout(null);
                    JPanel p1=new JPanel();
                    p1.setLayout(null);
                    p1.setBounds(0,0,900,800);
                    Student_menu(p1,frame,frame2);
                    parent.add(p1);
                    frame.add(parent);
                    frame.setVisible(true);
                }
                    else if(select==1){
                        JFrame frame = new JFrame("宿舍管理系统");
                        frame.setSize(900,800);
                        frame.setVisible(false);
                        frame.setResizable(false);
                        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        JPanel parent = new JPanel();
                        parent.setLayout(null);
                        JPanel p1=new JPanel();
                        p1.setLayout(null);
                        p1.setBounds(0,0,900,800);
                        Management_menu(p1,frame,frame2);
                        parent.add(p1);
                        frame.add(parent);
                        frame.setVisible(true);
                    }
                    else{
                        JFrame frame = new JFrame("宿舍管理系统");
                        frame.setSize(900,800);
                        frame.setVisible(false);
                        frame.setResizable(false);
                        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        JPanel parent = new JPanel();
                        parent.setLayout(null);
                        JPanel p1=new JPanel();
                        p1.setLayout(null);
                        p1.setBounds(0,0,900,800);
                        MAMA_menu(p1,frame,frame2);
                        parent.add(p1);
                        frame.add(parent);
                        frame.setVisible(true);
                    }
                }
                else if(state==1)
                    JOptionPane.showMessageDialog(null,"密码错误","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==2)
                    JOptionPane.showMessageDialog(null,"用户不存在","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.remove(panel);
                parent.add(panel2);
                panel2.setBounds(0,0,700,600);
                parent.revalidate();
                parent.repaint();
            }
        });
    }
    //孙文朋
    public void modify_password(JPanel panel,JPanel panel2,JPanel parent){
        //修改密码
        JLabel label1 = new JLabel("修改密码");
        label1.setFont(new Font("微软雅黑",Font.PLAIN,30));
        panel.add(label1);
        label1.setBounds(280,0,200,100);

        //用户输入账号
        JLabel label2 = new JLabel("账号:");
        panel.add(label2);
        label2.setFont(new Font("微软雅黑",Font.PLAIN,20));
        label2.setBounds(200,60,100,100);
        JTextField text1 = new JTextField(10);
        panel.add(text1);
        text1.setBounds(270,95,200,30);

        //用户输入密码
        JLabel label3 = new JLabel("密码:");
        panel.add(label3);
        label3.setFont(new Font("微软雅黑",Font.PLAIN,20));
        label3.setBounds(200,120,100,100);
        JTextField text2 = new JTextField(10);
        panel.add(text2);
        text2.setBounds(270,155,200,30);

        //用户输入修改密码
        JLabel label4 = new JLabel("修改密码:");
        panel.add(label4);
        label4.setFont(new Font("微软雅黑",Font.PLAIN,20));
        label4.setBounds(160,180,100,100);
        JTextField text3 = new JTextField(10);
        panel.add(text3);
        text3.setBounds(270,215,200,30);

        //用户确认密码
        JLabel label5 = new JLabel("确认密码:");
        panel.add(label5);
        label5.setFont(new Font("微软雅黑",Font.PLAIN,20));
        label5.setBounds(160,240,100,100);
        JTextField text4 = new JTextField(10);
        panel.add(text4);
        text4.setBounds(270,275,200,30);

        //用户身份
        JLabel label6 = new JLabel("身份:");
        panel.add(label6);
        label6.setFont(new Font("微软雅黑",Font.PLAIN,20));
        label6.setBounds(200,300,100,100);
        JComboBox<String> user = new JComboBox<>();
        user.addItem("学生");
        user.addItem("宿管");
        user.addItem("超级管理员");
        panel.add(user);
        user.setBounds(270,335,130,30);

        //确认修改密码
        JButton bt1 = new JButton("确定");
        panel.add(bt1);
        bt1.setBounds(230,395,100,50);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int select = user.getSelectedIndex();
            int state = DB.modify(select,text1.getText(),text2.getText(),text3.getText(),text4.getText());
            if(text1.getText().equals("")||text1.getText().equals("")||text1.getText().equals("")||text1.getText().equals(""))
                JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
            else{
            if(state==0)
            {
                System.out.println("密码修改成功");
                JOptionPane.showMessageDialog(null,"密码修改成功","消息提示",JOptionPane.WARNING_MESSAGE);
                parent.remove(panel);
                parent.add(panel2);
                panel2.setBounds(0,0,700,600);
                parent.revalidate();
                parent.repaint();
            }
            else if(state==1)
            {
                JOptionPane.showMessageDialog(null,"所输密码错误","消息提示",JOptionPane.WARNING_MESSAGE);
            }
            else if(state==2)
                JOptionPane.showMessageDialog(null,"该用户不存在","消息提示",JOptionPane.WARNING_MESSAGE);
            else if(state==3)
                JOptionPane.showMessageDialog(null,"密码格式应为6-10位字符","消息提示",JOptionPane.WARNING_MESSAGE);
            else if(state==4)
                JOptionPane.showMessageDialog(null,"两次密码不一致","消息提示",JOptionPane.WARNING_MESSAGE);
            else if(state==5)
                JOptionPane.showMessageDialog(null,"修改密码和原密码一样","消息提示",JOptionPane.WARNING_MESSAGE);
            }}
        });
        //取消
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(370,395,100,50);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.remove(panel);
                parent.add(panel2);
                panel2.setBounds(0,0,700,600);
                parent.revalidate();
                parent.repaint();
            }
        });
    }
    //袁可嘉
    public void Student_menu(JPanel panel,JFrame frame1,JFrame frame2){
        //panel是根组件，p1是子组件，p2是p1的子组件;
        JPanel p1=new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(0,100,900,700);
        ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\dormitory－2\\Dormitory－2\\src\\images\\bg.jpg");
        p2.setLayout(null);
        p2.setBounds(0,0,900,700);
        huanying(p2);
        p1.add(p2);

        JMenuBar menu = new JMenuBar();
        JMenu one = new JMenu("用户");
        JMenu two = new JMenu("个人中心");
        JMenu three = new JMenu("报修");
        JMenu four = new JMenu("宿舍信息");
        menu.add(one);
        JMenuItem item11 = new JMenuItem("宿舍公告");
        JMenuItem item10 = new JMenuItem("首页");
        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                huanying(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item1=new JMenuItem("退出");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(true);
                frame1.setVisible(false);
            }
        });
        one.add(item1);
        one.add(item10);
        menu.add(two);
        JMenuItem item2=new JMenuItem("查看个人信息");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Student_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item3=new JMenuItem("编辑个人信息");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Edit_Student_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        two.add(item2);
        two.add(item3);
        menu.add(three);
        JMenuItem item4=new JMenuItem("提交报修信息");
        JMenuItem item5=new JMenuItem("查看报修表");
        three.add(item4);
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                repair(p2);
                p1.repaint();
                p1.validate();
            }
        });
        three.add(item5);
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Item_Message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        menu.add(four);
        JMenuItem item6=new JMenuItem("查看宿舍成员信息");
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
               Student_Dormitory_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item7=new JMenuItem("查看宿舍处分信息");
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                select_StudentChufen(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item8=new JMenuItem("查看宿管信息");
        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
               Student_Management_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Student_selectMessage(p2);
                p1.repaint();
                p1.validate();
            }
        });
        four.add(item6);
        four.add(item7);
        four.add(item8);
        four.add(item11);
        panel.add(menu);
        menu.setBounds(0,0,900,100);
    }
    //超级管理员菜单
    // 孙婕
    public void MAMA_menu(JPanel panel,JFrame frame1,JFrame frame2){
        //panel是根组件，p1是子组件，p2是p1的子组件;
        JPanel p1=new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(0,100,900,700);
        ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\dormitory－2\\Dormitory－2\\src\\images\\bg.jpg");
        p2.setLayout(null);
        p2.setBounds(0,0,900,700);
        huanying(p2);
        p1.add(p2);
        JMenuBar menu = new JMenuBar();
        JMenu four = new JMenu("用户");
        JMenu one = new JMenu("宿舍楼");
        JMenu two = new JMenu("学生信息");
        JMenu three = new JMenu("宿舍信息");
        JMenu five = new JMenu("宿管信息");
        JMenuItem item12 = new JMenuItem("添加宿管");
        JMenuItem item13 = new JMenuItem("删除宿管");
        JMenuItem item14 = new JMenuItem("查询宿管");
        item12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                addManagement(p2);
                p1.repaint();
                p1.validate();
            }
        });
        item13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                deleManagement(p2);
                p1.repaint();
                p1.validate();
            }
        });
        item14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                selectManagement(p2);
                p1.repaint();
                p1.validate();
            }
        });
        menu.add(four);
        menu.add(one);
        menu.add(two);
        menu.add(three);
        menu.add(five);
        five.add(item12);
        five.add(item13);
        five.add(item14);
        JMenuItem item1 = new JMenuItem("添加宿舍楼");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                newFloor(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item2 = new JMenuItem("删除宿舍楼");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                defloor(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item16 = new JMenuItem("查询宿舍楼");
        item16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
               selectTno(p2);
                p1.repaint();
                p1.validate();

            }
        });
        one.add(item1);
        one.add(item2);
        one.add(item16);
        JMenuItem item4 = new JMenuItem("增加学生");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                newStudent(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item5 = new JMenuItem("删除学生");
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
               destudent(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item15 = new JMenuItem("查询学生");
        item15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                selectStudent(p2);
                p1.repaint();
                p1.validate();
            }
        });
        two.add(item4);
        two.add(item5);
        two.add(item15);
        JMenuItem item6 = new JMenuItem("添加宿舍");
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                newDor(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item7 = new JMenuItem("删除宿舍");
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                deDor(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item8 = new JMenuItem("查询宿舍");
        three.add(item6);
        three.add(item7);
        three.add(item8);
        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                select_Dormitory(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item10 = new JMenuItem("退出");
        JMenuItem item11 = new JMenuItem("首页");
        four.add(item11);
        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                huanying(p2);
                p1.repaint();
                p1.validate();
            }
        });
        four.add(item10);
        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(true);
                frame1.setVisible(false);
            }
        });
        panel.add(menu);
        menu.setBounds(0,0,900,100);
    }
    //超级管理员添加宿管信息
    public void addManagement(JPanel panel){
        //学号信息
        JLabel label1 = new JLabel("宿管号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(280,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        //姓名
        JLabel label2 = new JLabel("姓名:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        //年龄
        JLabel label3 = new JLabel("年龄:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(300,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        //密码
        JLabel label4 = new JLabel("密码:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(300,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setText("123456");
        text4.setEditable(false);

        //联系方式
        JLabel label5 = new JLabel("联系方式:");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setEditable(true);

        //宿舍楼
        JLabel label6 = new JLabel("宿舍楼:");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(280,250,100 ,100);
        JTextField text6 = new JTextField();
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setEditable(true);
        JButton bt1 = new JButton("确定");
        panel.add(bt1);
        bt1.setBounds(280,350,110,30);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.equals("")||text2.equals("")||text3.equals("")||text4.equals("")||text5.equals("")||text6.equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                int state = DB.newMa(text1.getText(),text6.getText(),text2.getText(),text3.getText(),text4.getText(),text5.getText());
            if(state==0)
                JOptionPane.showMessageDialog(null,"新建成功","消息提示",JOptionPane.WARNING_MESSAGE);
            else  JOptionPane.showMessageDialog(null,"宿舍楼或宿管号信息错误","消息提示",JOptionPane.WARNING_MESSAGE);

            }
        });
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,350,110,30);
    }
    //超级管理员删除宿管信息
    public void deleManagement(JPanel panel){
        JLabel label5 = new JLabel("宿管号:");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(280,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setEditable(true);

        JButton bt1 = new JButton("确定");
        panel.add(bt1);
        bt1.setBounds(280,300,110,30);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text5.equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                int state = DB.judgeMa(text5.getText());
                if(state==0) {
                    DB.deleMa(text5.getText());
                    JOptionPane.showMessageDialog(null, "删除成功", "消息提示", JOptionPane.WARNING_MESSAGE);
                }
                else  JOptionPane.showMessageDialog(null,"宿管号信息错误","消息提示",JOptionPane.WARNING_MESSAGE);

            }
        });
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,300,110,30);
    }
    //超级管理员查询宿管信息
    public void selectManagement(JPanel panel){
        //panel是跟画布，p1是子画布，专门用来放表格信息
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(150,30,400,30);
        JComboBox<String> bt1 = new JComboBox<String>();//创建一个下拉列表框c1
        bt1.addItem("宿管号");
        bt1.addItem("宿舍楼");
        panel.add(bt1);
        bt1.setBounds(570,30,130,30);
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Management> vct2 = new Vector<Management>();
                p1.removeAll();
               select_Ma(p1,vct2,2,"");
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);
        JButton bt2=new JButton("搜索");
        panel.add(bt2);
        bt2.setBounds(710,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Management> vct2 = new Vector<Management>();
                p1.removeAll();
                select_Ma(p1,vct2,bt1.getSelectedIndex(),text.getText());
                p1.repaint();
                p1.revalidate();
            }
        });
        Vector<Management> vct = new Vector<Management>();
        DB.selectMa(vct,2,"");
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getMno();
            tableData[i][1]=vct.get(i).getMname();
            tableData[i][2]=vct.get(i).getTno();
            tableData[i][3]=vct.get(i).getMtel();
        }
        Object[] columnTitle = {"宿管号","姓名","宿舍楼","电话"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //刷新超级管理员查询宿管的信息
    public void select_Ma(JPanel p1,Vector<Management> vct,int select,String s){
        DB.selectMa(vct,select,s);
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getMno();
            tableData[i][1]=vct.get(i).getMname();
            tableData[i][2]=vct.get(i).getTno();
            tableData[i][3]=vct.get(i).getMtel();
        }
        Object[] columnTitle = {"宿管号","姓名","宿舍楼","电话"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //超级管理员查询宿舍楼信息
    public void selectTno(JPanel panel){
        //panel是跟画布，p1是子画布，专门用来放表格信息
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(150,30,400,30);
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Floor> vct = new Vector<Floor>();
                p1.removeAll();
               refresh_selectTno(p1,vct,2,"");
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);
        JButton bt2=new JButton("搜索");
        panel.add(bt2);
        bt2.setBounds(610,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Floor> vct = new Vector<Floor>();
                p1.removeAll();
                refresh_selectTno(p1,vct,0,text.getText());
                p1.repaint();
                p1.revalidate();
            }
        });
        Vector<Floor> vct = new Vector<Floor>();
        DB.selectTno(vct,2,"");
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getTno();
            tableData[i][1]=vct.get(i).getFno();
            tableData[i][2]=vct.get(i).getDco();
            tableData[i][3]=vct.get(i).getCategory();
        }
        Object[] columnTitle = {"宿舍楼号","楼层数","宿舍个楼","类别"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //刷新超级管理员查询宿舍楼的信息
    public void refresh_selectTno(JPanel p1,Vector<Floor> vct,int select,String s){
        DB.selectTno(vct,select,s);
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getTno();
            tableData[i][1]=vct.get(i).getFno();
            tableData[i][2]=vct.get(i).getDco();
            tableData[i][3]=vct.get(i).getCategory();
        }
        Object[] columnTitle = {"宿舍楼号","楼层数","宿舍个楼","类别"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //超级管理员查询学生的信息
    public void selectStudent(JPanel panel){
        //panel是跟画布，p1是子画布，专门用来放表格信息
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(150,30,400,30);
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Student> vct = new Vector<Student>();
                p1.removeAll();
                refresh_selectStudent(p1,vct,2,"");
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);
        JButton bt2=new JButton("搜索");
        panel.add(bt2);
        bt2.setBounds(610,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Student> vct = new Vector<Student>();
                p1.removeAll();
                refresh_selectStudent(p1,vct,0,text.getText());
                p1.repaint();
                p1.revalidate();
            }
        });
        Vector<Student> vct = new Vector<Student>();
        DB.selectStudent(vct,2,"");
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getSno();
            tableData[i][1]=vct.get(i).getSname();
            tableData[i][2]=vct.get(i).getStel();
            if(DB.judgeisdo(vct.get(i).getSno())==0){
            tableData[i][3]="是";
            }
            else tableData[i][3]="否";
        }
        Object[] columnTitle = {"学号","姓名","联系方式","是否分配宿舍"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //刷新超级管理员查询学生的信息
    public void refresh_selectStudent(JPanel p1,Vector<Student> vct,int select,String s){
        DB.selectStudent(vct,select,s);
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getSno();
            tableData[i][1]=vct.get(i).getSname();
            tableData[i][2]=vct.get(i).getStel();
            if(DB.judgeisdo(vct.get(i).getSno())==0)
                tableData[i][3]="是";
            else tableData[i][3]="否";
        }
        Object[] columnTitle = {"学号","姓名","联系方式","是否分配宿舍"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i <4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //查看学生信息
    //袁可嘉
    void Student_message(JPanel panel){
        //学号信息
        JLabel label1 = new JLabel("学号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(300,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setText(DB.User);
        text1.setEditable(false);

        //姓名
        JLabel label2 = new JLabel("姓名:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setText(DB.getStuname(DB.User));
        text2.setEditable(false);

        //性别
        JLabel label3 = new JLabel("性别:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(300,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setText(DB.getStusex(DB.User));
        text3.setEditable(false);

        //班级
        JLabel label4 = new JLabel("班级:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(300,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setText(DB.getStuclass(DB.User));
        text4.setEditable(false);

        //联系方式
        JLabel label5 = new JLabel("联系方式:");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setText(DB.getStutel(DB.User));
        text5.setEditable(false);

        //专业
        JLabel label6 = new JLabel("专业:");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(300,250,100 ,100);
        JTextField text6 = new JTextField();
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setText(DB.getStumajor(DB.User));
        text6.setEditable(false);

        //密码
        JLabel label7 = new JLabel("密码:");
        panel.add(label7);
        label7.setFont(new Font("Dialog",1,20));
        label7.setBounds(300,300,100 ,100);
        JTextField text7 = new JTextField();
        panel.add(text7);
        text7.setBounds(380,335,110,30);
        text7.setText(DB.getStupass(DB.User));
        text7.setEditable(false);

        //宿舍楼
        JLabel label8 = new JLabel("宿舍楼:");
        panel.add(label8);
        label8.setFont(new Font("Dialog",1,20));
        label8.setBounds(280,350,100 ,100);
        JTextField text8 = new JTextField();
        panel.add(text8);
        text8.setBounds(380,385,110,30);
        text8.setText(DB.getBuding(DB.User));
        text8.setEditable(false);

        //宿舍
        JLabel label9 = new JLabel("宿舍号:");
        panel.add(label9);
        label9.setFont(new Font("Dialog",1,20));
        label9.setBounds(280,400,100 ,100);
        JTextField text9 = new JTextField();
        panel.add(text9);
        text9.setBounds(380,435,110,30);
        text9.setText(DB.getDno(DB.User));
        text9.setEditable(false);

        //床位号
        JLabel label10 = new JLabel("床位号:");
        panel.add(label10);
        label10.setFont(new Font("Dialog",1,20));
        label10.setBounds(280,450,100 ,100);
        JTextField text10 = new JTextField();
        panel.add(text10);
        text10.setBounds(380,485,110,30);
        text10.setText(DB.getBno(DB.User));
        text10.setEditable(false);
    }
    //袁可嘉
    //编辑学生信息
    void Edit_Student_message(JPanel panel){
        //学号信息
        JLabel label1 = new JLabel("学号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(300,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setText(DB.User);
        text1.setEditable(false);

        //姓名
        JLabel label2 = new JLabel("姓名:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setText(DB.getStuname(DB.User));
        text2.setEditable(false);

        //性别
        JLabel label3 = new JLabel("性别:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(300,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setText(DB.getStusex(DB.User));
        text3.setEditable(false);

        //班级
        JLabel label4 = new JLabel("班级:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(300,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setText(DB.getStuclass(DB.User));
        text4.setEditable(false);

        //联系方式
        JLabel label5 = new JLabel("联系方式*");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setText(DB.getStutel(DB.User));
        text5.setEditable(true);

        //专业
        JLabel label6 = new JLabel("专业:");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(300,250,100 ,100);
        JTextField text6 = new JTextField();
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setText(DB.getStumajor(DB.User));
        text6.setEditable(false);

        //密码
        JLabel label7 = new JLabel("密码*");
        panel.add(label7);
        label7.setFont(new Font("Dialog",1,20));
        label7.setBounds(300,300,100 ,100);
        JTextField text7 = new JTextField();
        panel.add(text7);
        text7.setBounds(380,335,110,30);
        text7.setText(DB.getStupass(DB.User));
        text7.setEditable(true);

        //宿舍楼
        JLabel label8 = new JLabel("宿舍楼:");
        panel.add(label8);
        label8.setFont(new Font("Dialog",1,20));
        label8.setBounds(280,350,100 ,100);
        JTextField text8 = new JTextField();
        panel.add(text8);
        text8.setBounds(380,385,110,30);
        text8.setText(DB.getBuding(DB.User));
        text8.setEditable(false);

        //宿舍
        JLabel label9 = new JLabel("宿舍号:");
        panel.add(label9);
        label9.setFont(new Font("Dialog",1,20));
        label9.setBounds(280,400,100 ,100);
        JTextField text9 = new JTextField();
        panel.add(text9);
        text9.setBounds(380,435,110,30);
        text9.setText(DB.getDno(DB.User));
        text9.setEditable(false);

        //床位号
        JLabel label10 = new JLabel("床位号:");
        panel.add(label10);
        label10.setFont(new Font("Dialog",1,20));
        label10.setBounds(280,450,100 ,100);
        JTextField text10 = new JTextField();
        panel.add(text10);
        text10.setBounds(380,485,110,30);
        text10.setText(DB.getBno(DB.User));
        text10.setEditable(false);

        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text5.getText().length()!=11)
                    JOptionPane.showMessageDialog(null,"联系方式不合法","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(text7.getText().length()<6||text7.getText().length()>10)
                    JOptionPane.showMessageDialog(null,"密码应为6-10字符","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.modify_tel(0,DB.User,text5.getText())==0&&DB.modify_pass(0,DB.User,text7.getText())==0)
                    JOptionPane.showMessageDialog(null,"修改成功","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,535,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,535,110,30);

    }
    //查看宿舍成员信息
    void Student_Dormitory_message(JPanel panel){
        Vector<Lodging> vct2 = new Vector<Lodging>();
        DB.Domitory_Studet_Dmessage(vct2,DB.User);
        Vector<Student> vct = new Vector<Student>();
        DB.Domitory_Student_message(vct,DB.User);
        Object[][] tableData = new Object[vct.size()][3];
        for(int i=0;i< vct.size();i++) {
            for(int j=0;j<3;j++) {
                tableData[i][0]=vct.get(i).getSno();
                tableData[i][1]=vct.get(i).getSname();
                tableData[i][2]=vct2.get(i).getBno();
            }
        }
        Object[] columnTitle = {"学号","姓名","床位号"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 3; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(300);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(125,50,600,350);
        panel.setBackground(Color.white);
        panel.setBackground(Color.BLACK);
    }
    //梅傲寒
    //查看宿管信息
    void Student_Management_message(JPanel panel){
        Vector<Management> vct = new Vector<Management>();
        DB.Management_Student_message(vct,DB.User);
        Object[][] tableData = new Object[vct.size()][2];
        for(int i=0;i< vct.size();i++) {
            for(int j=0;j<2;j++) {
                tableData[i][0]=vct.get(i).getMname();
                tableData[i][1]=vct.get(i).getMtel();
            }
        }
        Object[] columnTitle = {"宿管姓名","联系方式"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 2; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(200);
            }
            if(i!=0) {
                column.setPreferredWidth(300);
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(125,50,600,300);
        panel.setBackground(Color.white);
        panel.setBackground(Color.BLACK);
    }
    //梅傲寒
    //物品信息表
    void Item_Message(JPanel panel){
        Vector<Repair_Message> vct = new Vector<Repair_Message>();
        DB.selectItemMessage(vct);
        Object[][] tableData = new Object[vct.size()][2];
        for(int i=0;i< vct.size();i++) {
            for(int j=0;j<2;j++) {
                tableData[i][0]=vct.get(i).getItemnum();
                tableData[i][1]=vct.get(i).getItem();
            }
        }
        Object[] columnTitle = {"修理号","修理物品"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 2; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(200);
            }
            if(i!=0) {
                column.setPreferredWidth(300);
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(125,50,600,500);
        panel.setBackground(Color.white);
        panel.setBackground(Color.BLACK);
    }
    //梅傲寒
    //修理信息
    void repair(JPanel panel){
        JLabel label1 = new JLabel("宿舍楼号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(280,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("宿舍号:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        JLabel label3 = new JLabel("修理号:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(300,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        JLabel label4 = new JLabel("具体情况:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(280,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setEditable(true);

        JLabel label5 = new JLabel("提交日期");
        panel.add(label5);
        final JXDatePicker datepick = new JXDatePicker();
        datepick.setBounds(380,235,110,30);
        panel.add(datepick);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(280,200,100 ,100);


        JLabel label6 = new JLabel("维修编号:");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(280,250,100 ,100);
        JTextField text6 = new JTextField();
        panel.add(text6);
        int n = DB.repair_num();
        String num = String.valueOf(n);
        text6.setText(num);
        text6.setBounds(380,285,110,30);
        text6.setEditable(false);
        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
                String date = formatter.format(datepick.getDate());
                if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals("")||text4.getText().equals("")||date.equals("")||text6.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.repairt(DB.repair_num(), text1.getText(),text2.getText(),text3.getText(),text4.getText(),date)==0){
                    JOptionPane.showMessageDialog(null,"插入成功","消息提示",JOptionPane.WARNING_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null,"插入失败，请检查宿舍楼号/宿舍号和修理号的正确性","消息提示",JOptionPane.WARNING_MESSAGE);
            }

        });
        panel.add(bt1);
        bt1.setBounds(280,385,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,385,110,30);

    }
    //孙婕
    //宿管菜单
    public void Management_menu(JPanel panel,JFrame frame1,JFrame frame2){
        JPanel p1=new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(0,100,900,700);
        ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\dormitory－2\\Dormitory－2\\src\\images\\bg.jpg");
        p2.setLayout(null);
        huanying(p2);
        p1.add(p2);
        p2.setBounds(0,0,900,700);
        JMenuBar menu = new JMenuBar();
        JMenu one = new JMenu("用户");
        JMenu two = new JMenu("个人中心");
        JMenu three = new JMenu("处分");
        JMenu four = new JMenu("学生信息");
        JMenu five = new JMenu("宿舍楼信息");
        JMenu six = new JMenu("报修信息");
        JMenu seven = new JMenu("住宿信息");
        JMenu eight = new JMenu("宿舍信息");
        JMenu nine = new JMenu("外来人员登记");
        JMenu ten = new JMenu("公告栏");
        JMenuItem item22 = new JMenuItem("发布通知");
        JMenuItem item24 = new JMenuItem("查询通知");
        ten.add(item22);
        ten.add(item24);
        item22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
               newMessage(p2);
                p1.repaint();
                p1.validate();
            }
        });
        item24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                selectMessage(p2);
                p1.repaint();
                p1.validate();
            }
        });
        menu.add(one);
        menu.add(two);
        menu.add(three);
        menu.add(four);
        menu.add(five);
        menu.add(six);
        menu.add(seven);
        menu.add(eight);
        menu.add(nine);
        menu.add(ten);
        JMenuItem item20=new JMenuItem("新增人员记录");
        item20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                newOutsiders(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item21=new JMenuItem("查询人员记录");
        nine.add(item20);
        nine.add(item21);
        item21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                selectOutsiders(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item19 = new JMenuItem("查询宿舍");
        eight.add(item19);
        item19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                management_select_Dormitory(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item18 = new JMenuItem("首页");
        item18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
               huanying(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item1=new JMenuItem("退出");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(true);
                frame1.setVisible(false);
            }
        });
        one.add(item1);
        one.add(item18);
        JMenuItem item2=new JMenuItem("查看个人信息");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Management_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item3=new JMenuItem("编辑个人信息");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Edit_Management_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        two.add(item2);
        two.add(item3);
        JMenuItem item4=new JMenuItem("提交处分信息");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                newChufen(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item5=new JMenuItem("查看处分信息");
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                //selectChufen(p2);
                chufen_Message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item6=new JMenuItem("删除处分信息");
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                dechufen(p2);
                p1.repaint();
                p1.validate();
            }
        });
        three.add(item4);
        three.add(item5);
        three.add(item6);
        JMenuItem item7=new JMenuItem("查看学生信息");
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                select_Management_student(p2);
                p1.repaint();
                p1.validate();
            }
        });
        four.add(item7);
        JMenuItem item11=new JMenuItem("查看宿舍楼信息");
        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Floor_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        five.add(item11);
        JMenuItem item12=new JMenuItem("查看报修信息");
        item12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                Repairt_Message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item13=new JMenuItem("删除报修信息");
        item13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                derepair(p2);
                p1.repaint();
                p1.validate();
            }
        });
        six.add(item12);
        six.add(item13);
        JMenuItem item14=new JMenuItem("查看住宿信息");
        item14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                select_Management_Dormitory(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item15=new JMenuItem("修改住宿信息");
        item15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                revise_Dormitory_messsage(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item16=new JMenuItem("增加住宿信息");
        item16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                newLodging(p2);
                p1.repaint();
                p1.validate();
            }
        });
        JMenuItem item17=new JMenuItem("删除住宿信息");
        item17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                ImagePanel p2 = new ImagePanel(900,700,"D:\\IDEA\\project\\Dormitory\\src\\images\\bg.jpg");
                p2.setLayout(null);
                p2.setBounds(0,0,900,700);
                p1.add(p2);
                delete_Dormitory_message(p2);
                p1.repaint();
                p1.validate();
            }
        });
        seven.add(item14);
        seven.add(item15);
        seven.add(item16);
        seven.add(item17);
        panel.add(menu);
        menu.setBounds(0,0,900,100);
    }

    void Management_message(JPanel panel){
        //宿管号信息
        JLabel label1 = new JLabel("宿管号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(280,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setText(DB.User);
        text1.setEditable(false);
        //姓名
        JLabel label2 = new JLabel("姓名:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setText(DB.getManame(DB.User));
        text2.setEditable(false);

        JLabel label3 = new JLabel("年龄:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(300,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setText(DB.getMaage(DB.User));
        text3.setEditable(false);

        JLabel label4 = new JLabel("宿舍楼号:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(260,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setText(DB.getMatno(DB.User));
        text4.setEditable(false);
        //联系方式
        JLabel label5 = new JLabel("联系方式:");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setText(DB.getMatel(DB.User));
        text5.setEditable(false);
        //密码
        JLabel label6 = new JLabel("密码:");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(300,250,100 ,100);
        JTextField text6 = new JTextField();
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setText(DB.getMapass(DB.User));
        text6.setEditable(false);
    }
    //编辑宿管个人信息
    //梅傲寒
    void Edit_Management_message(JPanel panel){
        //学号信息
        JLabel label1 = new JLabel("宿管号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(280,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setText(DB.User);
        text1.setEditable(false);

        //姓名
        JLabel label2 = new JLabel("姓名:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setText(DB.getManame(DB.User));
        text2.setEditable(false);

        JLabel label3 = new JLabel("年龄:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(300,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setText(DB.getMaage(DB.User));
        text3.setEditable(false);

        JLabel label4 = new JLabel("宿舍楼号:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(260,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setText(DB.getMatno(DB.User));
        text4.setEditable(false);

        //联系方式
        JLabel label5 = new JLabel("联系方式*");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setText(DB.getMatel(DB.User));
        text5.setEditable(true);

        //密码
        JLabel label7 = new JLabel("密码*");
        panel.add(label7);
        label7.setFont(new Font("Dialog",1,20));
        label7.setBounds(300,250,100 ,100);
        JTextField text7 = new JTextField();
        panel.add(text7);
        text7.setBounds(380,285,110,30);
        text7.setText(DB.getMapass(DB.User));
        text7.setEditable(true);
        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text5.getText().length()!=11)
                    JOptionPane.showMessageDialog(null,"联系方式不合法","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(text7.getText().length()<6||text7.getText().length()>10)
                    JOptionPane.showMessageDialog(null,"密码应为6-10字符","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.modify_tel(1,DB.User,text5.getText())==0&&DB.modify_pass(1,DB.User,text7.getText())==0)
                    JOptionPane.showMessageDialog(null,"修改成功","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,535,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,535,110,30);
    }

    //新增处分信息
    //梅傲寒
    void newChufen(JPanel panel){
        JLabel label1 = new JLabel("处分编号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("宿舍楼号:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(260,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        JLabel label3 = new JLabel("宿舍号:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(280,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        JLabel label4 = new JLabel("处分原因:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(260,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setEditable(true);


        JLabel label5 = new JLabel("处分日期：");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,110 ,100);
        final JXDatePicker datepick = new JXDatePicker();
        datepick.setBounds(380,235,110,30);
        panel.add(datepick);

        JLabel label6 = new JLabel("处分等级：");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(260,250,110 ,100);
        JComboBox<String> text6 = new JComboBox<String>();//创建一个下拉列表框c1
        text6.addItem("-选择等级-");
        text6.addItem("通报批评");       // 创建4个下拉选项
        text6.addItem("警告处分");
        text6.addItem("严重处分");
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setEditable(true);
        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
                String date = formatter.format(datepick.getDate());
                System.out.println((String)text6.getSelectedItem());
                String s1=(String)text6.getSelectedItem();
                if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals("")||text4.getText().equals("")||date.equals("")||s1.equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.newChufen(text1.getText(),text2.getText(),text3.getText(),text4.getText(),date,(String)text6.getSelectedItem())==0)
                    JOptionPane.showMessageDialog(null,"插入成功","消息提示",JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,"插入失败，请检查处分号，宿舍楼号和宿舍号的正确性","消息提示",JOptionPane.WARNING_MESSAGE);
                }
            });
	        panel.add(bt1);
	        bt1.setBounds(280,385,110,30);
            JButton bt2 = new JButton("取消");
	        panel.add(bt2);
	        bt2.setBounds(480,385,110,30);
            bt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    //学生查看自己宿舍的处分信息
    //孙婕
    void select_StudentChufen(JPanel panel){
        Vector<edchufen> vct = new Vector<edchufen>();
        DB.StudentselectchufenMessage(vct,DB.User);
        Object[][] tableData = new Object[vct.size()][3];
        for(int i=0;i< vct.size();i++) {
                tableData[i][0]=vct.get(i).getReason();
                tableData[i][1]=vct.get(i).getAdate();
                tableData[i][2]=vct.get(i).getLevel();
        }
        Object[] columnTitle = {"处分原因","日期","处分等级"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 3; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            if (i == 1) {
                column.setPreferredWidth(300);
            }
            if(i!=1) {
                column.setPreferredWidth(200);
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(175,50,500,300);
        panel.setBackground(Color.white);
        panel.setBackground(Color.BLACK);
    }

    //宿管查看学生信息
    //袁可嘉
    void select_Management_student(JPanel panel){
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(150,30,350,30);

        JLabel label1 = new JLabel("请按学号搜索");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(520,30,160,30);

        String tno=DB.getMatno(DB.User);
        JButton bt2=new JButton("搜索");
        panel.add(bt2);
        bt2.setBounds(700,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Student> vct2 = new Vector<Student>();
                int state=DB.Management_student_Search(vct2,text.getText(),tno);
                System.out.println(vct2.size());
                if(state==2)
                    JOptionPane.showMessageDialog(null,"所搜学生不在此楼中","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==0)
                    JOptionPane.showMessageDialog(null,"所搜学生不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==1)
                {
                    p1.removeAll();
                    refresh_student_table(vct2,p1);
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Student> vct2 = new Vector<Student>();
                DB.Management_student(vct2,tno);
                p1.removeAll();
                refresh_student_table(vct2,p1);
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);

        Vector<Student> vct = new Vector<Student>();
        DB.Management_student(vct,tno);
        Object[][] tableData = new Object[vct.size()][6];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getSno();
            tableData[i][1]=vct.get(i).getSname();
            tableData[i][2]=vct.get(i).getSsex();
            tableData[i][3]=vct.get(i).getSclass();
            tableData[i][4]=vct.get(i).getStel();
            tableData[i][5]=vct.get(i).getSdept();
        }
        Object[] columnTitle = {"学号","姓名","性别","班级","班级","专业"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);//以下设置表格列宽
        for (int i = 0; i < 6; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //刷新学生表
    //袁可嘉
    public void refresh_student_table( Vector<Student> vct ,JPanel p1)
    {
        Object[][] tableData = new Object[vct.size()][6];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getSno();
            tableData[i][1]=vct.get(i).getSname();
            tableData[i][2]=vct.get(i).getSsex();
            tableData[i][3]=vct.get(i).getSclass();
            tableData[i][4]=vct.get(i).getStel();
            tableData[i][5]=vct.get(i).getSdept();
        }
        Object[] columnTitle = {"学号","姓名","性别","班级","班级","专业"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 6; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //宿管查看报修信息
    //梅傲寒
    void Repairt_Message(JPanel panel){
        Vector<edrepairt> vct = new Vector<edrepairt>();
        DB.selectRapairMessage(vct);
        Object[][] tableData = new Object[vct.size()][6];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getNumber();
            tableData[i][1]=vct.get(i).getTno();
            tableData[i][2]=vct.get(i).getDno();
            tableData[i][3]=vct.get(i).getItemnum();
            tableData[i][4]=vct.get(i).getDes();
            tableData[i][5]=vct.get(i).getDate();
        }
        Object[] columnTitle = {"报修编号","宿舍楼号","宿舍号","物品号","报修原因","提交日期"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 6; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(300);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(125,50,600,500);
        panel.setBackground(Color.white);
        panel.setBackground(Color.BLACK);
    }
    //宿管删除报修信息
    //梅傲寒
    void derepair(JPanel panel){
        JLabel label1 = new JLabel("报修编号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,200,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,235,110,30);
        text1.setEditable(true);
        //确认
        JButton bt1 = new JButton("删除");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.judgerepair(text1.getText())==0) {
                    DB.derepair(text1.getText());
                    JOptionPane.showMessageDialog(null, "删除成功", "消息提示", JOptionPane.WARNING_MESSAGE);

                } else
                    JOptionPane.showMessageDialog(null,"删除失败，请检查","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,300,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,300,110,30);
    }
    //宿管查看处分信息
    //梅傲寒
    void chufen_Message(JPanel panel){
        Vector<edchufen> vct = new Vector<edchufen>();
        DB.selectchufenMessage(vct);
        Object[][] tableData = new Object[vct.size()][6];
        for(int i=0;i< vct.size();i++) {
            for(int j=0;j<6;j++) {
                tableData[i][0]=vct.get(i).getNumber();
                tableData[i][1]=vct.get(i).getTno();
                tableData[i][2]=vct.get(i).getDno();
                tableData[i][3]=vct.get(i).getReason();
                tableData[i][4]=vct.get(i).getAdate();
                tableData[i][5]=vct.get(i).getLevel();
            }
        }
        Object[] columnTitle = {"处分编号","宿舍楼号","宿舍号","处分原因","处分日期","处分等级"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 6; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(300);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(125,50,600,500);
        panel.setBackground(Color.white);
        panel.setBackground(Color.BLACK);
    }
    //宿管删除处分信息
    //梅傲寒
    void dechufen(JPanel panel){
        JLabel label1 = new JLabel("处分编号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,200,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,235,110,30);
        text1.setEditable(true);
        //确认
        JButton bt1 = new JButton("删除");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.judgechufen(text1.getText())==0) {
                    DB.dechufen(text1.getText());
                    JOptionPane.showMessageDialog(null, "删除成功", "消息提示", JOptionPane.WARNING_MESSAGE);
                }
                else if(DB.judgechufen(text1.getText())==-1)
                    JOptionPane.showMessageDialog(null,"删除失败，处分不存在","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,300,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,300,110,30);
    }
    //查看宿舍楼
    //梅傲寒
    void Floor_message(JPanel panel){
        String tno=DB.getMatno(DB.User);
        JLabel label1 = new JLabel("宿舍楼号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setText(tno);
        text1.setEditable(false);

        JLabel label2 = new JLabel("宿舍个数:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(260,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setText(DB.getMaDco(tno));
        text2.setEditable(false);

        JLabel label3 = new JLabel("楼层数:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(280,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setText(DB.getMaFno(tno));
        text3.setEditable(false);

        JLabel label4 = new JLabel("宿舍类别:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(260,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setText(DB.getMaCategory(tno));
        text4.setEditable(false);
    }


    //欢迎菜单页面
    void huanying(JPanel panel){
        JLabel label = new JLabel("欢迎来到宿舍管理系统");
        panel.add(label);
        label.setBounds(250,100,400,100);
        label.setFont(new Font("宋体",1,30));
        ImageIcon img = new ImageIcon("D:\\IDEA\\project\\dormitory－1\\Dormitory－1\\src\\images\\主页.jpg");
        img.setImage(img.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));

        JLabel label1= new JLabel();
        label1.setIcon(img);//文件路径
        label1.setBounds(270,200,300,300);
        panel.add(label1);
    }
    //超级管理员查询宿舍
    //梅傲寒
    public void select_Dormitory(JPanel panel){
        //panel是跟画布，p1是子画布，专门用来放表格信息
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(150,30,400,30);
        JComboBox<String> bt1 = new JComboBox<String>();//创建一个下拉列表框c1
        bt1.addItem("搜索方式");
        bt1.addItem("按宿舍楼搜");
        bt1.addItem("按宿舍号搜");
        panel.add(bt1);
        bt1.setBounds(570,30,130,30);
        JButton bt2=new JButton("搜索");
        panel.add(bt2);
        bt2.setBounds(710,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Dormitory> vct2 = new Vector<Dormitory>();
                int state=DB.MA_Lodging_Search(bt1.getSelectedIndex(),vct2,text.getText());
               if(state==0)
                    JOptionPane.showMessageDialog(null,"所搜宿舍楼或宿舍不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==1)
                {
                    p1.removeAll();
                    refresh_Dormitory(vct2,p1);
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Dormitory> vct2 = new Vector<Dormitory>();
                DB.selectDormitory(vct2);
                p1.removeAll();
                refresh_Dormitory(vct2,p1);
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);
        Vector<Dormitory> vct = new Vector<Dormitory>();
        DB.selectDormitory(vct);
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getTno();
            tableData[i][1]=vct.get(i).getDno();
            tableData[i][2]=vct.get(i).getDnum();
            tableData[i][3]=vct.get(i).getDcount();
        }
        Object[] columnTitle = {"宿舍楼","宿舍号","宿舍人数","床位数"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //宿管查询宿舍
    //梅傲寒
    public void management_select_Dormitory(JPanel panel){
       //panel是跟画布，p1是子画布，专门用来放表格信息
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(150,30,400,30);
        JComboBox<String> bt1 = new JComboBox<String>();//创建一个下拉列表框c1
        bt1.addItem("搜索信息");
        bt1.addItem("宿舍信息");
        bt1.addItem("宿舍成员");
        panel.add(bt1);
        bt1.setBounds(570,30,130,30);
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Dormitory> vct2 = new Vector<Dormitory>();
                DB.management_Lodging_Search(DB.User,vct2);
                p1.removeAll();
                refresh_Dormitory(vct2,p1);
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);
        JButton bt2=new JButton("搜索");
        panel.add(bt2);
        bt2.setBounds(710,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Dormitory> vct2 = new Vector<Dormitory>();
                if (bt1.getSelectedIndex() == 1) {
                    int state = DB.Management_searchtDor(vct2, text.getText(), DB.User);
                    if (state == 0)
                        JOptionPane.showMessageDialog(null, "所搜宿舍不存在", "消息提示", JOptionPane.WARNING_MESSAGE);
                    else if (state == 1) {
                        p1.removeAll();
                        refresh_Dormitory(vct2, p1);
                        p1.repaint();
                        p1.revalidate();
                    }
                }
                else if(bt1.getSelectedIndex()==2){
                    p1.removeAll();
                    refresh_Student_Dormitory(p1,text.getText(),DB.getMatno(DB.User));
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        Vector<Dormitory> vct = new Vector<Dormitory>();
        DB.selectDormitory(vct);
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getTno();
            tableData[i][1]=vct.get(i).getDno();
            tableData[i][2]=vct.get(i).getDnum();
            tableData[i][3]=vct.get(i).getDcount();
        }
        Object[] columnTitle = {"宿舍楼","宿舍号","宿舍人数","床位数"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //梅傲寒
    //刷新查询宿舍页面
    public void refresh_Dormitory( Vector<Dormitory> vct ,JPanel p1)
    {
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getTno();
            tableData[i][1]=vct.get(i).getDno();
            tableData[i][2]=vct.get(i).getDnum();
            tableData[i][3]=vct.get(i).getDcount();
        }
        Object[] columnTitle = {"宿舍楼","宿舍号","宿舍人数","床位数"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //宿管刷新查询学生信息页面
    //袁可嘉
    public void refresh_Student_Dormitory(JPanel panel,String Dno,String Tno){
        Vector<Lodging> vct2 = new Vector<Lodging>();
        Vector<Student> vct1 = new Vector<Student>();
        DB.Management_Student_Dor(vct1,Dno,Tno,vct2);
        System.out.println(vct1.size());
        Object[][] tableData = new Object[vct1.size()][3];
        for(int i=0;i< vct1.size();i++) {
                tableData[i][0]=vct1.get(i).getSno();
                tableData[i][1]=vct1.get(i).getSname();
                tableData[i][2]=vct2.get(i).getBno();
        }
        Object[] columnTitle = {"学号","姓名","床位号"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 3; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(300);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        panel.setBackground(Color.white);
        panel.setBackground(Color.BLACK);
    }
    //孙婕
    //超级管理员新建宿舍信息
    void newDor(JPanel panel){
        JLabel label1 = new JLabel("宿舍楼:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(280,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("宿舍号:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(280,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        JLabel label3 = new JLabel("宿舍人数:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(260,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setText("0");
        text3.setEditable(false);

        JLabel label4 = new JLabel("床位数:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(280,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setEditable(true);

        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text4.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.newDor(text1.getText(),text2.getText(),text3.getText(),text4.getText())==0)
                    JOptionPane.showMessageDialog(null,"插入成功","消息提示",JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,"插入失败，该宿舍已存在","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,250,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,250,110,30);
    }
    //孙婕
    //超级管理删除宿舍信息
    void deDor(JPanel panel){
        //宿舍楼号
        JLabel label1 = new JLabel("宿舍楼号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,150,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,185,110,30);
        text1.setEditable(true);
        //宿舍号
        JLabel label2 = new JLabel("宿舍号:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(280,200,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,235,110,30);
        text2.setEditable(true);
        //确认
        JButton bt1 = new JButton("删除");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.deDor(text1.getText(),text2.getText())==0)
                    JOptionPane.showMessageDialog(null,"删除成功","消息提示",JOptionPane.WARNING_MESSAGE);
                else  if(DB.deDor(text1.getText(),text2.getText())==-1)
                    JOptionPane.showMessageDialog(null,"删除失败,可能是该宿舍有学生或不存在","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,300,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,300,110,30);
    }
    //超级管理增加学生信息
    //袁可嘉
    void newStudent(JPanel panel){
        JLabel label1 = new JLabel("学号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(300,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("姓名:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        JLabel label3 = new JLabel("性别:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(300,100,100 ,100);
        JComboBox<String> text3 = new JComboBox<String>();//创建一个下拉列表框c1
        text3.addItem("男");
        text3.addItem("女");       // 创建4个下拉选
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        JLabel label4 = new JLabel("班级:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(300,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setEditable(true);

        JLabel label5 = new JLabel("电话：");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(300,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setEditable(true);

        JLabel label6 = new JLabel("专业：");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(300,250,100 ,100);
        JTextField text6 = new JTextField();
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setEditable(true);

        JLabel label7 = new JLabel("密码：");
        panel.add(label7);
        label7.setFont(new Font("Dialog",1,20));
        label7.setBounds(300,300,100 ,100);
        JTextField text7 = new JTextField();
        panel.add(text7);
        text7.setBounds(380,335,110,30);
        text7.setText("123456");
        text7.setEditable(false);
        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text4.getText().equals("")||text5.getText().equals("")||text6.getText().equals("")||text7.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.newStudent(text1.getText(),text2.getText(),text3.getSelectedIndex(),text4.getText(),text5.getText(),text6.getText(),text7.getText())==0)
                    JOptionPane.showMessageDialog(null,"插入成功","消息提示",JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,"插入失败，请检查学号的正确性","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,535,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,535,110,30);
    }
    //超级管理删除学生
    //袁可嘉
    void destudent(JPanel panel){
        JLabel label1 = new JLabel("学号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(300,200,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,235,110,30);
        text1.setEditable(true);
        //确认
        JButton bt1 = new JButton("删除");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.judge_student(text1.getText())==0) {
                    JOptionPane.showMessageDialog(null, "删除成功", "消息提示", JOptionPane.WARNING_MESSAGE);
                DB.Management_delete_student(text1.getText());
                }  else if(DB.judge_student(text1.getText())==-1)
                    JOptionPane.showMessageDialog(null,"删除失败,(可能是此学生不存在)","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,300,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,300,110,30);
    }
    //超级管理增加宿舍楼
    //梅傲寒
    void newFloor(JPanel panel){
        JLabel label1 = new JLabel("宿舍楼号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("宿舍个数:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(260,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        JLabel label3 = new JLabel("楼层号:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(280,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        JLabel label4 = new JLabel("宿舍类别:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(260,150,100 ,100);
        JComboBox<String> text4 = new JComboBox<String>();//创建一个下拉列表框c1
        text4.addItem("男寝");
        text4.addItem("女寝");
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setEditable(true);

        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.newFloor(text1.getText(),text2.getText(),text3.getText(),text4.getSelectedIndex())==0)
                    JOptionPane.showMessageDialog(null,"插入成功","消息提示",JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,"插入失败,请检查宿舍楼号的正确性","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,535,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,535,110,30);
    }
    //超级管理删除宿舍楼
    void defloor(JPanel panel){
        JLabel label1 = new JLabel("宿舍楼号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,200,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,235,110,30);
        text1.setEditable(true);
        //确认
        JButton bt1 = new JButton("删除");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(DB.Management_delete_floor(text1.getText())==0)
                    JOptionPane.showMessageDialog(null,"删除成功","消息提示",JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,"删除失败,(可能是此宿舍楼中宿舍信息未完全删除)","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(bt1);
        bt1.setBounds(280,300,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,300,110,30);
    }




    //宿管查看住宿信息
    void select_Management_Dormitory(JPanel panel){
        //panel是跟画布，p1是子画布，专门用来放表格信息
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(150,30,400,30);
        JComboBox<String> bt1 = new JComboBox<String>();//创建一个下拉列表框c1
        bt1.addItem("搜索方式");
        bt1.addItem("按学号搜");       // 创建4个下拉选项
        bt1.addItem("按宿舍搜");
        panel.add(bt1);
        bt1.setBounds(570,30,110,30);
        JButton bt2=new JButton("搜索");
        panel.add(bt2);
        bt2.setBounds(700,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Lodging> vct2 = new Vector<Lodging>();
                int state=DB.Management_Lodging_Search(bt1.getSelectedIndex(),vct2,text.getText(),DB.User);
                System.out.println(vct2.size());
                if(state==2)
                    JOptionPane.showMessageDialog(null,"所搜学生或宿舍不在此楼中","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==0)
                    JOptionPane.showMessageDialog(null,"所搜学生或宿舍不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==1)
                {
                    p1.removeAll();
                    refresh_Dormitory_table(vct2,p1);
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Lodging> vct2 = new Vector<Lodging>();
                DB.Management_Lodging(vct2,DB.User);
                p1.removeAll();
                refresh_Dormitory_table(vct2,p1);
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);
        Vector<Lodging> vct = new Vector<Lodging>();
        DB.Management_Lodging(vct,DB.User);
        Object[][] tableData = new Object[vct.size()][6];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getSno();
            tableData[i][1]=vct.get(i).getTno();
            tableData[i][2]=vct.get(i).getDno();
            tableData[i][3]=vct.get(i).getBno();
            tableData[i][4]=vct.get(i).getStay();
            tableData[i][5]=vct.get(i).getIsleave();
        }
        Object[] columnTitle = {"学号","宿舍楼","宿舍号","床位号","入住时间","是否离校"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 6; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //刷新住宿表
    public void refresh_Dormitory_table( Vector<Lodging> vct ,JPanel p1)
    {
        Object[][] tableData = new Object[vct.size()][6];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getSno();
            tableData[i][1]=vct.get(i).getTno();
            tableData[i][2]=vct.get(i).getDno();
            tableData[i][3]=vct.get(i).getBno();
            tableData[i][4]=vct.get(i).getStay();
            tableData[i][5]=vct.get(i).getIsleave();
        }
        Object[] columnTitle = {"学号","宿舍楼","宿舍号","床位号","入住时间","是否离校"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 6; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //增加住宿信息
    void newLodging(JPanel panel){
        JLabel label1 = new JLabel("学号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(300,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("宿舍楼号:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(260,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setText(DB.getMatno(DB.User));
        text2.setEditable(false);

        JLabel label3 = new JLabel("宿舍号:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(280,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        JLabel label4 = new JLabel("床位号:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(280,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setEditable(true);

        JLabel label5 = new JLabel("入住时间:");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,100 ,100);
        final JXDatePicker datepick = new JXDatePicker();
        datepick.setBounds(380,235,110,30);
        panel.add(datepick);

        JLabel label6 = new JLabel("是否离校:");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(260,250,100 ,100);
        JComboBox<String> text6 = new JComboBox<String>();//创建一个下拉列表框c1
        text6.addItem("未离校");
        text6.addItem("已离校");
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setEditable(true);
        //确认
        JButton bt1 = new JButton("确定");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int state= DB.judgeLodging(text1.getText(),text2.getText(),text3.getText(),text4.getText());
                if(state==0)
                    JOptionPane.showMessageDialog(null,"学号不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==1)
                    JOptionPane.showMessageDialog(null,"该宿舍不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==2)
                    JOptionPane.showMessageDialog(null,"该宿舍已满","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==3)
                    JOptionPane.showMessageDialog(null,"该床位已被分配","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==4) {
                    java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
                    String date = formatter.format(datepick.getDate());
                    DB.newLodging(text1.getText(),text2.getText(),text3.getText(),text4.getText(),date,text6.getSelectedIndex());
                    JOptionPane.showMessageDialog(null,"创建成功","消息提示",JOptionPane.WARNING_MESSAGE);
                }
            }


        });
        panel.add(bt1);
        bt1.setBounds(280,535,110,30);
        JButton bt2 = new JButton("取消");
        panel.add(bt2);
        bt2.setBounds(480,535,110,30);
    }
    //删除住宿信息
    public void delete_Dormitory_message(JPanel panel){
        //panel是跟画布，p1是子画布，专门用来放表格信息
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(25,80,800,400);
        JTextField text = new JTextField(10);
        panel.add(text);
        text.setBounds(90,30,400,30);
        JComboBox<String> bt1 = new JComboBox<String>();//创建一个下拉列表框c1
        bt1.addItem("删除方式");
        bt1.addItem("按学号删");       // 创建4个下拉选项
        bt1.addItem("按宿舍删");
        panel.add(bt1);
        bt1.setBounds(590,30,110,30);
        JButton bt2=new JButton("查看");
        panel.add(bt2);
        bt2.setBounds(510,30,60,30);
        JButton bt4 = new JButton("删除");
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Lodging> vct2 = new Vector<Lodging>();
                int state=DB.Management_Lodging_Search(bt1.getSelectedIndex(),vct2,text.getText(),DB.User);
                DB.Management_delete_Lodging(vct2);
                p1.removeAll();
                refresh_Dormitory_table(vct2,p1);
                p1.repaint();
                p1.revalidate();

            }
        });
        panel.add(bt4);
        bt4.setBounds(720,30,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Lodging> vct2 = new Vector<Lodging>();
                int state=DB.Management_Lodging_Search(bt1.getSelectedIndex(),vct2,text.getText(),DB.User);
                if(state==2)
                    JOptionPane.showMessageDialog(null,"所搜学生或宿舍不在此楼中","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==0)
                    JOptionPane.showMessageDialog(null,"所搜学生或宿舍不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==1)
                {
                    p1.removeAll();
                    refresh_Dormitory_table(vct2,p1);
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        JButton bt3 = new JButton("查看所有信息");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Lodging> vct2 = new Vector<Lodging>();
                DB.Management_Lodging(vct2,DB.User);
                p1.removeAll();
                refresh_Dormitory_table(vct2,p1);
                p1.repaint();
                p1.revalidate();
            }
        });
        panel.add(bt3);
        bt3.setBounds(300,500,200,40);

        Vector<Lodging> vct = new Vector<Lodging>();
        DB.Management_Lodging(vct,DB.User);
        Object[][] tableData = new Object[vct.size()][6];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getSno();
            tableData[i][1]=vct.get(i).getTno();
            tableData[i][2]=vct.get(i).getDno();
            tableData[i][3]=vct.get(i).getBno();
            tableData[i][4]=vct.get(i).getStay();
            tableData[i][5]=vct.get(i).getIsleave();
        }
        Object[] columnTitle = {"学号","宿舍楼","宿舍号","床位号","入住时间","是否离校"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 6; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,800,400);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //修改住宿表信息
    public void revise_Dormitory_messsage(JPanel panel){
        //学号信息
        JLabel label1 = new JLabel("学号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(300,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("宿舍楼号:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(260,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(false);

        //宿舍信息
        JLabel label3 = new JLabel("宿舍号:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(280,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        //床位号
        JLabel label4 = new JLabel("床位号:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(280,150,100 ,100);
        JTextField text4 = new JTextField();
        panel.add(text4);
        text4.setBounds(380,185,110,30);
        text4.setEditable(true);

        //入住时间
        JLabel label5 = new JLabel("入住时间:");
        panel.add(label5);
        label5.setFont(new Font("Dialog",1,20));
        label5.setBounds(260,200,100 ,100);
        JTextField text5 = new JTextField();
        panel.add(text5);
        text5.setBounds(380,235,110,30);
        text5.setEditable(false);

        //是否离校
        JComboBox<String> text6 = new JComboBox<String>();//创建一个下拉列表框c1
        text6.addItem("未离校");
        text6.addItem("已离校");       // 创建4个下拉选项
        JLabel label6 = new JLabel("是否离校:");
        panel.add(label6);
        label6.setFont(new Font("Dialog",1,20));
        label6.setBounds(260,250,100 ,100);
        panel.add(text6);
        text6.setBounds(380,285,110,30);
        text6.setEditable(true);

        JButton bt1 = new JButton("确定");
        panel.add(bt1);
        bt1.setBounds(520,35,60,30);
        bt1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int state=DB.Sno_state(text1.getText(),DB.User);
                if(state==0)
                    JOptionPane.showMessageDialog(null,"学生不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(state==1){
                    text3.setText(DB.Lodging_Dno(text1.getText()));
                    text2.setText(DB.Lodging_Floor(text1.getText()));
                    text4.setText(DB.Lodging_Bno(text1.getText()));
                    text5.setText(DB.Lodging_Stay(text1.getText()).substring(0,10));
                    if(DB.Lodging_isleave(text1.getText()).equals("0"))
                        text6.setSelectedIndex(0);
                    else text6.setSelectedIndex(1);}
                else if(state==1)
                    JOptionPane.showMessageDialog(null,"该生不属于此宿舍楼","消息提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        //修改床位号按钮
        JButton bt2 = new JButton("修改");
        panel.add(bt2);
        bt2.setBounds(520,185,60,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Bno_state = DB.Dormitory_state(DB.User,text3.getText(),text4.getText());
                if(Bno_state==0)
                    JOptionPane.showMessageDialog(null,"宿舍不存在","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(Bno_state==1)
                    JOptionPane.showMessageDialog(null,"宿舍没空床","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(Bno_state==2)
                    JOptionPane.showMessageDialog(null,"该床位已分配","消息提示",JOptionPane.WARNING_MESSAGE);
                else if(Bno_state==3) {
                    DB.reverse_Dno_Bno(text3.getText(),text4.getText(),text1.getText());
                    JOptionPane.showMessageDialog(null, "床位信息已修改", "消息提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //修改离校状态
        JButton bt3 = new JButton("修改");
        panel.add(bt3);
        bt3.setBounds(520,285,60,30);
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB.reverse_isleave(text6.getSelectedIndex(),text1.getText());
                JOptionPane.showMessageDialog(null, "离校状态已修改", "消息提示", JOptionPane.WARNING_MESSAGE);
            }
        });
        //取消按钮
        JButton bt4 = new JButton("取消");
        panel.add(bt4);
        bt4.setBounds(390,385,80,40);
    }
    //增加外来人员信息
    void newOutsiders(JPanel panel){
        JLabel label1 = new JLabel("外来编号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("姓名:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(300,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        JLabel label3 = new JLabel("外来原因:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(260,100,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(380,135,110,30);
        text3.setEditable(true);

        JLabel label4 = new JLabel("日期:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(300,150,100 ,100);
        final JXDatePicker datepick = new JXDatePicker();
        datepick.setBounds(380,185,110,30);
        panel.add(datepick);

        //确认
        JButton bt1 = new JButton("确定");
        panel.add(bt1);
        bt1.setBounds(400,250,65,30);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(text1.getText());
                java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
                String date = formatter.format(datepick.getDate());
                if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals("")||date.equals("")){
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
            }
                else {
                    DB.newOutsiders(num, text2.getText(), text3.getText(), date,DB.User);
                    JOptionPane.showMessageDialog(null, "建立成功", "消息提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    //查询外来人员信息
    void selectOutsiders(JPanel panel){
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(125,155,600,350);
        JLabel label1 = new JLabel("年份:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(150,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(210,35,100,30);

        JLabel label2 = new JLabel("月份:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(350,0,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(410,35,100,30);

        JLabel label3 = new JLabel("日期:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(550,0,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(610,35,100,30);

        JButton bt1 = new JButton("查询");
        panel.add(bt1);
        bt1.setBounds(290,95,85,40);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else {
                    p1.removeAll();
                    refreshOutsiders(p1, text1.getText(), text2.getText(), text3.getText());
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        JButton bt2 = new JButton("删除");
        panel.add(bt2);
        bt2.setBounds(500,95,85,40);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else {
                Vector<Outsiders> vct = new Vector<Outsiders>();
                DB.selectOutsiders(text1.getText(), text2.getText(), text3.getText(),vct);
                DB.deleteOutsiders(vct);
                JOptionPane.showMessageDialog(null,"删除成功","消息提示",JOptionPane.WARNING_MESSAGE);
                }

            }
        });


        Vector<Outsiders> vct = new Vector<Outsiders>();
        DB.selectallOutsiders(vct);
        Object[][] tableData = new Object[vct.size()][5];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getOnum();
            tableData[i][1]=vct.get(i).getOname();
            tableData[i][2]=vct.get(i).getOreason();
            tableData[i][3]=vct.get(i).getOdate();
            tableData[i][4]=vct.get(i).getOTno();
        }
        Object[] columnTitle = {"编号","姓名","原因","日期","宿舍楼"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,600,350);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);

        JButton bt3 = new JButton("查看所有信息");
        panel.add(bt3);
        bt3.setBounds(350,530,150,40);
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.removeAll();
                Vector<Outsiders> vct = new Vector<Outsiders>();
                DB.selectallOutsiders(vct);
                Object[][] tableData = new Object[vct.size()][5];
                for(int i=0;i< vct.size();i++) {
                    tableData[i][0]=vct.get(i).getOnum();
                    tableData[i][1]=vct.get(i).getOname();
                    tableData[i][2]=vct.get(i).getOreason();
                    tableData[i][3]=vct.get(i).getOdate();
                    tableData[i][4]=vct.get(i).getOTno();
                }
                Object[] columnTitle = {"编号","姓名","原因","日期","宿舍楼"};
                JTable table = new JTable(tableData, columnTitle);
                JTableHeader head = table.getTableHeader(); // 创建表格标题对象
                head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
                head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
                table.setFont(new Font("仿宋", Font.PLAIN, 20));
                table.setRowHeight(50);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
                for (int i = 0; i < 4; i++) {
                    TableColumn column = table.getColumnModel().getColumn(i);
                    column.setPreferredWidth(100);
                }
                JScrollPane scrollPane = new JScrollPane(table);
                p1.add(scrollPane);
                scrollPane.setBounds(0,0,600,350);
                p1.setBackground(Color.white);
                p1.setBackground(Color.BLACK);
                p1.repaint();
                p1.revalidate();
            }
        });
    }
    //刷新外来人员信息
    public void refreshOutsiders(JPanel p1,String year,String month,String date){
        Vector<Outsiders> vct = new Vector<Outsiders>();
        DB.selectOutsiders(year,month,date,vct);
        Object[][] tableData = new Object[vct.size()][5];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getOnum();
            tableData[i][1]=vct.get(i).getOname();
            tableData[i][2]=vct.get(i).getOreason();
            tableData[i][3]=vct.get(i).getOdate();
            tableData[i][4]=vct.get(i).getOTno();
        }
        Object[] columnTitle = {"编号","姓名","原因","日期","宿舍楼"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,600,350);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }

    //宿管发布通知
    public void newMessage(JPanel panel){
        JLabel label1 = new JLabel("通知编号:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(260,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(380,35,110,30);
        text1.setEditable(true);

        JLabel label2 = new JLabel("通知信息:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(260,50,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(380,85,110,30);
        text2.setEditable(true);

        JLabel label4 = new JLabel("日期:");
        panel.add(label4);
        label4.setFont(new Font("Dialog",1,20));
        label4.setBounds(300,100,100 ,100);
        final JXDatePicker datepick = new JXDatePicker();
        datepick.setBounds(380,135,110,30);
        panel.add(datepick);
        //确认
        JButton bt1 = new JButton("确定");
        panel.add(bt1);
        bt1.setBounds(400,190,65,30);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(text1.getText());
                java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
                String date = formatter.format(datepick.getDate());
                if(text1.getText().equals("")||text2.getText().equals("")||date.equals("")){
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int select=DB.newMessage(num, text2.getText(), date,DB.User);
                    if(select==0)
                        JOptionPane.showMessageDialog(null, "建立成功", "消息提示", JOptionPane.WARNING_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "插入异常", "消息提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    //宿管查询通知
    public void selectMessage(JPanel panel){
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(125,155,600,350);
        JLabel label1 = new JLabel("年份:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(150,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(210,35,100,30);

        JLabel label2 = new JLabel("月份:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(350,0,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(410,35,100,30);

        JLabel label3 = new JLabel("日期:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(550,0,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(610,35,100,30);

        JButton bt1 = new JButton("查询");
        panel.add(bt1);
        bt1.setBounds(290,95,85,40);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text2.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else {
                    Vector<Message> vct3 = new Vector<Message>();
                    p1.removeAll();
                    refresh_selectMessage(p1, 1,vct3,text1.getText(),text2.getText(), text3.getText(),DB.getMatno(DB.User));
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        JButton bt2 = new JButton("删除");
        panel.add(bt2);
        bt2.setBounds(500,95,85,40);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else {
                    Vector<Message> vct4 = new Vector<Message>();
                    DB.selectMessage(vct4,1,text1.getText(), text2.getText(), text3.getText(),DB.getMatno(DB.User));
                    DB.deleMessage(vct4);
                    JOptionPane.showMessageDialog(null,"删除成功","消息提示",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        Vector<Message> vct = new Vector<Message>();
        DB.selectMessage(vct,2,"","","",DB.getMatno(DB.User));
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getMnum();
            tableData[i][1]=vct.get(i).getThing();
            tableData[i][2]=vct.get(i).getDate();
            tableData[i][3]=vct.get(i).getTno();

        }
        Object[] columnTitle = {"编号","公告信息","日期","宿舍楼"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,600,350);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);

        JButton bt3 = new JButton("查看所有信息");
        panel.add(bt3);
        bt3.setBounds(350,530,150,40);
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Message> vct2 = new Vector<Message>();
                p1.removeAll();
                refresh_selectMessage(p1, 2,vct2,"","", "",DB.getMatno(DB.User));
                p1.repaint();
                p1.revalidate();
            }
        });
    }
    //学生查询通知
    public void Student_selectMessage(JPanel panel){
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        panel.add(p1);
        p1.setBounds(125,155,600,350);
        JLabel label1 = new JLabel("年份:");
        panel.add(label1);
        label1.setFont(new Font("Dialog",1,20));
        label1.setBounds(150,0,100 ,100);
        JTextField text1 = new JTextField();
        panel.add(text1);
        text1.setBounds(210,35,100,30);

        JLabel label2 = new JLabel("月份:");
        panel.add(label2);
        label2.setFont(new Font("Dialog",1,20));
        label2.setBounds(350,0,100 ,100);
        JTextField text2 = new JTextField();
        panel.add(text2);
        text2.setBounds(410,35,100,30);

        JLabel label3 = new JLabel("日期:");
        panel.add(label3);
        label3.setFont(new Font("Dialog",1,20));
        label3.setBounds(550,0,100 ,100);
        JTextField text3 = new JTextField();
        panel.add(text3);
        text3.setBounds(610,35,100,30);

        JButton bt1 = new JButton("查询");
        panel.add(bt1);
        bt1.setBounds(390,95,85,40);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text1.getText().equals("")||text2.getText().equals("")||text2.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"输入框不得为空","消息提示",JOptionPane.WARNING_MESSAGE);
                else {
                    Vector<Message> vct3 = new Vector<Message>();
                    p1.removeAll();
                    refresh_selectMessage(p1, 1,vct3,text1.getText(),text2.getText(), text3.getText(),DB.getBuding(DB.User));
                    p1.repaint();
                    p1.revalidate();
                }
            }
        });
        Vector<Message> vct = new Vector<Message>();
        DB.selectMessage(vct,2,"","","",DB.getBuding(DB.User));
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getMnum();
            tableData[i][1]=vct.get(i).getThing();
            tableData[i][2]=vct.get(i).getDate();
            tableData[i][3]=vct.get(i).getTno();
        }
        Object[] columnTitle = {"编号","公告信息","日期","宿舍楼"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i < 4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,600,350);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);

        JButton bt3 = new JButton("查看所有信息");
        panel.add(bt3);
        bt3.setBounds(350,530,150,40);
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Message> vct2 = new Vector<Message>();
                p1.removeAll();
                refresh_selectMessage(p1, 2,vct2,"","", "",DB.getBuding(DB.User));
                p1.repaint();
                p1.revalidate();
            }
        });
    }
    //刷新公告信息
    public void refresh_selectMessage(JPanel p1,int select,Vector<Message> vct,String year,String month,String date,String user){
        DB.selectMessage(vct,select,year,month,date,user);
        Object[][] tableData = new Object[vct.size()][4];
        for(int i=0;i< vct.size();i++) {
            tableData[i][0]=vct.get(i).getMnum();
            tableData[i][1]=vct.get(i).getThing();
            tableData[i][2]=vct.get(i).getDate();
            tableData[i][3]=vct.get(i).getTno();
        }
        Object[] columnTitle = {"编号","公告信息","日期","宿舍楼"};
        JTable table = new JTable(tableData, columnTitle);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
        head.setFont(new Font("黑体",Font.HANGING_BASELINE,20));
        table.setFont(new Font("仿宋", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        for (int i = 0; i <4; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(100);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane);
        scrollPane.setBounds(0,0,600,350);
        p1.setBackground(Color.white);
        p1.setBackground(Color.BLACK);
    }
    //宿管个人信息
    //梅傲寒
}
