package repository;

import services.Decode;
import services.Encode;
import services.Function;
import services.UnsupportedFunction;

public enum FunctionCode {
    ENCODE(new Encode()), DECODE(new Decode()), UNSUPPORTED_FUNCTION(new UnsupportedFunction());


    private Function function;
    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}
