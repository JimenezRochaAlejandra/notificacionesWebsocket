/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osb.socket.endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

/**
 *
 * @author Draxl
 */
@Service
@ServerEndpoint("/notificaciones")
public class NotificacionSocket {
    
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnMessage
    public void onMessage(String Mensaje) throws IOException, EncodeException{////,Session sesion) throws....
        for(Session per : peers){
            //if(!per.equals(sesion)){////condicional para no reenviar a quien envío el msg
                //System.out.println("mensaje => "+Mensaje);
                per.getBasicRemote().sendText(Mensaje);
            //}
        }
    
    }
    
    @OnOpen
    public void onOpen (Session peer) {
        System.out.println(peer.getRequestURI().toString());
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }

    public void enviarNotificaciones() throws IOException, EncodeException {
       HashMap msg = new HashMap();
       msg.put("accion", "notificar");
       onMessage(JSONValue.toJSONString(msg));
    }
    
}
