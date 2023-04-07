package api.endpoints;


/*
Swagger URI --> https://petstore.swagger.io


Create user (POST) : https://petstore.swagger.io/v2/user
Get user (GET) : https://petstore.swagger.io/v2/user/{username}
Update user (PUT) : https://petstore.swagger.io/v2/user/{username}
Delete user (DELETE) : https://petstore.swagger.io/v2/user/{username}

*/
public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";
	
	
	//User Module
	public static  String user_post_url = base_url+"/user";
	public static String user_get_url = base_url+"/user/{username}";
	public static String user_update_url = base_url+"/user/{username}";
	public static String user_delete_url = base_url+"/user/{username}";
	
	
	//Store Module
	
	
	
	
	
	//Pet Module
}
