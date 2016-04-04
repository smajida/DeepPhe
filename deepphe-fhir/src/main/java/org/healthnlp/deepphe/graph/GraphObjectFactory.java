package org.healthnlp.deepphe.graph;

import org.healthnlp.deepphe.graph.summary.MedicalRecord;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Girish Chavan on 4/1/2016.
 */
public class GraphObjectFactory {

    public static final String POJO_PACKAGE = "org.healthnlp.deepphe.graph";

    public static MedicalRecord copy(org.healthnlp.deepphe.fhir.summary.MedicalRecord mr) {
        MedicalRecord medR = new MedicalRecord();

        copy(mr, medR);

        return medR;
    }

    /**
     * Calls all getters on source and saves the results using setters on destination
     *
     * @param source
     * @param dest
     */
    public static void copy(Object source, Object dest) {
        Method[] methods = (dest.getClass().getMethods());

        for (Method destMethod : methods) {

            //Identify setter methods
            if (destMethod.getName().startsWith("set")) {
                try {
                    //Try to get matching graph object.
                    Object neo4JObject = instantiateNeo4JObjectIfAvailable(destMethod);


                    //Try to get source data object
                    Method getter = source.getClass().getMethod("get" + destMethod.getName().substring(3));
                    Object srcObject = getter.invoke(source);

                    //if we have matching graph object, then copy into it.
                    if (neo4JObject != null) {
                        //If the neo4jobj is a list, then we need to individually copy contents
                        if(neo4JObject instanceof Collection){

                            //the instantiateNeo4JObjectIfAvailable method returns a list with one empty
//                            object of the type that is required. We extract that and clear the list.
                            Class dstCls = ((List)neo4JObject).get(0).getClass();
                            ((List)neo4JObject).clear();

                            for(Object o:(List)srcObject){
                                Object dstObj = dstCls.newInstance();
                                copy(o, dstObj);

                                ((List)neo4JObject).add(dstObj);
                            }
                        }
                        else { //else just copy the object directly
                            copy(srcObject, neo4JObject);
                        }

                        destMethod.invoke(dest, neo4JObject);
                    } else //else we just save the source data object as is.
                        destMethod.invoke(dest, srcObject);

                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * See if the method parameter is a type of Neo4J object.
     * If yes, return an empty object of that type.
     * If no, return null;
     *
     * @param method
     * @return
     */
    public static Object instantiateNeo4JObjectIfAvailable(Method method) throws IllegalAccessException, InstantiationException {
        for (Type type : method.getGenericParameterTypes()) {

            try {
                if(type instanceof ParameterizedType){
                    ParameterizedType ptype = (ParameterizedType) type;
                    if(Collection.class.isAssignableFrom(Class.forName(ptype.getRawType().getTypeName()))) {
                        Type[] typeArguments = ptype.getActualTypeArguments();
                        for (Type typeArgument : typeArguments) {
                            Class cls = Class.forName(typeArgument.getTypeName());
                            if (cls.getPackage().getName().startsWith(POJO_PACKAGE)) {
                                List list = new ArrayList();
                                list.add(cls.newInstance());
                                return list;
                            }
                        }
                    }
                }
                Class cls = Class.forName(type.getTypeName());
                if (cls.getPackage().getName().startsWith(POJO_PACKAGE)) {
                    return cls.newInstance();
                }
            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            }

        }
        return null;
    }

}
