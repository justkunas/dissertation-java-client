package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIClient {

	private String ip;
	private int port;
	
	private Registry registry;
	private IRMIInterface rmiInterface;
	
	public RMIClient(String ip, int port){
		setIp(ip);
		setPort(port);
		try{
			System.out.println(getIp() + ":" + getPort());
			setRegistry(LocateRegistry.getRegistry(ip,port));
			setRmiInterface((IRMIInterface)registry.lookup("RMIServer"));
			
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public IRMIInterface getRmiInterface() {
		return rmiInterface;
	}

	public void setRmiInterface(IRMIInterface rmiInterface) {
		this.rmiInterface = rmiInterface;
	}

}
