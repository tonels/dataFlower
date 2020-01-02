package tonels.util;

public class StringUtil {

    private StringUtil() {
    }

    public static boolean isNullStr(Object strin) {
        if (strin == null) {
            return true;
        }
        String str = strin.toString();
        boolean flag = false;
        if ("null".equals(str) || "".equals(str) || "".equals(str.trim())) {
            flag = true;
        }
        return flag;
    }

    public static String trim(String strin) {
        if (isNullStr(strin)) {
            return null;
        }
        return strin.trim();
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    // 首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 得到一个String值的指定长度的字符串形式
     * NOTE:	不足的前面添'0'
     *
     * @param s
     * @param len
     * @param cutHead 当s的长度大于len时，截取方式：true,截掉头部；否则从截掉尾部
     *                例如getStringByAppointLen("12345",3,true) ---> "345"
     * @return
     */
    public static String getStringByAppointLen(String s, int len, boolean cutHead) {
        if (s == null || len <= 0) {
            s = "";
        }
        if (len > s.length()) {
            int size = len - s.length();
            StringBuilder sb = new StringBuilder();
            while (size-- > 0) {
                sb.append("0");
            }
            sb.append(s);
            return sb.toString();
        } else if (len == s.length()) {
            return s;
        } else {
            if (cutHead) {
                return s.substring(s.length() - len, s.length());
            } else {
                return s.substring(0, len);
            }
        }
    }

    /**
     * 得到一个Long值的指定长度的字符串形式
     *
     * @param l
     * @param len
     * @return
     */
    public static String getStringByAppointLen(String l, int len) {
        return getStringByAppointLen(l, len, true);
    }


    /**
     * 功能描述: 〈下划线转驼峰〉
     *
     * @param para 1
     * @return : java.lang.String
     * @author : yangyu 2019/12/6 13:40
     */
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 功能描述: 〈驼峰转下划线〉
     *
     * @param para 1
     * @return : java.lang.String
     * @author : yangyu 2019/12/6 13:42
     */
    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;//定位
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        String str = "updateTime";

        System.out.println(humpToUnderline(str));
    }
}
