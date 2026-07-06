package com.orbit.offline

import android.util.Log
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

class LocalWebSocketServer(port: Int) : WebSocketServer(InetSocketAddress(port)) {

    private val clients = mutableSetOf<WebSocket>()

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        conn?.let {
            clients.add(it)
            Log.d("LocalWS", "New connection from ${it.remoteSocketAddress}")
        }
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        conn?.let {
            clients.remove(it)
            Log.d("LocalWS", "Closed connection from ${it.remoteSocketAddress}")
        }
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        Log.d("LocalWS", "Message received: $message")
        // Relay message to all OTHER clients (basic peer-to-peer relay)
        message?.let { msg ->
            clients.filter { it != conn }.forEach { it.send(msg) }
        }
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        Log.e("LocalWS", "Error: ${ex?.message}")
    }

    override fun onStart() {
        Log.d("LocalWS", "Server started successfully on port $port")
    }
}