package reflect;

import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
  
public class Func {  
    private IFunction _obj;  
    private Method _method;  
    private Object[] _args;  //函数需要的参数，包含两部分(1、默认的参数个数及类型；2、数据库配置中的参数个数及类型)  
    private int _preArgsNum;  
  
    public Func(IFunction obj, Method method, int preArgsNum, String... args) {  
        this._obj = obj;  
        this._method = method;  
        this._preArgsNum = preArgsNum;  
        this._args = new Object[args.length + preArgsNum];  
        System.arraycopy(args, 0, this._args, preArgsNum, args.length);  //保存数据库中配置的参数个数及类型  
    }  
  
    public Object call(Object... args) throws IllegalArgumentException, IllegalAccessException,  
            InvocationTargetException {  
  
        if (args.length != this._preArgsNum)  
            throw new IllegalArgumentException("Illegal number of the arguments, need " + this._preArgsNum + " but "  
                    + args.length + ".");  
        System.arraycopy(args, 0, this._args, 0, args.length);  //保存默认的参数个数及类型  
        return this._method.invoke(this._obj, this._args);  //调用并运行配置中的函数  
  
    }  
  
}  