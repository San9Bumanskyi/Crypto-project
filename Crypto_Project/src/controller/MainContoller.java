package controller;

import view.View;

public class MainContoller {
    private View view;

    public MainContoller(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
