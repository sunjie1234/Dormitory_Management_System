package Entity;

public class Lodging {
    //学号
    public String Sno;
    //宿舍号
    public String Dno;
    //床位号
    public String Bno;
    //宿舍楼号
    public String Tno;
    //入住时间
    public String Stay;
    //是否离校
    public String isleave;


    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }

    public String getBno() {
        return Bno;
    }

    public void setBno(String bno) {
        Bno = bno;
    }

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getStay() {
        return Stay;
    }

    public void setStay(String stay) {
        Stay = stay;
    }

    public String getIsleave() {
        return isleave;
    }

    public void setIsleave(String isleave) {
        this.isleave = isleave;
    }


}
