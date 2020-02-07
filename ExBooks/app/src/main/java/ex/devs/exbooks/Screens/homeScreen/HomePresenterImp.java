package ex.devs.exbooks.Screens.homeScreen;

import android.content.Context;
import android.content.Intent;

import ex.devs.exbooks.Screens.loginScreen.LoginScreen;
import ex.devs.exbooks.model.LoginAuth;

public class HomePresenterImp implements HomeContract.HomePresenterInterface {
   HomeContract.HomeViewInterface homeViewInterface;
    LoginAuth loginAuth;
    public HomePresenterImp(HomeContract.HomeViewInterface homeViewInterface) {
        this.homeViewInterface = homeViewInterface;
        loginAuth=new LoginAuth(this);

    }

    @Override
    public void signOut() {
        loginAuth.signOut();
        Intent intent = new Intent(this.getContext(), LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //((Activity)this.getContext()).finish();
        this.getContext().startActivity(intent);
    }

    @Override
    public Context getContext() {
        return (Context) homeViewInterface;
    }
}
