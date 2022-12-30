package DB;
import Entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DBUtil {
    private static Connection dbConn = null;
    public  static String User;
    public DBUtil() {
        String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=manage";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dbConn = DriverManager.getConnection(dbURL, "sunjie", "123456");
        } catch (Exception e) {
            System.out.println("连接异常");
        }
    }
    /**
     * 用户登陆时账号密码比对
     */
    public int load(int select,String s1,String s2)
    {
        if(select==0) {
            try {
                String sql = String.format("select Spassword from Student where Sno = '%s'", s1);
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    String password = res.getString("Spassword");
                    res.close();
                    if (s2.equals(password)) {
                        User = s1;
                        return 0;//表示可正确登录
                    }
                    else
                    {

                     return 1;//表示密码错误
                    }
                } else {
                    return 2;//表示用户不存在
                }
            } catch (Exception e) {
                System.out.println("登陆异常");
            }
        }
        else if(select==1){
            try {
                String sql = String.format("select Mpassword from Management where Mno = '%s'", s1);
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    String password = res.getString("Mpassword");
                    if (s2.equals(password)) {
                        User = s1;
                        return 0;//表示可正确登录
                    } else return 1;//表示密码错误
                } else return 2;//表示用户不存在
            } catch (Exception e) {
                System.out.println("登陆异常");
            }
        }
    else if(select==2){
            try {
                String sql = String.format("select MMpassword from MANA where MMno = '%s'", s1);
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    String password = res.getString("MMpassword");
                    if (s2.equals(password)) {
                        User = s1;
                        return 0;//表示可正确登录
                    } else return 1;//表示密码错误
                } else return 2;//表示用户不存在
            } catch (Exception e) {
                System.out.println("登陆异常");
            }
        }


        return 0;
    }
    /**
     * 用户修改密码
     */
    public int modify(int select,String s1,String s2,String s3,String s4)
    {
        if(select==0) {
            try{
            String sql = String.format("select Spassword from Student where Sno = '%s'", s1);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
                if (res.next()) {
                    String password = res.getString("Spassword");
                    if (s2.equals(password))
                    {
                        if(s3.length()>=6&&s3.length()<=10)
                        {
                            if(s3.equals(s2))
                                return 5;//表示修改密码和原密码一样
                           else if(s3.equals(s4))
                           {
                               String sql2 = "update Student set Spassword = ? WHERE Sno=?";
                               PreparedStatement ps=dbConn.prepareStatement(sql2);
                               ps.setString(1,s3);
                               ps.setString(2,s1);
                               ps.executeUpdate();
                               return 0;
                           }
                           else return 4;//表示两次密码输入不一样
                        }
                        else return 3;//表示密码不符合规范
                    }
                    else return 1;//表示密码错误
                } else return 2;//表示用户不存在
            }catch(Exception e) {
                System.out.println("异常");
            }
        }
        else if(select==1)
        {
            try{
                String sql = String.format("select Mpassword from Management where Mno = '%s'", s1);
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    String password = res.getString("Mpassword");
                    if (s2.equals(password))
                    {
                        if(s3.length()>=6&&s3.length()<=10)
                        {
                            if(s3.equals(s2))
                                return 5;//表示修改密码和原密码一样
                            else if(s3.equals(s4))
                            {
                                String sql2 = "update Management set Mpassword = ? WHERE Mno=?";
                                PreparedStatement ps=dbConn.prepareStatement(sql2);
                                ps.setString(1,s3);
                                ps.setString(2,s1);
                                ps.executeUpdate();
                                return 0;
                            }
                            else return 4;//表示两次密码输入不一样
                        }
                        else return 3;//表示密码不符合规范
                    }
                    else return 1;//表示密码错误
                } else return 2;//表示用户不存在
            }catch(Exception e) {
                System.out.println("异常");
            }
        }
        else if(select==2)
        {
            try{
                String sql = String.format("select MMpassword from MANA where MMno = '%s'", s1);
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    String password = res.getString("MMpassword");
                    if (s2.equals(password))
                    {
                        if(s3.length()>=6&&s3.length()<=10)
                        {
                            if(s3.equals(s2))
                                return 5;//表示修改密码和原密码一样
                            else if(s3.equals(s4))
                            {
                                String sql2 = "update MANA set MMpassword = ? WHERE MMno=?";
                                PreparedStatement ps=dbConn.prepareStatement(sql2);
                                ps.setString(1,s3);
                                ps.setString(2,s1);
                                ps.executeUpdate();
                                return 0;
                            }
                            else return 4;//表示两次密码输入不一样
                        }
                        else return 3;//表示密码不符合规范
                    }
                    else return 1;//表示密码错误
                } else return 2;//表示用户不存在
            }catch(Exception e) {
                System.out.println("异常");
            }
        }
        return -1;
    }
    //返回学生用户信息
    /**
     * 返回学生用户的密码
     */
    public String getStupass(String user)
    {
        try{
            String sql = String.format("select Spassword from Student where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Spassword");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     *返回学生用户的姓名
     */
    public String getStuname(String user)
    {
        try{
            String sql = String.format("select Sname from Student where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Sname");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     * 返回学生用户的专业
     */
    public String getStumajor(String user)
    {
        try{
            String sql = String.format("select Sdept from Student where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Sdept");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     * 返回学生用户性别
     */
    public String getStusex(String user)
    {
        try{
            String sql = String.format("select Ssex from Student where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Ssex");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     * 返回学生用户班级
     */
    public String getStuclass(String user)
    {
        try{
            String sql = String.format("select Sclass from Student where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Sclass");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     * 返回学生用户电话
     */
    public String getStutel(String user)
    {
        try{
            String sql = String.format("select Stel from Student where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Stel");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     *返回学生用户所在宿舍楼
     */
    public String getBuding(String user)
    {
        try{
            String sql = String.format("select Tno from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Tno");
            }
        }catch (Exception e) {
            System.out.println("获取宿舍楼异常");
        }
        return null;
    }
    /**
     * 返回学生用户所在宿舍号
     */
    public String getDno(String user)
    {
        try{
            String sql = String.format("select Dno from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Dno");
            }
        }catch (Exception e) {
            System.out.println("获取宿舍楼异常");
        }
        return null;
    }
    /**
     * 返回学生用户的床位号
     */
    public String getBno(String user)
    {
        try{
            String sql = String.format("select Bno from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Bno");
            }
        }catch (Exception e) {
            System.out.println("获取宿舍楼异常");
        }
        return null;
    }
    //查看学生所在宿舍成员的学生信息，返回到容器中
    public  void Domitory_Student_message(Vector<Student> vct, String user){
        String sql = String.format("select Tno,Dno from Lodging where Sno = '%s'", user);
        try{
        PreparedStatement statement = dbConn.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        if(res.next())
        {
            String s1= res.getString("Tno");
            String s2= res.getString("Dno");
            String sql2 = String.format("select * from Student where Sno IN (select Sno from Lodging where Tno='%s' and Dno = '%s')", s1,s2);
            PreparedStatement statement2 = dbConn.prepareStatement(sql2);
            ResultSet res2 = statement2.executeQuery();
            while(res2.next()){
                Student stu = new Student();
                stu.setSname(res2.getString("Sname"));
                stu.setSno(res2.getString("Sno"));
                vct.add(stu);
            }
        }
    }catch (Exception e) {
        System.out.println("获取同宿舍成员异常");
    }
    }
    /**
     * 查看学生所在宿舍成员的住宿信息，返回到容器中
     */
    public void Domitory_Studet_Dmessage(Vector<Lodging> vct, String user){
        String sql = String.format("select Tno,Dno from Lodging where Sno = '%s'", user);
        try{
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                String s1= res.getString("Tno");
                String s2= res.getString("Dno");
                String sql2 = String.format("select Bno from Lodging where Sno IN (select Sno from Lodging where Tno='%s' and Dno = '%s')", s1,s2);
                PreparedStatement statement2 = dbConn.prepareStatement(sql2);
                ResultSet res2 = statement2.executeQuery();
               while(res2.next()){
                    Lodging lo = new Lodging();
                    lo.setBno(res2.getString("Bno"));
                    vct.add(lo);
                }
            }
        }catch (Exception e) {
            System.out.println("获取同宿舍成员住宿信息异常");
        }
    }
    /**
     * 查看学生所在宿舍楼的宿管信息，返回到容器中
     */
    public void Management_Student_message(Vector<Management> vct, String user){
        String sql = String.format("select Tno from Lodging where Sno = '%s'", user);
        try{
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                String s1= res.getString("Tno");
                String sql2 = String.format("select * from Management where Mno IN(select Mno from Management where Tno='%s')", s1);
                PreparedStatement statement2 = dbConn.prepareStatement(sql2);
                ResultSet res2 = statement2.executeQuery();
                while(res2.next()){
                   Management ma = new Management();
                   ma.setMname(res2.getString("Mname"));
                   ma.setMtel(res2.getString("Mtel"));
                    vct.add(ma);
                }
            }
        }catch (Exception e) {
            System.out.println("获取同宿舍成员住宿信息异常");
        }
    }
    /**
     * 修改用户的联系方式
     */
    public int modify_tel(int select,String user,String tel){
        if(select ==0)
        {
            try{
                String sql2 = "update Student set Stel = ? WHERE Sno=?";
                PreparedStatement ps=dbConn.prepareStatement(sql2);
                ps.setString(1,tel);
                ps.setString(2,user);
                ps.executeUpdate();
                return 0;
            }catch(Exception e) {
                System.out.println("异常");
            }
        }
        else{
            try{
                String sql2 = "update Management set Mtel = ? WHERE Mno=?";
                PreparedStatement ps=dbConn.prepareStatement(sql2);
                ps.setString(1,tel);
                ps.setString(2,user);
                ps.executeUpdate();
                return 0;
            }catch(Exception e) {
                System.out.println("宿管电话获取异常");
            }
        }
        return -1;
    }
    /**
     * 修改用户的密码
     * @param select
     * @param user
     * @param pass
     * @return
     */
    public int modify_pass(int select,String user,String pass){
        if(select ==0)
        {
            try{
                String sql2 = "update Student set Spassword = ? WHERE Sno=?";
                PreparedStatement ps=dbConn.prepareStatement(sql2);
                ps.setString(1,pass);
                ps.setString(2,user);
                ps.executeUpdate();
                return 0;
            }catch(Exception e) {
                System.out.println("异常");
            }
        }
        else {
                try{
                    String sql2 = "update Management set Mpassword = ? WHERE Mno=?";
                    PreparedStatement ps=dbConn.prepareStatement(sql2);
                    ps.setString(1,pass);
                    ps.setString(2,user);
                    ps.executeUpdate();
                    return 0;
                }catch(Exception e) {
                    System.out.println("宿管密码获取异常");
                }

        }
        return -1;
    }
    /**
     * 获取物品表信息，返回到一个容器里，容器存储着这个表的信息
     */
    public void selectItemMessage(Vector<Repair_Message> vct){
        String sql = "select * from ItemMessage";
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Repair_Message re = new Repair_Message();
                re.setItemnum(res.getString("Itemnum"));
                re.setItem(res.getString("Item"));
                vct.add(re);
            }
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
    }
    //报修编号
    public int repair_num()
    {
        String sql="select count(*) as totalCount from Repairt";
        try{
        PreparedStatement statement = dbConn.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        if(res.next()) {
            if (res.getInt("totalCount") == 0)
                return 1;
            else {
                String sql2 = "select max(Number) as Max from Repairt";
                PreparedStatement statement2 = dbConn.prepareStatement(sql2);
                ResultSet res2 = statement2.executeQuery();
                if(res2.next()){
                return res2.getInt("Max") + 1;}
            }
        }
    }catch (Exception e) {
        System.out.println("获取报修数目异常");
    }
        return -1;
    }
    //添加报修表
    public int repairt(int num,String r1,String r2,String r3,String r4,String r5){
        try{
            String sql2 = "insert into Repairt values(?,?,?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setInt(1,num);
            ps.setString(2,r1);
            ps.setString(3,r2);
            ps.setString(4,r3);
            ps.setString(5,r4);
            ps.setString(6,r5);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //返回宿管用户信息
    /**
     * 返回宿管用户的密码
     */
    public String getMapass(String user)
    {
        try{
            String sql = String.format("select Mpassword from Management where Mno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Mpassword");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     * 返回宿管用户的姓名
     */
    public String getManame(String user)
    {
        try{
            String sql = String.format("select Mname from Management where Mno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Mname");
            }
        }catch (Exception e) {
            System.out.println("获取宿管姓名异常");
        }
        return null;
    }
    /**
     * 返回宿管用户的年龄
     */
    public String getMaage(String user)
    {
        try{
            String sql = String.format("select Mage from Management where Mno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Mage");
            }
        }catch (Exception e) {
            System.out.println("获取宿管年龄异常");
        }
        return null;
    }
    /**
     * 返回宿管用户所在宿舍楼
     */
    public String getMatno(String user)
    {
        try{
            String sql = String.format("select Tno from Management where Mno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Tno");
            }
        }catch (Exception e) {
            System.out.println("获取密码异常");
        }
        return null;
    }
    /**
     返回宿管用户电话
     */
    public String getMatel(String user)
    {
        try{
            String sql = String.format("select Mtel from Management where Mno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Mtel");
            }
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return null;
    }
    //添加住宿信息
    public int newLodging(String r1,String r2,String r3,String r4,String r5,String r6){
        try{
            String sql2 = "insert into Lodging values(?,?,?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.setString(2,r2);
            ps.setString(3,r3);
            ps.setString(4,r4);
            ps.setString(5,r5);
            ps.setString(6,r6);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    public int newChufen(String r1,String r2,String r3,String r4,String r5,String r6){
        try{
            String sql2 = "insert into Chufen values(?,?,?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.setString(2,r2);
            ps.setString(3,r3);
            ps.setString(4,r4);
            ps.setString(5,r5);
            ps.setString(6,r6);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //宿管查看处分信息
    public void selectchufenMessage(Vector<edchufen> vct){
        String sql = "select * from chufen";
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                edchufen re = new edchufen();
                re.setNumber(res.getString("Number"));
                re.setTno(res.getString("Tno"));
                re.setDno(res.getString("Dno"));
                re.setReason(res.getString("Reason"));
                re.setAdate(res.getString("Adate"));
                re.setLevel(res.getString("Level"));
                vct.add(re);
            }
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
    }
    //学生查看处分信息
    public void StudentselectchufenMessage(Vector<edchufen> vct,String user){
        String sql = String.format("select Tno,Dno from Lodging where Sno = '%s'", user);
        try{
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                String s1= res.getString("Tno");
                String s2= res.getString("Dno");
                String sql2 = String.format("select * from chufen where Tno ='%s' and Dno = '%s'", s1,s2);
                PreparedStatement statement2 = dbConn.prepareStatement(sql2);
                ResultSet res2 = statement2.executeQuery();
                while(res2.next()){
                    edchufen ed = new edchufen();
                    ed.setAdate(res2.getString("Adate"));
                    ed.setReason(res2.getString("Reason"));
                    ed.setLevel(res2.getString("Level"));
                    vct.add(ed);
                }
            }
        }catch (Exception e) {
            System.out.println("获取s宿舍处分信息异常");
        }
    }
    //宿管搜索住宿信息
    public int Management_Lodging_Search(int select,Vector<Lodging> vct,String s,String user){
        if(select==1)//按照学号搜
        {
        String sql = String.format("select * from Lodging where Sno = '%s'", s);
            try {
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();

                if(res.next()){
                    String s1=res.getString("Tno");
                    if(!s1.equals(getMatno(user)))
                        return 2;//表示该学生不在此宿舍楼中
                    Lodging lo = new Lodging();
                    lo.setSno(res.getString("Sno"));
                    lo.setBno(res.getString("Bno"));
                    lo.setDno(res.getString("Dno"));
                    if(res.getString("isleave").equals("0")){
                        lo.setIsleave("未离校");}
                    else lo.setIsleave("已离校");
                    lo.setStay(res.getString("Stay"));
                    lo.setTno(res.getString("Tno"));
                    vct.add(lo);
                while(res.next()){
                    Lodging lo1 = new Lodging();
                    lo1.setSno(res.getString("Sno"));
                    lo1.setBno(res.getString("Bno"));
                    lo1.setDno(res.getString("Dno"));
                    if(res.getString("isleave").equals("0")){
                    lo1.setIsleave("未离校");}
                    else lo1.setIsleave("已离校");
                    lo1.setStay(res.getString("Stay"));
                    lo1.setTno(res.getString("Tno"));
                    vct.add(lo1);
                }
                return 1;//搜到了这个学号
                }
                else return 0;//表示没有这个学号
            }catch (Exception e) {
                System.out.println("获取信息异常");
            }
        }
        else if(select==2)//按照宿舍搜
        {
            String sql = String.format("select * from Lodging where Dno = '%s' and Tno ='%s'", s,getMatno(user));
            try {
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if(res.next()){
                    Lodging lo = new Lodging();
                    lo.setSno(res.getString("Sno"));
                    lo.setBno(res.getString("Bno"));
                    lo.setDno(res.getString("Dno"));
                    if(res.getString("isleave").equals("0")){
                        lo.setIsleave("未离校");
                    }
                    else lo.setIsleave("已离校");
                    lo.setStay(res.getString("Stay"));
                    lo.setTno(res.getString("Tno"));
                    vct.add(lo);
                    while(res.next()){
                        Lodging lo1 = new Lodging();
                        lo1.setSno(res.getString("Sno"));
                        lo1.setBno(res.getString("Bno"));
                        lo1.setDno(res.getString("Dno"));
                        if(res.getString("isleave").equals("0")){
                            lo1.setIsleave("未离校");
                        }
                        else lo1.setIsleave("已离校");
                        lo1.setStay(res.getString("Stay"));
                        lo1.setTno(res.getString("Tno"));
                        vct.add(lo1);
                    }
                    return 1;//搜到了这个宿舍
                }
                else return 0;//表示没有这个宿舍
            }catch (Exception e) {
                System.out.println("获取信息异常");
            }
        }
        return -1;
    }
    //宿管查看住宿信息
    public void Management_Lodging(Vector<Lodging> vct,String user){
        String sql = String.format("select * from Lodging where Tno = '%s'",getMatno(user));
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Lodging lo = new Lodging();
                lo.setSno(res.getString("Sno"));
                lo.setBno(res.getString("Bno"));
                lo.setDno(res.getString("Dno"));
                if(res.getString("isleave").equals("0")){
                    lo.setIsleave("未离校");}
                else lo.setIsleave("已离校");
                lo.setStay(res.getString("Stay"));
                lo.setTno(res.getString("Tno"));
                vct.add(lo);
            }
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
    }
    //宿管得到此宿舍楼全部学生信息
    public void Management_student(Vector<Student> vct,String tno){
        String sql = String.format("select Sno from Lodging where Tno = '%s'", tno);
        try{
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next())
            {
                String s1= res.getString("Sno");
                String sql2 = String.format("select* from Student where Sno ='%s'", s1);
                try{
                PreparedStatement statement2 = dbConn.prepareStatement(sql2);
                ResultSet res2 = statement2.executeQuery();
                if(res2.next()){
                    Student lo = new Student();
                    lo.setSno(res2.getString("Sno"));
                    lo.setSname(res2.getString("Sname"));
                    lo.setSsex(res2.getString("Ssex"));
                    lo.setSclass(res2.getString("Sclass"));
                    lo.setStel(res2.getString("Stel"));
                    lo.setSdept(res2.getString("Sdept"));
                    lo.setSpassword(res2.getString("Spassword"));
                    vct.add(lo);
                }
                }catch (Exception e) {
                    System.out.println("获取信息异常");
                }
            }
        }catch (Exception e) {
            System.out.println("获取学生信息异常");
        }
    }
    //宿管搜索学生信息
    public int Management_student_Search(Vector<Student> vct,String s,String tno){
        String sql = String.format("select* from Student where Sno = '%s'", s);
        try{
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                String sql2 = String.format("select Sno from Lodging where Sno ='%s' and Tno='%s'", s,tno);
                try{
                    PreparedStatement statement2 = dbConn.prepareStatement(sql2);
                    ResultSet res2 = statement2.executeQuery();
                    if(!res2.next()){
                        return 2;//表示该学生不在此宿舍楼中
                    }
                }catch (Exception e) {
                    System.out.println("获取信息异常");
                }
                Student lo = new Student();
                lo.setSno(res.getString("Sno"));
                lo.setSname(res.getString("Sname"));
                lo.setSsex(res.getString("Ssex"));
                lo.setSclass(res.getString("Sclass"));
                lo.setStel(res.getString("Stel"));
                lo.setSdept(res.getString("Sdept"));
                lo.setSpassword(res.getString("Spassword"));
                vct.add(lo);
                return 1;//搜到了这个学号
            }
            else return 0;//表示没有这个学号
        }
        catch (Exception e) {
        System.out.println("获取精确学生信息异常");
    }
        return -1;
}
    //宿管查看报修表信息
    public void selectRapairMessage(Vector<edrepairt> vct){
        String sql = "select * from Repairt";
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                edrepairt re = new edrepairt();
                re.setNumber(res.getString("Number"));
                re.setTno(res.getString("Tno"));
                re.setDno(res.getString("Dno"));
                re.setItemnum(res.getString("Itemnum"));
                re.setDes(res.getString("Des"));
                re.setDate(res.getString("Date"));
                vct.add(re);
            }
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
    }
    //宿管判断报修信息
    public int judgerepair(String r1){
        String sql =String.format("select * from Repairt where Number='%s'",r1) ;
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
           if(res.next()){
                return 0;
            }
           else return -1;
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
        return -1;
    }
    //宿管删除报修信息
    public void derepair(String r1){
        try{
            String sql2 = "delete from Repairt where Number=?";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("异常");
        }
    }
    //宿管判断处分信息
    public int judgechufen(String r1){
        String sql =String.format("select * from chufen where Number='%s'",r1) ;
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return 0;
            }
            else return -1;
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
        return -1;
    }
    //宿管删除处分
    public void dechufen(String r1){
        try{
            String sql2 = "delete from chufen where Number=?";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("异常");
        }
    }
    //返回宿舍楼的宿舍个数
    public String getMaDco(String tno)
    {
        try{
            String sql = String.format("select Dco from Floor where Tno = '%s'", tno);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Dco");
            }
        }catch (Exception e) {
            System.out.println("获取宿舍个数异常");
        }
        return null;
    }
    //返回宿舍楼的楼层数
    public String getMaFno(String tno)
    {
        try{
            String sql = String.format("select Fno from Floor where Tno = '%s'", tno);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Fno");
            }
        }catch (Exception e) {
            System.out.println("获取楼层数异常");
        }
        return null;
    }
    //返回宿舍楼的宿舍类别
    public String getMaCategory(String tno)
    {
        try{
            String sql = String.format("select Category from Floor where Tno = '%s'", tno);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Category");
            }
        }catch (Exception e) {
            System.out.println("获取宿舍类别异常");
        }
        return null;
    }
    //宿管删除住宿信息
    public void Management_delete_Lodging(Vector<Lodging>vct)
    {
        for(int i=0;i< vct.size();i++)
        {
            String sql = String.format("delete from Lodging where Sno='%s'",vct.get(i).getSno());
            try {
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
            }catch (Exception e) {
            }
            try {
                String sql1 = String.format("select Dnum  from Domitory where Dno='%s' and Tno ='%s'", vct.get(i).getDno(), vct.get(i).getTno());
                PreparedStatement statement1 = dbConn.prepareStatement(sql1);
                ResultSet res1 = statement1.executeQuery();
                if (res1.next()) {
                    int num = Integer.parseInt(res1.getString("Dnum")) - 1;
                    String Dnum = String.valueOf(num);
                    System.out.println(Dnum);
                    String sql3 = String.format("update  Domitory set Dnum ='%s' where Dno='%s' and Tno='%s'", Dnum, vct.get(i).getDno(), vct.get(i).getTno());
                    PreparedStatement statement3 = dbConn.prepareStatement(sql3);
                    statement3.executeQuery();
                }
            }
            catch (Exception e) {

            }
        }
    }
    //返回宿舍楼信息
    public String Lodging_Floor(String user){
        try{
            String sql = String.format("select Tno from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Tno");
            }
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return null;
    }
    //返回宿舍号信息
    public String Lodging_Dno(String user){
        try{
            String sql = String.format("select Dno from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Dno");
            }
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return null;
    }
    //返回床位号信息
    public String Lodging_Bno(String user){
        try{
            String sql = String.format("select Bno from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Bno");
            }
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return null;
    }
    //返回入住时间
    public String Lodging_Stay(String user){
        try{
            String sql = String.format("select Stay from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("Stay");
            }
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return null;
    }
    //返回是否离校
    public String Lodging_isleave(String user){
        try{
            String sql = String.format("select isleave from Lodging where Sno = '%s'", user);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                return res.getString("isleave");
            }
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return null;
    }
    //判断学生状态
    public int Sno_state(String sno,String user){
        String s = getMatno(user);
        try{
            String sql = String.format("select Tno from Lodging where Sno = '%s'", sno);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                if(s.equals(res.getString("Tno")))
                    return 1;//表示此学生是该宿舍楼的
                else return 2;//学生存在，但是不在这个宿舍楼
            }
            else return 0;//表示没有这个学生
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return -1;
    }
    //更换宿舍时宿舍和床位的信息
    public int Dormitory_state(String user,String Dno,String Bno){
        String s1 = getMatno(user);
        try{
            String sql = String.format("select * from Domitory where Tno = '%s' and Dno = '%s'", s1,Dno);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next())
            {
                String sql2 = String.format("select Dnum,Dcount from Domitory where Tno = '%s' and Dno = '%s'", s1,Dno);
                PreparedStatement statement2 = dbConn.prepareStatement(sql2);
                ResultSet res2 = statement2.executeQuery();
                if(res2.next())
                {
                    if(res2.getString("Dnum").equals(res2.getString("Dcount")))
                        return 1;//该宿舍没空床
                    else {
                        String sql3 = String.format("select Bno from Lodging where Tno = '%s' and Dno = '%s'", s1,Dno);
                        PreparedStatement statement3 = dbConn.prepareStatement(sql3);
                        ResultSet res3 = statement3.executeQuery();
                        while(res3.next())
                        {
                            if(res3.getString("Bno").equals(Bno))
                                return 2;//该床位已经被分配
                        }
                    }
                }
            }
            else return 0;//表示此宿舍楼没有该宿舍
        }catch (Exception e) {
            System.out.println("获取宿管电话异常");
        }
        return 3;//该床位可以被分配
    }
    //更换宿舍和床位
    public void reverse_Dno_Bno(String Dno,String Bno,String Sno ){
        try {
            //原宿舍人数减一
            String sql1 = String.format("select Dnum  from Domitory where Dno='%s'", getDno(Sno));
            PreparedStatement statement = dbConn.prepareStatement(sql1);
            ResultSet res1 = statement.executeQuery();
            if (res1.next()) {
                int num = Integer.parseInt(res1.getString("Dnum")) - 1;
                String Dnum = String.valueOf(num);
                String sql3 = String.format("update  Domitory set Dnum ='%s' where Dno='%s'", Dnum, getDno(Sno));
                PreparedStatement statement3 = dbConn.prepareStatement(sql3);
                statement3.executeQuery();
            }
        }catch (Exception e) {
        }
        try {
            //新宿舍更改信息
            String sql2 = String.format("update  Lodging set Dno = '%s' ,Bno='%s'  where Sno='%s'", Dno, Bno, Sno);
            PreparedStatement statement2 = dbConn.prepareStatement(sql2);
            statement2.executeQuery();
        }catch (Exception e) {
        }
        try{
            //新宿舍人数加一
            String sql4 = String.format("select Dnum  from Domitory where Dno='%s'", Dno);
            PreparedStatement statement4 = dbConn.prepareStatement(sql4);
            ResultSet res4 = statement4.executeQuery();
            if(res4.next()){
                int num2 = Integer.parseInt(res4.getString("Dnum"))+1;
                String Dnum2 = String.valueOf(num2);
                String sql5 = String.format("update Domitory set Dnum='%s' where Dno='%s'", Dnum2,Dno);
                PreparedStatement statement5 = dbConn.prepareStatement(sql5);
                statement5.executeQuery();
            }
        }catch (Exception e) {
        }
    }
    //更改是否离校状态
    public void reverse_isleave(int select,String Sno){
        try {
            String sql1 = String.format("update  Lodging set isleave=%d where Sno='%s'", select,Sno);
            PreparedStatement statement = dbConn.prepareStatement(sql1);
            statement.executeQuery();
        }catch (Exception e) {
        }
    }
    //宿管判断所要增加住宿信息
    public int judgeLodging(String sno,String Tno,String Dno,String Bno){
        String sql = String.format("select * from Student where Sno='%s'",sno);
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(!res.next())
                return 0;//学号不存在
            String sql2 = String.format("select * from Domitory where Tno='%s' and Dno='%s'",Tno,Dno);
            PreparedStatement statement2 = dbConn.prepareStatement(sql2);
            ResultSet res2 = statement2.executeQuery();
            if(!res2.next())
                return 1;//宿舍不存在
            else {
                if(res2.getString("Dnum").equals(res2.getString("Dcount")))
                    return 2;//宿舍已满
                else {
                    String sql3 = String.format("select Bno from Lodging where Tno = '%s' and Dno = '%s'", Tno,Dno);
                    PreparedStatement statement3 = dbConn.prepareStatement(sql3);
                    ResultSet res3 = statement3.executeQuery();
                    while(res3.next())
                    {
                        if(res3.getString("Bno").equals(Bno))
                            return 3;//该床位已经被分配
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("判断住宿信息异常");
        }
        return 4;
    }
    public void newLodging(String sno,String Tno,String Dno,String Bno,String date,int isleave)
    {
        try{
            String sql2 = "insert into Lodging values(?,?,?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,sno);
            ps.setString(2,Tno);
            ps.setString(3,Dno);
            ps.setString(4,Bno);
            ps.setString(5,date);
            ps.setInt(6,isleave);
            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("异常");
        }
    }
    //容器返回宿舍信息
    public void selectDormitory(Vector<Dormitory> vct){
        String sql = "select * from Domitory";
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Dormitory dm = new Dormitory();
                dm.setTno(res.getString("Tno"));
                dm.setDno(res.getString("Dno"));
                dm.setDcount(res.getString("Dcount"));
                dm.setDnum(res.getString("Dnum"));
                vct.add(dm);
            }
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
    }
    //超级管理员查询宿舍信息
    public int MA_Lodging_Search(int select,Vector<Dormitory> vct,String s){
        if(select==1)//按照宿舍楼搜
        {
            String sql = String.format("select * from Domitory where Tno = '%s'", s);
            try {
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if(res.next()){
                    Dormitory dm1 = new Dormitory();
                    dm1.setTno(res.getString("Tno"));
                    dm1.setDno(res.getString("Dno"));
                    dm1.setDnum(res.getString("Dnum"));
                    dm1.setDcount(res.getString("Dcount"));
                    vct.add(dm1);
                while(res.next()){
                    Dormitory dm = new Dormitory();
                    dm.setTno(res.getString("Tno"));
                    dm.setDno(res.getString("Dno"));
                    dm.setDnum(res.getString("Dnum"));
                    dm.setDcount(res.getString("Dcount"));
                    vct.add(dm);
                    }
                    return 1;//搜到了这个宿舍楼
                }
                else return 0;//表示没有这个宿舍楼
            }catch (Exception e) {
                System.out.println("获取信息异常");
            }
        }
        else if(select==2)//按照宿舍搜
        {
            String sql = String.format("select * from Domitory where Dno = '%s'", s);
            try{
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                Dormitory dm1 = new Dormitory();
                dm1.setTno(res.getString("Tno"));
                dm1.setDno(res.getString("Dno"));
                dm1.setDnum(res.getString("Dnum"));
                dm1.setDcount(res.getString("Dcount"));
                vct.add(dm1);
                while(res.next()){
                    Dormitory dm = new Dormitory();
                    dm.setTno(res.getString("Tno"));
                    dm.setDno(res.getString("Dno"));
                    dm.setDnum(res.getString("Dnum"));
                    dm.setDcount(res.getString("Dcount"));
                    vct.add(dm);
                }
                return 1;//搜到了这个宿舍楼
            }
            else return 0;//表示没有这个宿舍楼
            }catch (Exception e) {
                System.out.println("获取信息异常");
            }
        }
        return -1;
    }
    //超级管理新建学生
    public int newStudent(String r1,String r2,int select,String r4,String r5,String r6,String r7){
        try{
            String sql2 = "insert into Student values(?,?,?,?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.setString(2,r2);
            String r3;
            if(select==0)
                r3="男";
            else r3="女";
            ps.setString(3,r3);
            ps.setString(4,r4);
            ps.setString(5,r5);
            ps.setString(6,r6);
            ps.setString(7, r7);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //超级管理员检验学生
    public int judge_student(String r1){
        try{
            String sql = String.format("select* from Student where Sno= '%s'", r1);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return 0;
            }
            else return -1;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //超级管理删除学生
    public void Management_delete_student(String r1){
        try {
            String sql2 = "delete from Student where Sno= ?";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.executeUpdate();
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }

    }
    //超级管理新建宿舍楼
    public int newFloor(String r1,String r2,String r3,int select){
        try{
            String sql2 = "insert into Floor values(?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.setString(2,r2);
            ps.setString(3,r3);
            String r4;
            if(select==0)
                r4="男寝";
            else r4="女寝";
            ps.setString(4,r4);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //超级管理删除宿舍楼
    public int Management_delete_floor(String r1){
        try{
            String sql2 = "delete from Floor where Tno=?";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
            return -1;
        }
    }
    //超级管理新建宿舍
    public int newDor(String r1,String r2,String r3,String r4){
        try{
            String sql2 = "insert into Domitory values(?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.setString(2,r2);
            ps.setString(3,r3);
            ps.setString(4,r4);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //超级管理删除宿舍
    public int deDor(String r1,String r2){
        String sql = String.format("delete from Domitory where Tno='%s' and Dno='%s'", r1,r2);
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return 0;
            }
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
        return -1;
    }
    //宿管查询宿舍信息
    public int Management_searchtDor(Vector<Dormitory> vct,String s,String user) {
        String sql = String.format("select * from Domitory where Dno = '%s' and Tno='%s'", s,getMatno(user));
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                Dormitory dm1 = new Dormitory();
                dm1.setTno(res.getString("Tno"));
                dm1.setDno(res.getString("Dno"));
                dm1.setDnum(res.getString("Dnum"));
                dm1.setDcount(res.getString("Dcount"));
                vct.add(dm1);
                while (res.next()) {
                    Dormitory dm = new Dormitory();
                    dm.setTno(res.getString("Tno"));
                    dm.setDno(res.getString("Dno"));
                    dm.setDnum(res.getString("Dnum"));
                    dm.setDcount(res.getString("Dcount"));
                    vct.add(dm);
                }
                return 1;//搜到了这个宿舍
            } else return 0;//表示没有这个宿舍
        } catch (Exception e) {
            System.out.println("获取信息异常");
        }
        return -1;
    }
    //宿管查询宿舍的学生姓名和学号
    public void Management_Student_Dor(Vector<Student> vct,String Dno,String Tno,Vector<Lodging> vct2){
        try{
            String sql = String.format("select * from Student where Sno IN (select Sno from Lodging where Tno='%s' and Dno = '%s')", Tno,Dno);
            PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()) {
                    Student stu = new Student();
                    stu.setSname(res.getString("Sname"));
                    stu.setSno(res.getString("Sno"));
                    vct.add(stu);
                }
            String sql2 = String.format("select Bno from Lodging   where Tno='%s' and Dno = '%s'", Tno,Dno);
            PreparedStatement statement2 = dbConn.prepareStatement(sql2);
            ResultSet res2 = statement2.executeQuery();
            while(res2.next()) {
                Lodging lo = new Lodging();
                lo.setBno(res2.getString("Bno"));
                vct2.add(lo);
            }
        }catch (Exception e) {
            System.out.println("获取同宿舍成员异常");
        }
    }
    //宿管查询宿舍信息
    public int management_Lodging_Search(String user,Vector<Dormitory> vct){
            String sql = String.format("select * from Domitory where Tno = '%s'", getMatno(user));
            try {
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                if(res.next()){
                    Dormitory dm1 = new Dormitory();
                    dm1.setTno(res.getString("Tno"));
                    dm1.setDno(res.getString("Dno"));
                    dm1.setDnum(res.getString("Dnum"));
                    dm1.setDcount(res.getString("Dcount"));
                    vct.add(dm1);
                    while(res.next()){
                        Dormitory dm = new Dormitory();
                        dm.setTno(res.getString("Tno"));
                        dm.setDno(res.getString("Dno"));
                        dm.setDnum(res.getString("Dnum"));
                        dm.setDcount(res.getString("Dcount"));
                        vct.add(dm);
                    }
                    return 1;//搜到了这个宿舍楼
                }
                else return 0;//表示没有这个宿舍楼
            }catch (Exception e) {
                System.out.println("获取信息异常");
            }
        return -1;
    }
    //新增外来访问
    public void newOutsiders(int num,String name,String reason,String date,String user){
        try{
            String sql = "insert into Outsiders values(?,?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql);
            ps.setInt(1,num);
            ps.setString(2,name);
            ps.setString(3,reason);
            ps.setString(4,date);
            ps.setString(5,getMatno(user));
            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("插入外来人员信息异常");
        }
    }
    //查询外来访问
    public void selectOutsiders(String year,String month,String date,Vector<Outsiders> vct){
        String da = year+"-"+month+"-"+date;
        try{
            String sql = String.format("select* from Outsiders where Odate ='%s'",da);
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                Outsiders ou1 = new Outsiders();
                ou1.setOnum(res.getInt("Onum"));
                ou1.setOreason(res.getString("Oreason"));
                ou1.setOdate(res.getString("Odate"));
                ou1.setOname(res.getString("Oname"));
                ou1.setOTno(res.getString("OTno"));
                vct.add(ou1);
            }
            while(res.next()){
                Outsiders ou = new Outsiders();
                ou.setOnum(res.getInt("Onum"));
                ou.setOreason(res.getString("Oreason"));
                ou.setOdate(res.getString("Odate"));
                ou.setOname(res.getString("Oname"));
                vct.add(ou);
            }
        }catch(Exception e) {
            System.out.println("查询外来人员异常");
        }
    }
    //全部外来访问信息
    public void selectallOutsiders(Vector<Outsiders> vct){
        try{
            String sql = "select* from Outsiders ";
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Outsiders ou = new Outsiders();
                ou.setOnum(res.getInt("Onum"));
                ou.setOreason(res.getString("Oreason"));
                ou.setOdate(res.getString("Odate"));
                ou.setOname(res.getString("Oname"));
                ou.setOTno(res.getString("OTno"));
                vct.add(ou);
            }

        }catch(Exception e) {
            System.out.println("插入外来人员信息异常");
        }
    }
    //删除来访信息
    public void deleteOutsiders(Vector<Outsiders> vct){
        for(int i=0;i<vct.size();i++){
            try{
                String sql = "delete from Outsiders where Onum=?";
                PreparedStatement ps=dbConn.prepareStatement(sql);
                ps.setInt(1,vct.get(i).getOnum());
                ps.executeUpdate();
            }catch(Exception e) {
                System.out.println("异常");
            }
        }
    }
    //超级管理员添加宿管信息
    public int newMa(String r1,String r2,String r3,String r4,String r5,String r6){
        try{
            String sql2 = "insert into Management values(?,?,?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setString(1,r1);
            ps.setString(2,r2);
            ps.setString(3,r3);
            ps.setString(4,r4);
            ps.setString(5,r5);
            ps.setString(6,r6);
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //超级管理员检验除宿管信息
    public int judgeMa(String r1){
        String sql = String.format("select * from Management where Mno='%s'", r1);
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            System.out.println(res);
            if(res.next()){
                return 0;
            }
            else return -1;
        }catch (Exception e) {
            System.out.println("获取信息异常");
        }
        return -1;
    }
    //超级管理员删除宿管信息
    public void deleMa(String r1){
        String sql = "delete from Message where Mnum=?";
        try{
        PreparedStatement ps=dbConn.prepareStatement(sql);
        ps.setString(1,r1);
        ps.executeUpdate();}
        catch(Exception e) {
            System.out.println("异常");
        }
    }
    //超级管理员查询宿管信息
    public void selectMa(Vector<Management> vct,int select,String s){
        if(select==0){
            String sql = String.format("select* from Management where Mno='%s'",s);
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Management ma = new Management();
                    ma.setMno(res.getString("Mno"));
                    ma.setMname(res.getString("Mname"));
                    ma.setTno(res.getString("Tno"));
                    ma.setMtel(res.getString("Mtel"));
                    vct.add(ma);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
        else if(select==1){
            String sql = String.format("select* from Management where Tno='%s'",s);
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Management ma = new Management();
                    ma.setMno(res.getString("Mno"));
                    ma.setMname(res.getString("Mname"));
                    ma.setTno(res.getString("Tno"));
                    ma.setMtel(res.getString("Mtel"));
                    vct.add(ma);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
        else{
            String sql = String.format("select* from Management ");
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Management ma = new Management();
                    ma.setMno(res.getString("Mno"));
                    ma.setMname(res.getString("Mname"));
                    ma.setTno(res.getString("Tno"));
                    ma.setMtel(res.getString("Mtel"));
                    vct.add(ma);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
    }
    //超级管理员查询宿舍楼信息
    public void selectTno(Vector<Floor> vct,int select,String s){
        if(select==0){
        String sql = String.format("select* from Floor where Tno='%s'",s);
        try{
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Floor fo = new Floor();
                fo.setTno(res.getString("Tno"));
                fo.setDco(res.getString("Dco"));
                fo.setFno(res.getString("Fno"));
                fo.setCategory(res.getString("Category"));
                vct.add(fo);
            }
        }catch(Exception e) {
            System.out.println("宿管号异常");
        }
    }
        else{
            String sql = String.format("select* from Floor");
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Floor fo = new Floor();
                    fo.setTno(res.getString("Tno"));
                    fo.setDco(res.getString("Dco"));
                    fo.setFno(res.getString("Fno"));
                    fo.setCategory(res.getString("Category"));
                    vct.add(fo);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
    }
    //超级管理员查询学生信息
    public void selectStudent(Vector<Student> vct,int select,String s){
        if(select==0){
            String sql = String.format("select* from Student where Sno='%s'",s);
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Student st= new Student();
                    st.setSno(res.getString("Sno"));
                    st.setSname(res.getString("Sname"));
                    st.setStel(res.getString("Stel"));
                    vct.add(st);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
        else{
            String sql = String.format("select* from Student");
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Student st= new Student();
                    st.setSno(res.getString("Sno"));
                    st.setSname(res.getString("Sname"));
                    st.setStel(res.getString("Stel"));
                    vct.add(st);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
    }
    //新增通知信息
    public int newMessage(int r1,String r2,String r3,String user){
        try{
            String sql2 = "insert into Message values(?,?,?,?)";
            PreparedStatement ps=dbConn.prepareStatement(sql2);
            ps.setInt(1,r1);
            ps.setString(2,r2);
            ps.setString(3,r3);
            ps.setString(4,getMatno(user));
            ps.executeUpdate();
            return 0;
        }catch(Exception e) {
            System.out.println("异常");
        }
        return -1;
    }
    //学生、宿管查询通告信息
    public void selectMessage(Vector<Message> vct,int select, String year,String month,String date,String Tno){
        String da = year+"-"+month+"-"+date;
        if(select==1)//按日期查
        {
            String sql = String.format("select* from Message where date='%s' and Tno='%s'",da,Tno);
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Message me = new Message();
                    me.setDate(res.getString("Date"));
                    me.setThing(res.getString("thing"));
                    me.setMnum(res.getInt("Mnum"));
                    me.setTno(res.getString("Tno"));
                    vct.add(me);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
        else if(select==2)//查看全部信息
        {
            String sql = String.format("select* from Message ");
            try{
                PreparedStatement statement = dbConn.prepareStatement(sql);
                ResultSet res = statement.executeQuery();
                while(res.next()){
                    Message me = new Message();
                    me.setDate(res.getString("Date"));
                    me.setThing(res.getString("thing"));
                    me.setMnum(res.getInt("Mnum"));
                    me.setTno(res.getString("Tno"));
                    vct.add(me);
                }
            }catch(Exception e) {
                System.out.println("宿管号异常");
            }
        }
    }
    //宿管删除通告信息
    public void deleMessage(Vector<Message> vct){
        for(int i=0;i<vct.size();i++){
            try{
                String sql = "delete from Message where Mnum=?";
                PreparedStatement ps=dbConn.prepareStatement(sql);
                ps.setInt(1,vct.get(i).getMnum());
                ps.executeUpdate();
            }catch(Exception e) {
                System.out.println("异常");
            }
        }
    }
    //查询学生是否分配宿舍
    public int judgeisdo(String Sno) {
        String sql = String.format("select* from Lodging where Sno='%s'", Sno);
        try {
            PreparedStatement statement = dbConn.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return 0;
            } else return -1;
        } catch (Exception e) {
            System.out.println("查询学生是否分配异常");
        }
        return -1;
    }
}
