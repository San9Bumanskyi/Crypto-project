package view;


import entity.Result;

public interface View {
    String[] getParameters();
    void printResult(Result result);
}
