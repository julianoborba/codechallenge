package br.juliano.code.challenge.database.connection.factory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisDatabaseConnectionFactory {
	
	private static Jedis jedis;

	public static Jedis openConnection(String host, int port) {
		
		return jedis = new Jedis(host, port);
		
	}
	
	public static Jedis getOpenConnection() {
		
		if (jedis.isConnected())
			return jedis;
		
		return null;
		
	}
	
	public static Pipeline getPipelineFromExistingConnection() {
		
		if (jedis.isConnected())
			return jedis.pipelined();
		
		return null;
		
	}
	
	public static void closeConnection() {
		
		jedis.close();
		jedis = null;
		
	}
	
}
