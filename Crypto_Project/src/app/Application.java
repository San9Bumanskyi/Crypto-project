package app;

import controller.MainContoller;
import entity.Result;
import repository.FunctionCode;
import services.Function;

import static constants.FunctionCodeConstants.*;

public class Application {
    private final MainContoller mainContoller;

    public Application(MainContoller mainContoller) {
        this.mainContoller = mainContoller;
    }

    public Result run(){
        String[] parameters = mainContoller.getView().getParameters();
        String mode = parameters[0];
        Function function = getFunction(mode);
        return function.execute(parameters);
    }

    private Function getFunction(String mode) {
        return switch (mode){
            case "1" -> FunctionCode.valueOf(ENCODE).getFunction();
            case "2", "3" -> FunctionCode.valueOf(DECODE).getFunction();
            default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };
    }

    public void printResult(Result result){
        mainContoller.getView().printResult(result);
    }
}
