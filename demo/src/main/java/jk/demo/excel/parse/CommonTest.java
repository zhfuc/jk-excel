package jk.demo.excel.parse;


import jk.core.Excel;
import jk.core.ParseFactory;
import jk.core.excel.parse.base.Mapping;
import jk.core.excel.parse.base.ParseInfo;
import jk.demo.BaseTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Version 1.0.0
 * @Author Jack.Lee
 */
public class CommonTest extends BaseTest {

    public static void main(String[] args){

        File file = getFile("number_test.xlsx");
        parse(file);
    }

    public static void parse(File file){
        //配置文件，同时指定数据开始行号，从1开始
        ParseInfo info = new ParseInfo(file, 2,true);
        info.setMappings(getMappings());
        //设置需要解析sheet名，不设置，默认为第一个sheet
//        info.addSheet("other");
        //获取解析器
        Excel excel = ParseFactory.getExcelParse(info);
        long l = System.currentTimeMillis();
        //开始解析数据
        List<Map> data = excel.parseToMapList();
        l = System.currentTimeMillis() - l;
        for (Map m : data) {
            System.out.println(m);
        }
        System.out.println("all:" + data.size());
        System.out.println("time:" + l);
    }

    //配置映射
    public static List<Mapping> getMappings(){
        List<Mapping> list = new ArrayList<>();
        Mapping m = new Mapping("cash", "周实际回款(元)1");
        list.add(m);
        m = new Mapping("Date", "收集日期2");
        list.add(m);
        m = new Mapping("company", "公司3");
        list.add(m);
        m = new Mapping("code", "项目编码4");
        list.add(m);

        return  list;
    }
}
