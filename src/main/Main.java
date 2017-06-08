package main;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import org.json.JSONObject;

import rmi.RMIClient;

public class Main {

	private static enum Commands {
		QUERY, NEXT, PREVIOUS, LOAD
	};

	private static RMIClient client;
	private static JSONObject json;
	
	private static String arguments = "{\"Command\":0,\"Query\":{\"path\":\"Dissertation Index\",\"query\":\"Spanish\",\"filters\":{\"listprice\":{\"enabled\":false,\"max\":987654321,\"min\":200},\"dimensions\":null,\"numberofpages\":{\"enabled\":false,\"max\":123456789,\"min\":0}}},\"IP\":\"81.108.95.40\",\"Port\":5555}";
	private static String results = "";

	public static void main(String[] args) {
		try {
			/*
			json = new JSONObject(args[0]);
			/*/
			json = new JSONObject(arguments);
			//*/
			client = new RMIClient(json.getString("IP"), json.getInt("Port"));
			JSONObject query = json.getJSONObject("Query");

			switch (Commands.values()[json.getInt("Command")]) {

			case QUERY:
				results = search(query);
				break;
			case NEXT:
				results = next(query);
				break;
			case PREVIOUS:
				results = previous(query);
				break;
			case LOAD:
				results = load(query);
				break;
			}
			
			System.out.println(results);
		} catch (RemoteException err) {
			err.printStackTrace();
		}
	}

	private static String search(JSONObject query) throws RemoteException {
		return client.getRmiInterface().search(query.toString());
	}

	private static String next(JSONObject query) throws RemoteException {
		return client.getRmiInterface().nextPage(query.toString());
	}

	private static String previous(JSONObject query) throws RemoteException {
		return client.getRmiInterface().previousPage(query.toString());
	}

	private static String load(JSONObject query) throws RemoteException {
		return client.getRmiInterface().loadPages(query.toString());
	}

}
