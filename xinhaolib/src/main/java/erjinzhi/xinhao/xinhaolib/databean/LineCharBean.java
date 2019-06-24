package erjinzhi.xinhao.xinhaolib.databean;

public class LineCharBean {

    private int data;

    private String textBoom;

    private String textLife;

    public String getTextBoom() {
        return textBoom;
    }

    public void setTextBoom(String textBoom) {
        this.textBoom = textBoom;
    }

    public String getTextLife() {
        return textLife;
    }

    public void setTextLife(String textLife) {
        this.textLife = textLife;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "["  + data +
                ']';
    }
}
