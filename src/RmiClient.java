import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

public class RmiClient
{
    static public void main(String args[]) throws Exception
    {
        RmiInterface rmiServer;
        Registry registry;

        String serverAddress = args[0];
        String serverPort = args[1];
        String text = args[2];
        System.out.println("sending "+text+" to "+serverAddress+":"+serverPort);
        try
        {
            registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
            // look up the remote object
            rmiServer=(RmiInterface)(registry.lookup("rmiServer"));
            // call the remote method
            if(text.equals("S"))
            {
                rmiServer.receiveMessage("Your Machine is Going to ShutDown");
                rmiServer.ShutDown();
            }

            else if(text.equals("R"))
            {
                rmiServer.receiveMessage("Your Machine is Going to Restart");
                rmiServer.Restart();
            }

            else if(text.equals("L"))
            {
                rmiServer.receiveMessage("Your Machine is Going to LogOff");
                rmiServer.LogOff();
            }

            else
            {
                rmiServer.receiveMessage(text);
            }
        }

        catch(RemoteException e)
        {
            e.printStackTrace();
        }

        catch(NotBoundException e)
        {
            e.printStackTrace();
        }
    }
}