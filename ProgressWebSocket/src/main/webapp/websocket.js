$(document).ready(function(){
    connect();
});

let webSocket = null;// 'Connect' button click handler
function connect() {
   //var host = window.location.hostname == 'localhost' ? window.location.host+'/InversionPublica2021' : window.location.host;
   
   var host = window.location.host;
   webSocket = new WebSocket('wss://'+host+'/notificaciones');   
       webSocket.onopen = function () {
       console.log('Client connection opened');       
       //console.log('Subprotocol: ' + webSocket.protocol);
       //console.log('Extensions: ' + webSocket.extensions);
        //Notificaciones();
   };   
   webSocket.onmessage = function (event) {
       console.log('Client received: ' + event.data);
       var notif = JSON.parse(event.data);
       //console.log(notif.accion);
       if(notif.accion == "notificar"){
            console.log('Notificado');
       }
   };   
   webSocket.onerror = function (event) {
       console.log('Client error: ');
       console.log(event);
   };   
   webSocket.onclose = function (event) {
        console.log('Client connection closed: ' + event.code);
        setTimeout(connect,5000);
        console.log("reconectando....");
   };
}// 'Disconnect' button click handler
function disconnect() {
   if (webSocket != null) {
       webSocket.close();
       webSocket = null;
   }
}
var message = '';
function sendMessage() {
   //const message = $("#request").val();
   console.log('Client sends: ' + message);
   webSocket.send(message);
}
