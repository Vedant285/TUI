import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("\033[31m\t\tHey Welcome! to my TUI that makes life Simple");
        System.out.println("\t\t---------------------------------------------");
        System.out.print("Enter the password: ");

        Scanner scanner = new Scanner(System.in);
        String password;
        int wrongPass = 1;
        boolean flag = true;

        while (true) {
            password = scanner.nextLine();
            if (password.equals("ved")) {
                break;
            }

            if (wrongPass == 1) {
                System.out.println("You entered the wrong password - Now you have 2 chances to enter it correctly");
            } else if (wrongPass == 2) {
                System.out.println("You entered the wrong password again! Last chance!");
            } else {
                System.out.println("Sorry! You entered the wrong password 3 times!");
                System.exit(0);
            }

            System.out.print("Again, Enter your password: ");
            wrongPass++;
        }

        System.out.print("Where would you like to perform your job (local/remote): ");
        String location = scanner.nextLine();

        int wrongJob = 1;
        while (wrongJob <= 3) {
            if (!location.equals("local") && !location.equals("remote")) {
                if (wrongJob == 1) {
                    System.out.println("You entered the wrong job - you now only have 2 more chances to enter the correct job!");
                }
                System.out.print("Please enter the valid job (local/remote): ");
                location = scanner.nextLine();
                wrongJob++;
            } else {
                break;
            }
        }

        if (wrongJob == 4) {
            System.out.println("You entered the wrong job 3 times! Please try again after some time.");
            System.exit(0);
        }

        String remoteIp = null;
        if (location.equals("remote")) {
            System.out.println("In which server do you want to run:");
            System.out.print("Enter remote IP address: ");
            remoteIp = scanner.nextLine();
        }

        while (true) {
            System.out.println("\t\t    Press 1 : For Printing the date");
            System.out.println("\t\t    Press 2 : For Checking the Calendar");
            System.out.println("\t\t    Press 3 : For Adding a New User");
            System.out.println("\t\t    Press 4 : For Creating a folder");
            System.out.println("\t\t    Press 5 : For Downloading any software");
            System.out.println("\t\t    Press 6 : For Uninstalling any software");
            System.out.println("\t\t    Press 7 : To exit from this software");
            System.out.println("\t\t    Press 8 : For checking the user");
            System.out.println("\t\t    Press 9 : For Configuring your Web Server");
            System.out.println("\t\t    Press 10: To check all Network cards and IP addresses");
            System.out.println("\t\t    Press 11: For seeing the Port number related to many servers");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            if (location.equals("local")) {
                switch (choice) {
                    case 1:
                        executeCommand("date");
                        break;
                    case 2:
                        executeCommand("cal");
                        break;
                    case 3:
                        System.out.print("Can you please tell me the name of the user you want to add: ");
                        String newUser = scanner.nextLine();
                        executeCommand("useradd " + newUser);
                        System.out.println("Enter your password for New User");
                        executeCommand("passwd " + newUser);
                        break;
                    case 4:
                        System.out.print("Enter your New Folder Name: ");
                        String folderName = scanner.nextLine();
                        executeCommand("mkdir " + folderName);
                        break;
                    case 5:
                        System.out.print("Enter the software name: ");
                        String downloadSoftware = scanner.nextLine();
                        executeCommand("dnf install " + downloadSoftware);
                        break;
                    case 6:
                        System.out.print("Enter the software name: ");
                        String uninstallSoftware = scanner.nextLine();
                        executeCommand("dnf remove " + uninstallSoftware);
                        System.out.println("Software is completely removed!");
                        break;
                    case 7:
                        System.out.println("Thank you for using this Software.");
                        System.exit(0);
                        break;
                    case 8:
                        System.out.print("Enter User Name you want to check: ");
                        String userName = scanner.nextLine();
                        executeCommand("id " + userName);
                        break;
                    case 9:
                        System.out.println("Initializing web server: HTTPD");
                        executeCommand("dnf install httpd");
                        System.out.println("Your web server is installed");
                        executeCommand("systemctl start httpd");
                        System.out.println("Your HTTPD services are started");
                        executeCommand("systemctl stop firewalld");
                        break;
                    case 10:
                        System.out.println("Network cards are:");
                        executeCommand("ifconfig");
                        break;
                    case 11:
                        System.out.println("Port numbers are:");
                        executeCommand("netstat -tnlp");
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            } else {
                switch (choice) {
                    case 1:
                        executeCommand("ssh " + remoteIp + " date");
                        break;
                    case 2:
                        executeCommand("ssh " + remoteIp + " cal");
                        break;
                    case 3:
                        System.out.print("Can you please tell me the name of the user you want to add: ");
                        String newUser = scanner.nextLine();
                        executeCommand("ssh " + remoteIp + " useradd " + newUser);
                        System.out.println("Enter your password for New User");
                        executeCommand("ssh " + remoteIp + " passwd " + newUser);
                        break;
                    case 4:
                        System.out.print("Enter your New Folder Name: ");
                        String folderName = scanner.nextLine();
                        executeCommand("ssh " + remoteIp + " mkdir " + folderName);
                        break;
                    case 5:
                        System.out.print("Enter the software name: ");
                        String downloadSoftware = scanner.nextLine();
                        executeCommand("ssh " + remoteIp + " dnf install " + downloadSoftware);
                        break;
                    case 6:
                        System.out.print("Enter the software name: ");
                        String uninstallSoftware = scanner.nextLine();
                        executeCommand("ssh " + remoteIp + " dnf remove " + uninstallSoftware);
                        System.out.println("Software is completely removed!");
                        break;
                    case 7:
                        System.out.println("Thank you for using this Software.");
                        System.exit(0);
                        break;
                    case 8:
                        System.out.print("Enter User Name you want to check: ");
                        String userName = scanner.nextLine();
                        executeCommand("ssh " + remoteIp + " id " + userName);
                        break;
                    case 9:
                        System.out.println("Initializing web server: HTTPD");
                        executeCommand("ssh " + remoteIp + " dnf install httpd");
                        System.out.println("Your web server is installed");
                        executeCommand("ssh " + remoteIp + " systemctl start httpd");
                        System.out.println("Your HTTPD services are started");
                        executeCommand("ssh " + remoteIp + " systemctl stop firewalld");
                        break;
                    case 10:
                        System.out.println("Network cards are:");
                        executeCommand("ssh " + remoteIp + " ifconfig");
                        break;
                    case 11:
                        System.out.println("Port numbers are:");
                        executeCommand("ssh " + remoteIp + " netstat -tnlp");
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            }

            System.out.print("Press Enter to continue...");
            scanner.nextLine();
            executeCommand("clear");
        }
    }

    public static void executeCommand(String command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);
        processBuilder.inheritIO();
        processBuilder.start();
    }
}
