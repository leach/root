package com.anythy.app.service.impl;

import com.anythy.app.entity.DltVO;
import com.anythy.app.mapper.DltMapper;
import com.anythy.app.service.DltService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DltServiceImpl implements DltService {
    @Autowired
    private DltMapper dltMapper;

    @Override
    public int inputData() throws IOException {

        List<DltVO> list = new ArrayList<>();

        //读取文本路径
        File file = new File("E:\\Dev\\Git\\works\\root\\tools\\multi-datasource\\src\\main\\resources\\dlt.txt");
        String line=null;
        if(file.exists()){
            BufferedReader in=new BufferedReader(new FileReader(file));
            //判断文本是否为空
            while((line=in.readLine())!=null)
            {
                String source = line.trim().replace("\t", "");
                int sn = Integer.parseInt(source.substring(0,5));
                String a = source.substring(5,7);
                String b = source.substring(7,9);
                String c = source.substring(9,11);
                String d = source.substring(11,13);
                String e = source.substring(13,15);
                String f = source.substring(15,17);
                String g = source.substring(17,19);
                DltVO vo = new DltVO(null, sn, a, b, c, d, e, f, g);
                list.add(vo);
            }
            in.close();
        }


        int i = 0;
        for (DltVO dltVO: list) {
            int n = dltMapper.insertData(dltVO);
            i += n;
        }
        log.info("inputData: totalCount=", i);
        return list.size();
    }

    @Override
    public void countAll(){
        List<DltVO> list = dltMapper.queryAll();
    }

    public static void main(String[] args) throws IOException {

        Set<String> set = new HashSet<>();

        //读取文本路径
        File file = new File("E:\\Dev\\Git\\works\\root\\tools\\multi-datasource\\src\\main\\resources\\temp.txt");
        String line=null;
        if(file.exists()){
            BufferedReader in=new BufferedReader(new FileReader(file));
            //判断文本是否为空
            while((line=in.readLine())!=null)
            {
                String source = line.trim().replace("\t", "");
                String a = source.substring(0,2);
                String b = source.substring(2,4);
                String c = source.substring(4,6);
                String d = source.substring(6,8);
                String e = source.substring(8,10);
                String f = source.substring(10,12);
                String g = source.substring(12,14);
                set.add(a);
                set.add(b);
                set.add(c);
                set.add(d);
                set.add(e);
                set.add(f);
                set.add(g);
            }
            in.close();
        }

        System.out.println(set.size());
        List<String> aa = new ArrayList<>();
        List<String> bb = new ArrayList<>();
        for (String n : set) {
            if(Integer.parseInt(n) < 13){
                bb.add(n);
            }
            aa.add(n);
        }

        System.out.println(aa);
        System.out.println(bb);
    }
}
