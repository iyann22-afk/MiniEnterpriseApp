import controllers.LoginController;
import models.UserModel;
import views.LoginView;
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