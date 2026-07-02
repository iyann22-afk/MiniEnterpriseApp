import controller.LoginController;
import repository.UserModel;
import view.LoginView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            UserModel userModel = new UserModel();
            new LoginController(loginView, userModel);

            loginView.setVisible(true);
        });
    }
}