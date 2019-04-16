package com.example.exbooks.Screens.homeScreen;

import com.example.exbooks.model.BookDBService;

public class HomePresenterImp implements HomeContract.HomePresenterInterface {
   HomeContract.HomeViewInterface homeViewInterface;

    public HomePresenterImp(HomeContract.HomeViewInterface homeViewInterface) {
        this.homeViewInterface = homeViewInterface;

    }
}
