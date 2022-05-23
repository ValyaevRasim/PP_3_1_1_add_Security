package spring_boot.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
