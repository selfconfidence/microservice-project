package ml.weiyan.utils;

import java.util.Random;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package ml.weiyan.utils
 * @date 2019/4/14 17:18
 */
public class SendMessages6Util {
    public  String smsMessageRandom(){
        String ranDom = sms6();
        if(ranDom.length()==6){
            return ranDom;
        }else{
            smsMessageRandom();
        }
        return ranDom;
    }
    public  String sms6() {
        int [] arr = new int[6];
        String stringRandom="";
        for(int i =0;i<6;i++){
            int random = new Random().nextInt(9) + 1;
            arr[i]=random;
        }
        for( int k=0;k<arr.length;k++){
            stringRandom+=arr[k]+"";
        }
        return stringRandom;

    }
}
