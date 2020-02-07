package ex.devs.exbooks.Screens.signupScreen;

import ex.devs.exbooks.Presenter;

public interface SignupContract {

    interface SignupViewInterface {
        String getEmail();
        String getPassword();
    }

    interface SignupPresenterInterface extends Presenter {
        void signup (String email,String password,String phone);

        boolean checkPhone(String phone);
    }
}

