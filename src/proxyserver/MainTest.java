package proxyserver;

import java.io.IOException;

/**
 * 功能:
 * 作者: 崔兴旺
 * 日期: 2018/5/19
 * 备注:
 */
public class MainTest {
    public static void main(String[] args){
        MainActivity mainActivity=new MainActivity();
        try {
            mainActivity.launch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
