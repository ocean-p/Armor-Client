/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import DTO.Armors;
import interfaces.ArmorInterface;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ArmorServer extends UnicastRemoteObject implements ArmorInterface{
    String filename="ArmorsData.txt";
    ArrayList<Armors> list = null; //= new ArrayList<>();
    public ArmorServer() throws RemoteException{
        
    }

    @Override
    public boolean createArmor(Armors as) throws RemoteException {
        list.add(as);
        if(list==null || list.isEmpty()) return false;
//        FileWriter f = null;
//        PrintWriter pw = null;
        try {
            FileWriter f = new FileWriter(filename);
             PrintWriter pw = new PrintWriter(f);
            for (Armors a : list) {
                String s=a.toString();
                pw.println(s);
            }
            pw.close();
            f.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public ArrayList<Armors> getAllArmor() throws RemoteException {
         list = new ArrayList<>();
//         FileReader fr=null;
//         BufferedReader br = null;
         try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String s = br.readLine();
                String[] arr = s.split("-");
                if(arr.length==6){
                    Armors a = new Armors(arr[0], arr[1], arr[2], Integer.parseInt(arr[3])
                            , arr[4], arr[5]);
                    list.add(a);
                }
            }
            br.close();
            fr.close();
            //return list;
        } catch (Exception e) {
        }
         return list;
    }

    @Override
    public Armors findByArmorId(String id) throws RemoteException {
        for(int i=0; i<list.size(); i++){
            if(id.equalsIgnoreCase(list.get(i).getArmorId())){
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean removeArmor(String id) throws RemoteException {
        boolean check = false;
        Armors a = findByArmorId(id);
        if (a!=null) {
            list.remove(a);
//            FileWriter f = null;
//            PrintWriter pw = null;
            try {
                FileWriter f = new FileWriter(filename);
                PrintWriter pw = new PrintWriter(f);
                for (Armors aa : list) {
                    String s = aa.toString();
                    pw.println(s);
                }
                pw.close();
                f.close();
                check = true;
            } catch (Exception e) {
                check=false;
            }

        } else {
            check = false;
        }
        return check;
    }

    @Override
    public boolean updateArmor(Armors a) throws RemoteException {
        boolean check=false;
        int pos = findByArmorIndex(a.getArmorId());
        if (pos > -1) {
            list.set(pos, a);
//            FileWriter f = null;
//            PrintWriter pw = null;
            try {
               FileWriter f = new FileWriter(filename);
               PrintWriter pw = new PrintWriter(f);
                for (Armors aa : list) {
                    String s = aa.toString();
                    pw.println(s);
                }
                pw.close();
                f.close();
                check=true;
            } catch (Exception e) {
                check=false;
            }
        }
        else{
            check= false;
        }
        return check;
    }

    
    private int findByArmorIndex(String id){
        for (int i = 0; i <list.size(); i++) {
            if(id.equalsIgnoreCase(list.get(i).getArmorId())){
                return i;
            }
        }
        return -1;
    }

 
}
