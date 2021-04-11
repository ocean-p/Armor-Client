/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author DELL
 */
public class ManageServer {
    public static void main(String[] args) {
        String service="rmi://localhost:9000/ArmorService";
        try {
            ArmorServer as = new ArmorServer();
            LocateRegistry.createRegistry(9000);
            Runtime r = Runtime.getRuntime();
            r.exec("rmiregistry.exe");
            Naming.rebind(service, as);
            System.out.println("Server "+service+ " on");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
