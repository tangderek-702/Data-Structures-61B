public class GetEnvironmentVariables {

    //  Fill in the path to your fa20-s*** folder between the quotes
    public static String REPO_DIR = "/Users/derektang/Documents/cs61b/fa20-s1104";

    //  Fill in the path to your snaps-fa20-s*** folder between the quotes
    public static String SNAPS_DIR = "/Users/derektang/Documents/cs61b/fa20-s1104/snaps-fa20-s1104";

    public static void main(String[] args) {

        String catalina = "echo 'export {variable}={value}' >> ~/.zprofile";
        String mac = "echo 'export {variable}={value}' >> ~/.bash_profile";
        String linux = "echo 'export {REPO_DIR}={value}' >> ~/.bashrc";
        String catalinaSource = "source ~/.zprofile";
        String macSource = "source ~/.bash_profile";
        String linuxSource = "source ~/.bashrc";

        String yourOS = System.getProperty("os.name").toLowerCase();

        String repo = null;
        String snaps = null;
        String source = null;
        if (yourOS.contains("mac")) {
            String version = System.getProperty("os.version");
            String shell = System.getenv("SHELL");
            if (shell.contains("zsh")) {
                repo = catalina.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
                snaps = catalina.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
                source = catalinaSource;
            } else {
                repo = mac.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
                snaps = mac.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
                source = macSource;
            }
        } else if (yourOS.contains("nux")) {
            repo = linux.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
            snaps = linux.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
            source = linuxSource;
        }

        if (repo == null) {
            System.out.println();
            System.out.println("Oops! We couldn't detect your OS. Please reach out to a lab TA or post on Ed so we can help you move forward");
            return;
        }

        System.out.println();
        System.out.println("Keep reading the spec to know what to do with this output");
        System.out.println("----------------------------------------------------------");
        System.out.println(repo);
        System.out.println(snaps);
        System.out.println(source);

    }

}
