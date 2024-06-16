package services;

import entity.Result;
import exception.ApplicationException;
import repository.ResultCode;

public class UnsupportedFunction implements Function{
    @Override
    public Result execute(String[] parameters) {
        String message = "This feature is not yet supported.";
        System.out.println(message);
        return new Result(ResultCode.ERROR, new ApplicationException(message));
    }
}
