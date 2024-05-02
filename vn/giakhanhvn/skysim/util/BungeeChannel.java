/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.io.ByteArrayDataInput
 *  com.google.common.io.ByteArrayDataOutput
 *  com.google.common.io.ByteStreams
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.messaging.Messenger
 *  org.bukkit.plugin.messaging.PluginMessageListener
 */
package vn.giakhanhvn.skysim.util;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeChannel {
    private static WeakHashMap<Plugin, BungeeChannel> registeredInstances = new WeakHashMap();
    private final PluginMessageListener messageListener;
    private final Plugin plugin;
    private final Map<String, Queue<CompletableFuture<?>>> callbackMap;
    private Map<String, ForwardConsumer> forwardListeners;
    private ForwardConsumer globalForwardListener;

    public static synchronized BungeeChannel of(Plugin plugin) {
        return registeredInstances.compute(plugin, (k, v) -> {
            if (v == null) {
                v = new BungeeChannel(plugin);
            }
            return v;
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public BungeeChannel(Plugin plugin) {
        this.plugin = Objects.requireNonNull(plugin, "plugin cannot be null");
        this.callbackMap = new HashMap();
        WeakHashMap<Plugin, BungeeChannel> weakHashMap = registeredInstances;
        synchronized (weakHashMap) {
            registeredInstances.compute(plugin, (k, oldInstance) -> {
                if (oldInstance != null) {
                    oldInstance.unregister();
                }
                return this;
            });
        }
        this.messageListener = this::onPluginMessageReceived;
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(plugin, "BungeeCord");
        messenger.registerIncomingPluginChannel(plugin, "BungeeCord", this.messageListener);
    }

    public void registerForwardListener(ForwardConsumer globalListener) {
        this.globalForwardListener = globalListener;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void registerForwardListener(String channelName, ForwardConsumer listener) {
        if (this.forwardListeners == null) {
            this.forwardListeners = new HashMap<String, ForwardConsumer>();
        }
        Map<String, ForwardConsumer> map = this.forwardListeners;
        synchronized (map) {
            this.forwardListeners.put(channelName, listener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<Integer> getPlayerCount(String serverName) {
        Player player = this.getFirstPlayer();
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("PlayerCount-" + serverName, this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerCount");
        output.writeUTF(serverName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<List<String>> getPlayerList(String serverName) {
        Player player = this.getFirstPlayer();
        CompletableFuture<List<String>> future = new CompletableFuture<List<String>>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("PlayerList-" + serverName, this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerList");
        output.writeUTF(serverName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<List<String>> getServers() {
        Player player = this.getFirstPlayer();
        CompletableFuture<List<String>> future = new CompletableFuture<List<String>>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("GetServers", this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("GetServers");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public void connect(Player player, String serverName) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(serverName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    public void connectOther(String playerName, String server) {
        Player player = this.getFirstPlayer();
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("ConnectOther");
        output.writeUTF(playerName);
        output.writeUTF(server);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<InetSocketAddress> getIp(Player player) {
        CompletableFuture<InetSocketAddress> future = new CompletableFuture<InetSocketAddress>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("IP", this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("IP");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public void sendMessage(String playerName, String message) {
        Player player = this.getFirstPlayer();
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Message");
        output.writeUTF(playerName);
        output.writeUTF(message);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<String> getServer() {
        Player player = this.getFirstPlayer();
        CompletableFuture<String> future = new CompletableFuture<String>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("GetServer", this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("GetServer");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<String> getUUID(Player player) {
        CompletableFuture<String> future = new CompletableFuture<String>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("UUID", this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("UUID");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<String> getUUID(String playerName) {
        Player player = this.getFirstPlayer();
        CompletableFuture<String> future = new CompletableFuture<String>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("UUIDOther-" + playerName, this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("UUIDOther");
        output.writeUTF(playerName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<InetSocketAddress> getServerIp(String serverName) {
        Player player = this.getFirstPlayer();
        CompletableFuture<InetSocketAddress> future = new CompletableFuture<InetSocketAddress>();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("ServerIP-" + serverName, this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("ServerIP");
        output.writeUTF(serverName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void kickPlayer(String playerName, String kickMessage) {
        Player player = this.getFirstPlayer();
        CompletableFuture future = new CompletableFuture();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            this.callbackMap.compute("KickPlayer", this.computeQueueValue(future));
        }
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("KickPlayer");
        output.writeUTF(playerName);
        output.writeUTF(kickMessage);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    public void forward(String server, String channelName, byte[] data) {
        Player player = this.getFirstPlayer();
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Forward");
        output.writeUTF(server);
        output.writeUTF(channelName);
        output.writeShort(data.length);
        output.write(data);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    public void forwardToPlayer(String playerName, String channelName, byte[] data) {
        Player player = this.getFirstPlayer();
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("ForwardToPlayer");
        output.writeUTF(playerName);
        output.writeUTF(channelName);
        output.writeShort(data.length);
        output.write(data);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equalsIgnoreCase("BungeeCord")) {
            return;
        }
        ByteArrayDataInput input = ByteStreams.newDataInput((byte[])message);
        String subchannel = input.readUTF();
        Map<String, Queue<CompletableFuture<?>>> map = this.callbackMap;
        synchronized (map) {
            if (subchannel.equals("PlayerCount") || subchannel.equals("PlayerList") || subchannel.equals("UUIDOther") || subchannel.equals("ServerIP")) {
                String identifier = input.readUTF();
                Queue<CompletableFuture<?>> callbacks = this.callbackMap.get(subchannel + "-" + identifier);
                if (callbacks == null || callbacks.isEmpty()) {
                    return;
                }
                CompletableFuture<?> callback = callbacks.poll();
                try {
                    switch (subchannel) {
                        case "PlayerCount": {
                            callback.complete(input.readInt());
                            break;
                        }
                        case "PlayerList": {
                            callback.complete(Arrays.asList(input.readUTF().split(", ")));
                            break;
                        }
                        case "UUIDOther": {
                            callback.complete(input.readUTF());
                            break;
                        }
                        case "ServerIP": {
                            String ip = input.readUTF();
                            int port = input.readUnsignedShort();
                            callback.complete(new InetSocketAddress(ip, port));
                            break;
                        }
                    }
                }
                catch (Exception ex) {
                    callback.completeExceptionally(ex);
                }
                return;
            }
            Queue<CompletableFuture<?>> callbacks = this.callbackMap.get(subchannel);
            if (callbacks == null) {
                short dataLength = input.readShort();
                byte[] data = new byte[dataLength];
                input.readFully(data);
                if (this.globalForwardListener != null) {
                    this.globalForwardListener.accept(subchannel, player, data);
                }
                if (this.forwardListeners != null) {
                    Map<String, ForwardConsumer> map2 = this.forwardListeners;
                    synchronized (map2) {
                        ForwardConsumer listener = this.forwardListeners.get(subchannel);
                        if (listener != null) {
                            listener.accept(subchannel, player, data);
                        }
                    }
                }
                return;
            }
            if (callbacks.isEmpty()) {
                return;
            }
            CompletableFuture<?> callback = callbacks.poll();
            try {
                switch (subchannel) {
                    case "GetServers": {
                        callback.complete(Arrays.asList(input.readUTF().split(", ")));
                        break;
                    }
                    case "GetServer": 
                    case "UUID": {
                        callback.complete(input.readUTF());
                        break;
                    }
                    case "IP": {
                        String ip = input.readUTF();
                        int port = input.readInt();
                        callback.complete(new InetSocketAddress(ip, port));
                        break;
                    }
                }
            }
            catch (Exception ex) {
                callback.completeExceptionally(ex);
            }
        }
    }

    public void unregister() {
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.unregisterIncomingPluginChannel(this.plugin, "BungeeCord", this.messageListener);
        messenger.unregisterOutgoingPluginChannel(this.plugin);
        this.callbackMap.clear();
    }

    private BiFunction<String, Queue<CompletableFuture<?>>, Queue<CompletableFuture<?>>> computeQueueValue(CompletableFuture<?> queueValue) {
        return (key, value) -> {
            if (value == null) {
                value = new ArrayDeque<CompletableFuture>();
            }
            value.add(queueValue);
            return value;
        };
    }

    private Player getFirstPlayer() {
        Player firstPlayer = this.getFirstPlayer0(Bukkit.getOnlinePlayers());
        if (firstPlayer == null) {
            throw new IllegalArgumentException("Bungee Messaging Api requires at least one player to be online.");
        }
        return firstPlayer;
    }

    private Player getFirstPlayer0(Player[] playerArray) {
        return playerArray.length > 0 ? playerArray[0] : null;
    }

    private Player getFirstPlayer0(Collection<? extends Player> playerCollection) {
        return (Player)Iterables.getFirst(playerCollection, null);
    }

    @FunctionalInterface
    public static interface ForwardConsumer {
        public void accept(String var1, Player var2, byte[] var3);
    }
}

