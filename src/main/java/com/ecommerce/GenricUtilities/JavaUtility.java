package com.ecommerce.GenricUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {


    /**
     * @ this method is used for generating the random number
     * @author Ashish
     *
     */
    public int getRandomNum(){
        Random r = new Random();
        return r.nextInt(300);
    }


    /**
     * @ this method is used for generating the System date
     * @author Ashish
     *
     */
    public String getSystemDate(){
        Date date = new Date();
       return date.toString();
    }


    /**
     * this method is used for generating the modified system date
     * @author Ashish
     *
     */
    public String getFomattedSysDate(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yyyy hh-MM-ss");
        Date date=new Date();
       return simpleDateFormat.format(date);
    }
}
