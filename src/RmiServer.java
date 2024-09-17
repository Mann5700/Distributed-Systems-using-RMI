import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.lang.Runtime;
import java.util.Properties;
import java.rmi.server.*;

public class RmiServer extends java.rmi.server.UnicastRemoteObject implements RmiInterface
{
    Runtime r = Runtime.getRuntime();
    Process p = null;
    String s = System.getProperty("os.name");
    int thisPort;
    String thisAddress;
    Registry registry;

    public void receiveMessage(String x) throws RemoteException
    {
        System.out.println(x);
    }

    public  void ShutDown() throws Exception
    {
        System.out.println("\n OS Name " + s);
        if(s.startsWith("Windows")==true)
        {
            System.out.println("\n In the True \n The Value of S : " + s);
            p = r.exec("shutdown -f -s -t 00");     // -f: forced   -s: shut down   -t 00: time delay to execute function
        }

        else if(s.startsWith("Windows") == true)
        {
            p = r.exec("rundll32.exe Shell32.dll,SHExitWindowsEx 1");
        }
    }

    public void Restart() throws Exception
    {
        System.out.println("\n OS Name " + s);

        if(s.startsWith("Windows")==true)
        {
            p = r.exec("shutdown -f -r -t 00");
        }

        else if(s.equals("Windows 98") == true)
        {
            p = r.exec("rundll32.exe Shell32.dll,SHExitWindowsEx 2");
        }
    }

    public void LogOff() throws Exception
    {
        System.out.println("\n OS Name " + s);
        if(s.startsWith("Windows")==true)
        {
            p = r.exec("shutdown -l -f");
        }

        else if(s.equals("Windows 98") == true)
        {
            p = r.exec("rundll32.exe Shell32.dll,SHExitWindowsEx 0");
        }
    }

    public RmiServer(int port) throws RemoteException
    {
        try
        {
            thisAddress= (InetAddress.getLocalHost()).toString();
        }
        catch(Exception e)
        {
            throw new RemoteException("can't get inet address.");
        }
        thisPort=port;  // this port(registry’s port)
        System.out.println("this address="+thisAddress+",port="+thisPort);
        try
        {
            registry = LocateRegistry.createRegistry( thisPort );
            registry.rebind("rmiServer", this); 
        }

        catch(RemoteException e)
        {
            throw e;
        }
    }
    public static void main(String args[])
    {
        try
        {
            RmiServer s = new RmiServer(Integer.parseInt(args[0]));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
