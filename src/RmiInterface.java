import java.rmi.*;

public interface RmiInterface extends Remote
{
    void receiveMessage(String x) throws RemoteException;
    void ShutDown() throws Exception;
    void Restart() throws Exception;
    void LogOff() throws Exception;
}