package experiment;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

import java.util.ArrayList;
import java.util.List;


class Person {
    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    int getMobile() {
        return mobile;
    }

    void setMobile(int mobile) {
        this.mobile = mobile;
    }

    String  name;
    int     age;
    int     mobile;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mobile=" + mobile +
                '}';
    }
}

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-11
 * Time: 上午9:50
 * To change this template use File | Settings | File Templates.
 */
public class JsonSmartTest {

    public static void struct2JsonObject() {
        System.out.println("========Struct2JsonObject=======");

        Person person = new Person();
        person.setName("json smart");
        person.setAge(13);
        person.setMobile(20130808);

        Person person2 = new Person();
        person2.setName("test");
        person2.setAge(123);
        person2.setMobile(888666);

        List<Person> array = new ArrayList<Person>();
        array.add(person);
        array.add(person2);

        //1. struct <==> JsonObject
        JSONObject obj = new JSONObject();
//        //obj = (Object)person;  // compiler error!
//        // way 1:
//        JSONDomain data = new JSONDomain();   // for convert
//        data.setResult(person);
//        // obj = (JSONObject)data.getResult(); // run error: ClassCastException
//        obj.put("person", data.getResult());
//        System.out.println(JSONValue.toJSONString(obj));
//
//        // way 2:
//        obj.put("person", array.get(1));
//        System.out.println(JSONValue.toJSONString(obj));
//
//
//        //2. Container <==> JsonObject
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(person);
//        jsonArray.add(person2);
//        JSONObject result = new JSONObject();
//        result.put("persons", jsonArray);
//        System.out.println(JSONValue.toJSONString(result));


        String s = JSONValue.toJSONString(array, JSONStyle.NO_COMPRESS);
        System.out.println( s );


        Person p = null;

            p = (Person) JSONValue.parse( s, Person.class );

        //System.out.println( p.toString() );

        //net.minidev.json.JSONNavi.
    }

    public static void main(String[] args) {
        struct2JsonObject();
    }

}
