package com.osb.proyecto;

import com.osb.socket.endpoint.NotificacionSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 
 */
@Controller
@RequestMapping("/planeacion/proyecto")
public class ProyectoController {

    private static Logger logger = Logger.getLogger(ProyectoController.class);


    @Autowired
    private NotificacionSocket socket;
    
    
    @RequestMapping(value = "/enviar", produces = "application/json; charset=utf-8")
    public @ResponseBody String enviarNotificaciones(){
        HashMap salida = new HashMap();
        try{
            socket.enviarNotificaciones();
            salida.put("status", "ok");
            salida.put("message", "notificaciones enviadas");
        }catch(Exception ex){
            salida.put("status", "fail");
            salida.put("message", "Error: "+ex.getMessage());
        }
        return JSONValue.toJSONString(salida);
    }
}
