package com.example.exbooks.Screens.homeScreen;

import com.example.exbooks.Preseneter;

public interface HomeContract {
    interface HomeViewInterface
    {

    }
    interface HomePresenterInterface extends Preseneter
    {
        public void signOut();
    }
}
