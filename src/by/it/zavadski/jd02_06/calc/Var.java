package by.it.zavadski.jd02_06.calc;

import by.it.zavadski.jd02_05.Errors;
import by.it.zavadski.jd02_05.ResourceManager;
import by.it.zavadski.jd02_06.Logger;

import java.util.HashMap;
import java.util.Map;

abstract class Var implements Operation {

    public static ResourceManager resourceManager=ResourceManager.INSTANCE;
    private static Map<String, Var> vars = new HashMap<>();

    static Var saveVar(String name, Var var) {
        vars.put(name, var);
        return var;
    }

    static void printVar() {
        for (Map.Entry<String, Var> entry : vars.entrySet()) {
            StringBuilder varValue=new StringBuilder();
            varValue.append(entry.getKey()).append("=").append(entry.getValue());
            System.out.println(varValue);
            Logger.log(varValue.toString());
        }
    }

//    static Var createVar(String strVar) throws CalcException {
//        strVar = strVar.trim().replace(" ", "");
//        if (strVar.matches(Patterns.SCALAR))
//            return new Scalar(strVar);
//        else if (strVar.matches(Patterns.VECTOR))
//            return new Vector(strVar);
//        else if (strVar.matches(Patterns.MATRIX))
//            return new Matrix(strVar);
//        else if (vars.containsKey(strVar))
//            return vars.get(strVar);
//        throw new CalcException(resourceManager.get(Errors.CANT_CREATE_VAR)+strVar);
//    }
    static Var createVar(String strVar) throws CalcException {
        VarsCreator var=getVarCreator(strVar);
        if(var!=null)
            return var.createVar(strVar);

        if (vars.containsKey(strVar))
            return vars.get(strVar);
        throw new CalcException(resourceManager.get(Errors.CANT_CREATE_VAR));
    }

    private static VarsCreator getVarCreator(String strVar) {
        strVar = strVar.trim().replace(" ", "");
        VarsCreator var=null;
        if (strVar.matches(Patterns.SCALAR))
            var=new ScalarCreator();
        else if (strVar.matches(Patterns.VECTOR))
            var=new VectorCreator();
        else if (strVar.matches(Patterns.MATRIX))
            var=new MatrixCreator();
        else if (vars.containsKey(strVar))
            var=new MatrixCreator();
        return var;
    }

    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException(resourceManager.get(Errors.CANT_ADD));
    }

    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException(resourceManager.get(Errors.CANT_SUB));
    }

    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException(resourceManager.get(Errors.CANT_MUL));
    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException(resourceManager.get(Errors.CANT_DIV));
    }
}
