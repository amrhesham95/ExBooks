package com.example.exbooks.Screens.homeScreen;

import com.example.exbooks.Presenter;

public interface HomeContract {
    interface HomeViewInterface
    {

    }
    interface HomePresenterInterface extends Presenter
    {
        public void signOut();
    }
}
