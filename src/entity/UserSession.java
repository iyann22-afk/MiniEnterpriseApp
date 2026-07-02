package entity;

public class UserSession {
    // Kelas ini bertugas menyimpan data user yang sedang aktif (login)
    private static String activeUser = "admin";
    private static String role = "Admin";

    public static String getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(String activeUser) {
        UserSession.activeUser = activeUser;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserSession.role = role;
    }
}