<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>WebSocket/SockJS Echo Sample (Adapted from Tomcat's echo sample)</title>
  <style type="text/css">
    #connect-container {
      float: left;
      width: 400px
    }

    #connect-container div {
      padding: 5px;
    }

    #console-container {
      float: left;
      margin-left: 15px;
      width: 400px;
    }

    #console {
      border: 1px solid #CCCCCC;
      border-right-color: #999999;
      border-bottom-color: #999999;
      height: 170px;
      overflow-y: scroll;
      padding: 5px;
      width: 100%;
    }

    #console p {
      padding: 0;
      margin: 0;
    }
  </style>
	
	<script src="http://libs.baidu.com/jquery/1.7.0/jquery.min.js"></script>
  <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>

  <script type="text/javascript">
    $(document).ready(function(){
    	var url="ws://localhost:8080/ROOT/websocket"
    	alert("url:"+url);
        if (!url) {
          alert('Select whether to use W3C WebSocket or SockJS');
          return;
        }
		
        ws = (url.indexOf('sockjs') != -1) ? 
          new SockJS(url, undefined, {protocols_whitelist: "websocket"}) : new WebSocket(url);

        ws.onopen = function () {
          setConnected(true);
         };
        ws.onmessage = function (event) {
         $("#message").append("<br>"+event.data);
        };
        ws.onclose = function (event) {
          setConnected(false);
          alert('Info: connection closed.');
        };
    });
  </script>
</head>
<body> 
<div id="message">
</div>
</body>
</html>











 