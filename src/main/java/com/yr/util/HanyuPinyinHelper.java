package com.yr.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * 
 * @Date:2018年5月23日下午4:41:25	
 *
 * @author: 唐子壕
 *
 * @describe : 将文字转为汉语拼音
 */
public class HanyuPinyinHelper {

	/**
	 * 
	 * @Date : 2018年5月23日下午4:43:06
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String
	 * 
	 * @describe : 将文字转为汉语拼音
	 *
	 * @param chineseLanguage 要转成拼音的中文
	 */
    public String toHanyuPinyin(String chineseLanguage) {
        char[] clChars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < clChars.length; i++) {
                if (String.valueOf(clChars[i]).matches("[\u4e00-\u9fa5]+")) { // 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(clChars[i], defaultFormat)[0];
                } else { // 如果字符不是中文,则不转换
                    hanyupinyin += clChars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.print("字符不能转成汉语拼音\n");
        }
        return hanyupinyin;
    }
    /**
     * 
     * @Date : 2018年5月23日下午4:46:31
     * 
     * @author : 唐子壕
     *	
     * @return : String
     *
     * @param chineseLanguage 
     */
    public static String getFirstLettersUp(String chineseLanguage) {
        return getFirstLetters(chineseLanguage, HanyuPinyinCaseType.UPPERCASE);
    }
    
    /**
     * 
     * @Date : 2018年5月23日下午4:47:05
     * 
     * @author : 唐子壕
     *	
     * @return : String
     *
     * @param chineseLanguage 
     */
    public static String getFirstLettersLo(String chineseLanguage) {
        return getFirstLetters(chineseLanguage, HanyuPinyinCaseType.LOWERCASE);
    }
    /**
     * 
     * @Date : 2018年5月23日下午4:48:48
     * 
     * @author : 唐子壕
     *	
     * @return : String
     *
     * @param chineseLanguage 
     * @param caseType 
     */
    public static String getFirstLetters(String chineseLanguage, HanyuPinyinCaseType caseType) {
        char[] clChars = chineseLanguage.trim().toCharArray(); 
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType); // 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不带声调
        try {
            for (int i = 0; i < clChars.length; i++) {
                String str = String.valueOf(clChars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) { // 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(clChars[i], defaultFormat)[0].substring(0, 1);
                } else if (str.matches("[0-9]+")) { // 如果字符是数字,取数字
                    hanyupinyin += clChars[i];
                } else if (str.matches("[a-zA-Z]+")) { // 如果字符是字母,取字母
                    hanyupinyin += clChars[i];
                } else { // 否则不转换
                    hanyupinyin += clChars[i]; //如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.print("字符不能转成汉语拼音\n");
        }
        return hanyupinyin;
    }
    
    /**
     * 
     * @Date : 2018年5月23日下午4:49:24
     * 
     * @author : 唐子壕
     *	
     * @return : String
     *
     * @param chineseLanguage 
     */
    public static String getPinyinString(String chineseLanguage) {
        char[] clChars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不带声调
        try {
            for (int i = 0; i < clChars.length; i++) {
                String str = String.valueOf(clChars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) { // 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(
                            clChars[i], defaultFormat)[0];
                } else if (str.matches("[0-9]+")) { // 如果字符是数字,取数字
                    hanyupinyin += clChars[i];
                } else if (str.matches("[a-zA-Z]+")) { // 如果字符是字母,取字母
                    hanyupinyin += clChars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.print("字符不能转成汉语拼音\n");
        }
        return hanyupinyin;
    }
    /**
     * 取第一个汉字的第一个字符
     * @Date : 2018年5月23日下午4:50:30
     * 
     * @author : 唐子壕
     *	
     * @return : String
     *
     * @param chineseLanguage 
     */
    public static String getFirstLetter(String chineseLanguage) {
        char[] clChars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE); // 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不带声调
        try {
            String str = String.valueOf(clChars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) { // 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
                clChars[0], defaultFormat)[0].substring(0, 1);
            } else if (str.matches("[0-9]+")) { // 如果字符是数字,取数字
                hanyupinyin += clChars[0];
            } else if (str.matches("[a-zA-Z]+")) { // 如果字符是字母,取字母

                hanyupinyin += clChars[0];
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.print("字符不能转成汉语拼音\n");
        }
        return hanyupinyin;
    }
//    /**
//     * 
//     * @Date : 2018年5月23日下午4:51:13
//     * 
//     * @author : 唐子壕
//     *	
//     * @param args 
//     */
//    public static void main(String[] args) {
//        HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper() ;
//        System.out.print(hanyuPinyinHelper.toHanyuPinyin("唐子豪"));
//    }
}
