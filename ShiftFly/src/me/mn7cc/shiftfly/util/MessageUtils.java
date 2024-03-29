package me.mn7cc.shiftfly.util;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mn7cc.shiftfly.custom.FileManager;
import me.mn7cc.shiftfly.custom.Message;
import me.mn7cc.shiftfly.files.MessagesFile;

public class MessageUtils {
	
	public static HashMap<Message, String> messages = new HashMap<Message, String>();
	
	public static void addMessage(String m, String text) {
		
		Message message = null;
		try { message = Message.valueOf(m); }
		catch(IllegalArgumentException e) { return; }
		
		messages.put(message, text);
		
	}
	
	public static String getMessage(Message message, String... values) {
		
		if(!messages.containsKey(message)) return null;
		
		String result = messages.get(message);
		
		if(values != null) {
		
			int index = 0;
			for(String v : values) {
				result = result.replaceAll("\\{" + index + "\\}", v);
				index++;
			}
		
		}
		
		return TextUtils.color(result);
		
	}
	
	public static void loadMessages() {
		
		MessagesFile messages = FileManager.getMessages();
		for(Entry<String, String> entry : messages.getMessages().entrySet()) addMessage(entry.getKey(), entry.getValue());
		
	}
	
	public static void send(CommandSender sender, String message) {
		sender.sendMessage(TextUtils.color(message));
	}
	
	public static void send(CommandSender sender, Message message, String... values) {
		sender.sendMessage(TextUtils.color(getMessage(message, values)));
	}
	
	public static void send(CommandSender sender, Message message) {
		sender.sendMessage(TextUtils.color(getMessage(message, new String[0])));
	}
	
	public static void send(Player player, String message) {
		player.sendMessage(TextUtils.color(message));
	}

	public static void send(Player player, Message message, String... values) {
		player.sendMessage(TextUtils.color(getMessage(message, values)));
	}
	
	public static void send(Player player, Message message) {
		player.sendMessage(TextUtils.color(getMessage(message, new String[0])));
	}
	
	public static void broadcast(String message) {
		Bukkit.getServer().broadcastMessage(TextUtils.color(message));
	}
	
	public static void broadcast(String message, String permission) {
		for(Player player : Bukkit.getOnlinePlayers()) if(player.hasPermission(permission)) player.sendMessage(TextUtils.color(message));
	}
	
}