import java.util.HashMap;
import java.util.Scanner;

class Account{
    static String bank_name = "BOI";
    String acc_no;
    String name;
    double balance;
    String branch_name;
    Account(String acc_no,String name,double balance,String branch_name){
        this.acc_no = acc_no;
        this.name = name;
        this.balance = balance;
        this.branch_name = branch_name;
    }
    public void showAccount(){
        System.out.println("Bank name: "+bank_name);
        System.out.println("Account number:. "+acc_no);
        System.out.println("Holder name: "+name);
        System.out.println("Available balance: "+balance);
        System.out.println("Branch name: "+branch_name);
    }
    public void depositAmount(double amount){
        balance += amount;
        System.out.println("Rs."+amount+" Credited in your Ac:."+acc_no+"; Updated balance: "+balance);
    }
    public void withdrawAmount(double amount){
        if (amount<=balance) {
            balance -= amount;
            System.out.println("Rs."+amount+" Debited from your Ac:."+acc_no+"; Updated balance: "+balance);
        }else{
            System.out.println("Insufficient balance!!");
        }
    }
    public void checkBalance(){
        System.out.println("Account no.: "+acc_no+"; Available balance: "+balance);
    }
}

public class MyBankExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String,Account> accounts = new HashMap<>();

        while (true) {
            System.out.println("-------*******---------");
            System.out.println("!!Welcome to our services!!");
            System.out.println("1. Create new account\n"
                              +"2. Show my account status\n"
                              +"3. Check your balance\n"
                              +"4. Deposit amount\n"
                              +"5. Withdraw amount\n"
                              +"6. Exit!");
            System.out.println("Enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter account number: ");
                    String acc_no = sc.nextLine();
                    System.out.println("Enter holder name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter initial balance: ");
                    double balance = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter branch name: ");
                    String branch_name = sc.nextLine();
                    accounts.put(acc_no, new Account(acc_no, name, balance, branch_name));
                    System.out.println("Account created successfully!!");
                    break;
                case "2":
                    System.out.println("Enter your account number: ");
                    acc_no = sc.nextLine();
                    if (accounts.containsKey(acc_no)) {accounts.get(acc_no).showAccount();}
                    else System.out.println("Account not found!");
                    break;
                case "3":
                    System.out.println("Enter your account number: ");
                    acc_no = sc.nextLine();
                    if (accounts.containsKey(acc_no)) {accounts.get(acc_no).checkBalance();}
                    else System.out.println("Account not found!");
                    break; 
                case "4":
                    System.out.println("Enter your account number: ");
                    acc_no = sc.nextLine();
                    System.out.println("Enter deposit amount: ");
                    int amount = sc.nextInt();
                    sc.nextLine();
                    if (accounts.containsKey(acc_no)) {accounts.get(acc_no).depositAmount(amount);}
                    else System.out.println("Account not found!");
                    break; 
                case "5":
                    System.out.println("Enter your account number: ");
                    acc_no = sc.nextLine();
                    System.out.println("Enter Withdraw amount: ");
                    amount = sc.nextInt();
                    sc.nextLine();
                    if (accounts.containsKey(acc_no)) {accounts.get(acc_no).withdrawAmount(amount);}
                    else System.out.println("Account not found!");
                    break; 
                case "6":
                    System.out.println("Thanks for giving us a chance to help you!!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!!");
                    sc.close(); 
                    break;
            }                  
        }
        
    }
}
