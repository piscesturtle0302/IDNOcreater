package com.example.IDNOcreater.idno.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class IDNOService {
    public String createNewResidenceIdNo(){
        Random random = new Random();

        int firstInt = random.nextInt(26);
        char firstChar  = (char) ((char) firstInt + 65);

        int secondInt = random.nextInt(2) + 8;
        char secondChar = (char) ((char) secondInt + 48);

        int bodyInt;
        String bodyString = "";
        for(int i=1 ; i<=7 ; i++){
            bodyInt = random.nextInt(10);
            bodyString += String.valueOf(bodyInt);
        }

        String newResidentString = String.valueOf(firstChar) + String.valueOf(secondChar) + bodyString;

        newResidentString += newCheckCode(newResidentString);

        return newResidentString;
    }

    public boolean  checkNewResidenceIdNo(String str){

        final char[] strArr = str.toCharArray();
        return strArr[9]==newCheckCode(str);
    }

    public char newCheckCode(String str){

        final char[] strArr = str.toCharArray();

        final char[] pidCharArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        // 2020新式證號判斷修改新增'8','9'，下方使用二元搜尋法(排序後搜尋)，需依照ASCII碼的編號排序建立陣列
        final char[] pidCharArraySecond = { '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        // 原居留證第一碼英文字應轉換為10~33，十位數*1，個位數*9，這裡直接作[(十位數*1) mod 10] + [(個位數*9) mod 10]
        final int[] pidResidentFirstInt = { 1, 10, 9, 8, 7, 6, 5, 4, 9, 3, 2, 2, 11, 10, 8, 9, 8, 7, 6, 5, 4, 3, 11, 3,
                12, 10 };

        // 原居留證第二碼英文字應轉換為10~33，並僅取個位數*8，這裡直接取[(個位數*8) mod 10]
        final int[] pidResidentSecondInt = { 4, 2, 0, 8, 6, 4, 2, 0, 8, 6, 2, 4, 2, 0, 8, 6, 0, 4, 2, 0, 8, 6, 4, 2, 6,
                0, 8, 4};

        int verifyNum = 0;
        verifyNum += pidResidentFirstInt[Arrays.binarySearch(pidCharArray, strArr[0])];
        verifyNum += pidResidentSecondInt[Arrays.binarySearch(pidCharArraySecond, strArr[1])];
        for (int i = 2, j = 7; i < 9; i++, j--) {
            verifyNum += Character.digit(strArr[i], 10) * j;
        }
        verifyNum = (10 - (verifyNum % 10)) % 10;

        return (char) ((char) verifyNum + 48);
    }
}
