import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        openEmail();
    }

    private static void openEmail(){
        String InfoPage = input("""
                ===============================
                1: -> sign up
                2: -> log in
                ===============================
                """);
    }
    private static String input(String prompt){
        return JOptionPane.showInputDialog(prompt);
    }

    private static void display(String prompt){
        JOptionPane.showMessageDialog(null, prompt);
    }
}