package com.hopen.JewelTD.Data;

import com.hopen.JewelTD.Beans.Jewel;
import com.hopen.JewelTD.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/3/8.
 */

public class JewelMap {
    public static List<Jewel> Jewels;
    static {
        Jewels = new ArrayList<>();
        Jewels.add(new Jewel("碎裂的钻石" , "D1" , 12 , 200 , 6 , null , R.drawable.jewel0 , 0.8f ,0 , 0 , null));
        Jewels.add(new Jewel("碎裂的蓝宝石" , "D1" , 12 , 200 , 6 , null , 0 , 0 , 0 , null));
        Jewels.add(new Jewel("碎裂的黄玉" , "D1" , 12 , 200 , 6 , null , 0 , 0 , 0 , null));
        Jewels.add(new Jewel("白银" , "白银" , 12 , 200 , 6 , null , 0 , 0 , 0 , null));
        /*
        B,D,E,G,P,Q,R,Y
         */
    }
}
