package com.hkd.lianmeng.tools;

import com.hkd.lianmeng.model.User;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by johe on 2016/9/21.
 */
public class DemoHelper {
    private static class holder {
        private static DemoHelper dh = new DemoHelper();
    }

    private DemoHelper(){}

    public static DemoHelper getInstance() {
        return holder.dh;
    }
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;


    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    public ArrayList<User> filledData(ArrayList<User> date){
        ArrayList<User> mSortList=new ArrayList<User>();
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        for(int i=0; i<date.size(); i++){
            User sortModel = date.get(i);
            sortModel.setUserName(date.get(i).getUserName());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(sortModel.getUserName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        // 根据a-z进行排序源数据
        Collections.sort(mSortList, pinyinComparator);//前为需要排列的数组，后为排列方式
        return mSortList;

    }
}
