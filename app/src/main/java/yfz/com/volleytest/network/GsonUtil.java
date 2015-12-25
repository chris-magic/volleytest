package yfz.com.volleytest.network;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Authors:huweidong on 2015/11/7
 * Email：huwwds@gmail.com
 */
public final class GsonUtil {
    private static Gson gson = null;

    /**
     * 集合内部泛型Type
     */
    public static class ListDynamicGenericType implements ParameterizedType {

        private Type type;

        public ListDynamicGenericType ( Type type ) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments () {
            return new Type[] { type };
        }

        @Override
        public Type getOwnerType () {
            return null;
        }

        @Override
        public Type getRawType () {
            return List.class;
        }
    }

    static {
        gson = new Gson();
    }

    private GsonUtil () {
    }

    /**
     * 对象转成json
     */
    public static String obj2json ( Object object ) {
        String gsonString = null;
        gsonString = gson.toJson( object );
        return gsonString;
    }

    /**
     * json转成对象
     */
    public static < T > T json2Obj ( String gsonString, Class< T > cls ) {
        T t = null;
        t = gson.fromJson( gsonString, cls );
        return t;
    }

    /**
     * json转成list
     */
    public static < T > List< T > json2List ( String gsonString, Class< T > cls ) {
        List< T > list = null;
        ListDynamicGenericType type = new ListDynamicGenericType( cls );
        list = gson.fromJson( gsonString, type );
        return list;
    }
}
