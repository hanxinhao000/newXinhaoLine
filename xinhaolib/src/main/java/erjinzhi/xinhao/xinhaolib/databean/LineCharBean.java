package erjinzhi.xinhao.xinhaolib.databean;

public class LineCharBean {

    private int data;

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
