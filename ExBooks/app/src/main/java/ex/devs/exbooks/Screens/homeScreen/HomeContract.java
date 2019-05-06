package ex.devs.exbooks.Screens.homeScreen;

import ex.devs.exbooks.Presenter;

public interface HomeContract {
    interface HomeViewInterface
    {

    }
    interface HomePresenterInterface extends Presenter
    {
        public void signOut();
    }
}
