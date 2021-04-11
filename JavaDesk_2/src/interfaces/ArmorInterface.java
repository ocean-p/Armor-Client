/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTO.Armors;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface ArmorInterface extends Remote{
    public boolean createArmor(Armors a) throws RemoteException;
    public ArrayList<Armors> getAllArmor() throws RemoteException;
    public Armors findByArmorId(String id) throws RemoteException;
    public boolean removeArmor(String id) throws RemoteException;
    public boolean updateArmor(Armors a) throws RemoteException;
}
