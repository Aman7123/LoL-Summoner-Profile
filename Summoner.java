package getJSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class Summoner {
	public String summonerName;
	private String apiKey = ""; //Get API key from https://developer.riotgames.com/
	private String httpHeader = "https://na1.api.riotgames.com/lol/";
	private String apiKeyHeader = "?api_key=";
	static HashMap<String, Object> playerAccountData = new HashMap<String, Object>();
	
	public Summoner(String s){
		summonerName = s.toLowerCase();
		
		String line="";
		try {
			String playerAccountData = "summoner/v3/summoners/by-name/";
			URL riotAPICall = new URL(httpHeader + playerAccountData + summonerName + apiKeyHeader + apiKey);
			BufferedReader in = new BufferedReader(new InputStreamReader(riotAPICall.openStream()));
			line = in.readLine();
			in.close();
		} catch(Exception e) {System.out.println(e);}
		
		JSONObject obj = new JSONObject(line);
		playerAccountData.putAll(obj.toMap());
	}
	
	public String pullData(String m, String n) {
		if(m.equals("playerAccountData")) {
			String pAD = playerAccountData.get(n).toString();
			return pAD;
		}
		return null;
	}
	public boolean getPlayerStatus() {
		return getCurrentGameInfo();
	}
	private boolean getCurrentGameInfo() {
		boolean isOnline;
		try {
			String playerGameData = "spectator/v3/active-games/by-summoner/";
			String id = playerAccountData.get("id").toString();
			URL riotAPICall = new URL(httpHeader + playerGameData + id + apiKeyHeader + apiKey);
			BufferedReader in = new BufferedReader(new InputStreamReader(riotAPICall.openStream()));
			in.close();
			isOnline = true;
		} catch(Exception e) {isOnline=false;}
		return isOnline;
	}
}
