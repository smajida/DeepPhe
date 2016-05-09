package controllers;


import db.Neo4JUtils;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Utils extends Controller {

   
    public Result clear() {
    	try {
    		String result = Neo4JUtils.clearDb();
    		return ok(result);
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    return ok(index.render(e.getMessage()));
    	}
    }
    
    
    public Result ping() {
		String val = "harry";
		String result = "Here I am, "+val;
		System.err.println("test");

		return ok(result);
    }

}