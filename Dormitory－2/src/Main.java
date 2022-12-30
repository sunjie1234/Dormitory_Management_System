import menu.MyFrame;
public class Main {
    public static void main(String[] args) {
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
        }
      MyFrame frame = new MyFrame("宿舍管理系统");
    }
}