package cn.zyc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

/**
 * dsc: WorldClock
 * date: 2020/12/7 15:58
 * author: zyc
 */
public interface WorldClock extends Remote {
    LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;
}
